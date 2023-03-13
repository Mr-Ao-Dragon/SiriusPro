package cn.siriusbot.siriuspro.admin.webapi;

import cn.siriusbot.siriuspro.admin.entity.Admin;
import cn.siriusbot.siriuspro.admin.service.LogService;
import cn.siriusbot.siriuspro.admin.service.PlugInService;
import cn.siriusbot.siriuspro.admin.webapi.config.SessionContext;
import cn.siriusbot.siriuspro.config.Constant;
import cn.siriusbot.siriuspro.config.bean.StatisticsPool;
import cn.siriusbot.siriuspro.uitls.AppContextUtil;
import cn.siriusbot.siriuspro.web.R.R;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Component
@ServerEndpoint(value = "/dash-board")
@Log4j2
public class WebsocketController {

    StatisticsPool statisticsPool;

    PlugInService plugInService;

    SessionContext sessionContext;

    LogService logService;

    Timer timer = new Timer();    // 定时任务
    TimerTask task;

    Session session;

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        this.session = session;
        this.sessionContext = AppContextUtil.getBean(SessionContext.class);
        this.logService = AppContextUtil.getBean(LogService.class);
    }

    @OnMessage
    public void onMessage(String message) {
        JSONObject json = JSONObject.parseObject(message);
        Integer event = json.getInteger("event");
        // 首页仪表盘
        if (event == 1 || event == 2) {
            // 验证头信息
            String jsessionid = json.getString("JSESSIONID");
            if (jsessionid != null) {
                HttpSession session = this.sessionContext.getSession(jsessionid);
                Admin admin = (Admin) session.getAttribute(Constant.SESSION_ADMIN);
                if (admin == null) {
                    try {
                        this.session.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                // 启动任务
                if (event == 1){    // 仪表盘
                    this.statisticsPool = AppContextUtil.getBean(StatisticsPool.class);
                    this.plugInService = AppContextUtil.getBean(PlugInService.class);
                    this.task = new TimerTask() {
                        @Override
                        public void run() {
                            R r = new R()
                                    .setCode(0)
                                    .setData(statisticsPool.getStatisticsData())
                                    .setExtra(plugInService.queryAllPlugInList());
                            WebsocketController.this.session.getAsyncRemote().sendText(JSONObject.toJSONString(r));
                        }
                    };
                    this.timer.schedule(task, new Date(), 1000);
                }
                if (event == 2){    // 日志信息
                    this.task = new TimerTask() {
                        @Override
                        public void run() {
                            List<String> logBySession = logService.getLogBySession(WebsocketController.this.session.getId());
                            if (logBySession.size() > 0) {
                                R r = new R()
                                        .setCode(0)
                                        .setData(logBySession);
                                WebsocketController.this.session.getAsyncRemote().sendText(JSONObject.toJSONString(r));
                            }
                        }
                    };
                    // 发送近200条数据
                    List<String> logLately = logService.getLogLately();
                    if (logLately.size() > 0) {
                        R r = new R()
                                .setCode(0)
                                .setData(logLately);
                        WebsocketController.this.session.getAsyncRemote().sendText(JSONObject.toJSONString(r));
                    }
                    logService.registerListener(this.session.getId());
                    this.timer.schedule(task, new Date(), 200);
                }

            }
        }
    }

    @OnClose
    public void onClose() {
        if (task != null) {
            task.cancel();
        }
        logService.deleteListener(this.session.getId());
    }
}

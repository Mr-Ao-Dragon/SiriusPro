package cn.siriusbot.siriuspro.admin.webapi;

import cn.siriusbot.siriuspro.admin.service.PlugInService;
import cn.siriusbot.siriuspro.admin.service.impl.PlugInServiceImpl;
import cn.siriusbot.siriuspro.config.aop.PowerInterceptor;
import cn.siriusbot.siriuspro.config.bean.StatisticsPool;
import cn.siriusbot.siriuspro.web.R.R;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@PowerInterceptor(power = 0)
@RequestMapping("/api/plugin-manage")
public class PlugInController {

    @Autowired
    StatisticsPool statisticsPool;

    @Autowired
    PlugInService plugInService;

    /**
     * 获取统计信息
     */
    @RequestMapping("/statistics")
    public R getStatistics(){
        return new R()
                .setCode(0)
                .setData(statisticsPool.getStatisticsData());
    }

    /**
     * 获取插件信息列表
     */
    @RequestMapping("/info-list")
    public R getInfoList(){
        return new R()
                .setCode(0)
                .setData(plugInService.queryAllPlugInList());
    }

    /**
     * 获取sessionId
     */
    @RequestMapping("/get-session-id")
    public R getSessionId(
            HttpSession session
    ){
        return new R()
                .setCode(0)
                .setData(session.getId());
    }
}

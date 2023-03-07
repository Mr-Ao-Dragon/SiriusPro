package cn.siriusbot.siriuspro.bot.client;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.error.BotIsInOperationException;
import cn.siriusbot.siriuspro.bot.error.NoFindBotEventException;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.event.v1.BotEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotInfo;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotSession;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventBody;
import cn.siriusbot.siriuspro.error.MsgException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

@Log4j2
public class SiriusBotClient implements BotClient {

    BotInfo info;
    BotSession session;

    HashMap<Class<? extends BotEvent>, BotEvent> botEvents = new HashMap<>();

    boolean start = false;

    public SiriusBotClient(BotInfo botInfo, BotConfigBuilder builder) {
        this.info = botInfo;
        this.setConfig(BotHttpEvent.class, builder.getBotHttpEvent()); // 首要加载http插件
        this.session = new BotSession()
                .setS(0)
                .setOpenUrl(info.isSandBox() ? "https://sandbox.api.sgroup.qq.com/" : "https://api.sgroup.qq.com/")
                .setSessionId("");
        this.authBot(); // 验证机器人数据，初始化连接头
        builder.builder(this);
    }

    /**
     * 机器人信息
     *
     * @return
     */
    @Override
    public BotInfo getInfo() {
        return this.info;
    }

    /**
     * 机器人会话信息
     *
     * @return
     */
    @Override
    public BotSession getSession() {
        return this.session;
    }

    /**
     * 登录机器人
     */
    @Override
    public void start() {
        this.start = true;
        this.info.setState(Robot.STATE_PROCEED);    // 登录中
        for (Class<? extends BotEvent> key : this.botEvents.keySet()) {
            BotEvent event = this.botEvents.get(key);
            event.start();
        }
    }

    /**
     * 退出机器人
     */
    @Override
    public void close() {
        pushEvent(BotEventType.BOT_CLOSE, null);
        log.info(String.format("Bot[%s]关闭连接", this.info.getBotId()));
    }

    /**
     * 设置配置对象
     *
     * @param config
     * @return
     */
    @Override
    public <T extends BotEvent> BotClient setConfig(Class<T> clazz, T config) {
        if (start) {
            throw new BotIsInOperationException();
        }
        this.botEvents.put(clazz, config);
        config.init(this);
        return this;
    }

    /**
     * 获取配置对象
     *
     * @param clazz
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends BotEvent> T getBean(Class<T> clazz) {
        for (Class<? extends BotEvent> key : this.botEvents.keySet()) {
            BotEvent event = this.botEvents.get(key);
            if (clazz.isAssignableFrom(event.getClass())) {
                return (T) event;
            }
        }
        throw new NoFindBotEventException();

    }

    /**
     * 推送事件信息
     *
     * @param type
     * @param body
     */
    @Override
    public <T extends BotEventBody> void pushEvent(BotEventType type, T body) {
        for (Class<? extends BotEvent> key : this.botEvents.keySet()) {
            BotEvent event = this.botEvents.get(key);
            Class<? extends BotEvent> aClass = event.getClass();
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                if (method.getAnnotation(OnBotEvent.class) != null) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (body == null) {
                        // 推送单事件
                        if (parameterTypes.length != 1) {
                            continue;
                        }
                        if (parameterTypes[0] != BotEventType.class) {
                            continue;
                        }
                        try {
                            method.invoke(event, type);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                            log.error("bot事件推送异常，bot对象为:" + this + "，嵌套异常为:" + e);
                        }
                    } else {
                        // 推送信息包事件
                        if (parameterTypes.length != 2) {
                            continue;
                        }
                        if (parameterTypes[0] != BotEventType.class) {
                            continue;
                        }
                        if (parameterTypes[1] != body.getClass()) {
                            continue;
                        }
                        try {
                            method.invoke(event, type, body);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                            log.error("bot事件推送异常，bot对象为:" + this + "，嵌套异常为:" + e);
                        }
                    }

                }


            }
        }

    }

    private void authBot() {
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET)
                .setUrl(this.session.getOpenUrl() + "gateway");
        BotHttpEvent http = getBean(BotHttpEvent.class);

        try {
            String request = http.request(botRequest);
            JSONObject json = JSONObject.parseObject(request);
            this.session.setWebSocketUri(new URI(json.getString("url")));
        } catch (URISyntaxException e) {
            log.error(e);
            throw new MsgException(500, "WebSocketUri验证异常!");
        } catch (MsgException e) {
            this.info.setState(Robot.STATE_ERROR);
            this.info.setErrorInfo("验证机器人信息失败，请检查BotId和Token配置!");
            throw e;
        }
    }

}

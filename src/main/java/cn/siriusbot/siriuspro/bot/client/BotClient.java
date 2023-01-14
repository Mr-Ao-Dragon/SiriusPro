package cn.siriusbot.siriuspro.bot.client;


import cn.siriusbot.siriuspro.bot.event.v1.BotEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotInfo;
import cn.siriusbot.siriuspro.bot.pojo.BotSession;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventBody;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;

/**
 * 机器人客户端
 */
public interface BotClient {

    /**
     * 机器人信息
     *
     * @return
     */
    BotInfo getInfo();

    /**
     * 机器人会话信息
     *
     * @return
     */
    BotSession getSession();

    /**
     * 登录机器人
     */
    void start();

    /**
     * 退出机器人
     */
    void close();

    /**
     * 设置配置对象
     *
     * @param config
     * @param <T>
     * @return
     */
    <T extends BotEvent> BotClient setConfig(Class<T> clazz, T config);

    /**
     * 获取配置对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    <T extends BotEvent> T getBean(Class<T> clazz);

    /**
     * 推送事件信息
     *
     * @param type
     * @param body
     * @param <T>
     */
    <T extends BotEventBody> void pushEvent(BotEventType type, T body);
}

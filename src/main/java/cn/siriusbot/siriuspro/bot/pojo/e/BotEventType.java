package cn.siriusbot.siriuspro.bot.pojo.e;


public enum BotEventType {
    /**
     * 通用的
     */
    UNIVERSAL,
    /**
     * 机器人关闭事件
     */
    BOT_CLOSE,
    /**
     * WebSocket消息事件
     */
    WEBSOCKET_MESSAGE,

    /**
     * WebSocket连接失败事件
     */
    WEBSOCKET_ERROR,
    /**
     * 订阅消息事件
     */
    EVENT_MESSAGE,
    /**
     * 启动心跳包事件
     */
    TASK_HEARTBEAT_START,
    /**
     * 暂停心跳包事件
     */
    TASK_HEARTBEAT_PAUSE;

    BotEventType() {
    }
}

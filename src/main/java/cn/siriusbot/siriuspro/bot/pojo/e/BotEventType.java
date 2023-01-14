package cn.siriusbot.siriuspro.bot.pojo.e;



public enum BotEventType {
    /**
     * 通用的
     */
    UNIVERSAL,
    /**
     * WebSocket消息事件
     */
    WEBSOCKET_MESSAGE,
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

    private BotEventType() {
    }
}

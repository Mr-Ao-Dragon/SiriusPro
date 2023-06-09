package cn.siriusbot.siriuspro.bot.plugin;

import cn.siriusbot.siriuspro.bot.application.SiriusApplicationInfo;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
import cn.siriusbot.siriuspro.web.R.R;
import cn.siriusbot.siriuspro.web.pojo.BotHttpRequest;

public interface PlugInClient {

    /**
     * 生成一个唯一的uuid作为键
     */
    String getUuid();

    /**
     * 插件唯一id
     */
    String getPackageName();

    /**
     * 插件详细
     */
    SiriusApplicationInfo getInfo();

    /**
     * 推送事件
     * @param botId 机器人id
     * @param message   事件对象
     */
    void putEvent(String botId, BotEventMessage message);

    /**
     * 插件web请求处理
     * @return R对象
     */
    R webPost(BotHttpRequest request);
}

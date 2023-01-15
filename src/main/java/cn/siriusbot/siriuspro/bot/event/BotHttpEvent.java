package cn.siriusbot.siriuspro.bot.event;

import cn.siriusbot.siriuspro.bot.event.v1.BotEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;

public interface BotHttpEvent extends BotEvent {

    /**
     * http请求
     *
     * @param request 请求对象
     * @return 响应内容
     */
    String request(BotRequest request);

    /**
     * http请求
     * @param request 请求对象
     * @return Bot专属响应体
     */
    BotResponse req (BotRequest request);


}

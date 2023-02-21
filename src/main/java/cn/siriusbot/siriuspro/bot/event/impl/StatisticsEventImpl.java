package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.StatisticsEvent;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodHaveParam;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
import cn.siriusbot.siriuspro.bot.pojo.message.PrivateDomainEvent.PrivateDomainMessageInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent.PublicMessageEvent;
import cn.siriusbot.siriuspro.config.bean.StatisticsPool;
import com.alibaba.fastjson2.JSONObject;

import static cn.siriusbot.siriuspro.bot.pojo.e.BotEventType.EVENT_MESSAGE;

public class StatisticsEventImpl implements StatisticsEvent , EventMethodHaveParam<BotEventMessage> {
    BotClient client;

    StatisticsPool statisticsPool;

    public StatisticsEventImpl(StatisticsPool statisticsPool) {
        this.statisticsPool = statisticsPool;
    }

    /**
     * 注入客户端对象，并初始化
     *
     * @param client
     */
    @Override
    public void init(BotClient client) {
        this.client = client;
    }

    /**
     * 机器人登录事件
     */
    @Override
    public void start() {

    }


    @OnBotEvent
    @Override
    public void onEvent(BotEventType type, BotEventMessage body) {
        if (type == EVENT_MESSAGE){
            this.statisticsPool.addMsgNum(1);
            switch (body.getEventType()){
                case "MESSAGE_CREATE", "AT_MESSAGE_CREATE" -> {
                    // 统计消息事件
                    String msgId = null;
                    if (body.getClazz() == PrivateDomainMessageInfo.class){
                        PrivateDomainMessageInfo messageInfo = JSONObject.parseObject(body.getMessage(), PrivateDomainMessageInfo.class);
                        msgId = messageInfo.getD().getId();
                    }
                    if (body.getClazz() == PublicMessageEvent.class){
                        PublicMessageEvent messageInfo = JSONObject.parseObject(body.getMessage(), PublicMessageEvent.class);
                        msgId = messageInfo.getD().getId();
                    }
                    if (msgId != null){
                        statisticsPool.putEventResponseMsgId(msgId);
                    }
                }
            }
        }


    }
}

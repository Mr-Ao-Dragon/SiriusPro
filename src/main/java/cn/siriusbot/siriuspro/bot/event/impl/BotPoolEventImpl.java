package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotPoolEvent;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodNoParam;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.config.bean.BotPool;

public class BotPoolEventImpl implements BotPoolEvent , EventMethodNoParam {
    BotClient client;
    BotPool pool;

    public BotPoolEventImpl(BotPool pool) {
        this.pool = pool;
    }

    /**
     * 注入客户端对象，并初始化
     *
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
        pool.addBot(this.client);
    }

    @OnBotEvent
    @Override
    public void onEvent(BotEventType type) {
        if (type == BotEventType.BOT_CLOSE){
            // 机器人关闭事件
            this.client.getInfo().setState(Robot.STATE_READY);  // 未登录
            pool.remove(this.client.getInfo().getBotId());
        }
    }
}

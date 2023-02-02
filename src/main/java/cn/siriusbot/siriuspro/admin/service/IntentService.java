package cn.siriusbot.siriuspro.admin.service;

import cn.siriusbot.siriuspro.admin.entity.Intent;
import cn.siriusbot.siriuspro.bot.pojo.e.IntentsType;

import java.util.List;

public interface IntentService {

    /**
     * 订阅事件
     *
     */
    void subscription(String robotId, IntentsType[] intents);

    /**
     * 订阅事件
     *
     */
    void subscription(String robotId, int[] intents);

    /**
     * 查询所有订阅事件
     * @param robotId
     * @return
     */
    List<Intent> queryAllByRobotId(String robotId);

}

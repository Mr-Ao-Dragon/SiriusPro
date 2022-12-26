package cn.siriusbot.siriuspro.entity.api;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.entity.impl.Guild;
import cn.siriusbot.siriuspro.entity.impl.User;


import java.util.List;
import java.util.Map;

/**
 * 用户Api
 */
public interface UserApi {

    /**
     * 获取机器人基本信息
     * @return 返回Bot(机器人)对象
     */
    public abstract Map<User,Object> getRobotInfo(Bot bot);

    /**
     * 获取频道指定机器人频道列表
     * @param bot 传入机器人对象
     * @param before 读此 guild id 之前的数据
     * @param after 读此 guild id 之后的数据
     * @param limit 每次查询的条数，默认100，最大100
     * @return 频道数组
     * after 和 before 同时设置时， after 参数无效
     */
    public abstract Map<List<Guild>,Object> getGuildList(Bot bot, String before, String after, int limit);
}

package cn.siriusbot.siriuspro.bot.api;

import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.Guild;
import cn.siriusbot.siriuspro.bot.api.pojo.User;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

import java.util.List;

/**
 * 用户Api
 */
public interface UserApi {

    /**
     * 获取机器人基本信息
     * @return 返回Bot(机器人)对象
     */
    public abstract Tuple<User,String> getRobotInfo(@NonNull @ENonNull String bot_id);

    /**
     * 获取频道指定机器人频道列表
     * @param bot_id 传入机器人ID
     * @param before 读此 guild id 之前的数据
     * @param after 读此 guild id 之后的数据
     * @param limit 每次查询的条数，默认100，最大100
     * @return 频道数组
     * after 和 before 同时设置时， after 参数无效
     */
    public abstract Tuple<List<Guild>,String> getGuildList(@NonNull @ENonNull String bot_id, String before, String after, int limit);
}

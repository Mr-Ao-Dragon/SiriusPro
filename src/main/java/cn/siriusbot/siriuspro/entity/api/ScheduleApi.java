package cn.siriusbot.siriuspro.entity.api;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.entity.impl.Schedule;
import cn.siriusbot.siriuspro.entity.temp.Tuple;

import java.util.List;
import java.util.Map;

/**
 * 日程对象Api
 */
public interface ScheduleApi {

    /**
     * 获取日程列表
     * 用于获取channel_id指定的子频道中当天的日程列表。
     * 若带了参数 since，则返回在 since 对应当天的日程列表；若未带参数 since，则默认返回今天的日程列表
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param since      起始时间戳(ms)
     * @return 返回日程列表
     */
    public abstract Tuple<List<Schedule>,String> getScheduleListByChannel_id(Bot bot, String channel_id, String since);

    /**
     * 获取日程详情
     * 获取日程子频道 channel_id 下 schedule_id 指定的的日程的详情。
     *
     * @param bot         传入机器人对象
     * @param channel_id  子频道ID
     * @param schedule_id 日程ID
     * @return 返回日程对象
     */
    public abstract Tuple<Schedule,String> getScheduleInfo(Bot bot, String channel_id, String schedule_id);

    /**
     * 删除日程
     * @param bot,传入机器人对象
     * @param channel_id 子频道ID
     * @param schedule_id 日程ID
     * @return 返回操作结果
     */
    public abstract Boolean deleteSchedule(Bot bot, String channel_id, String schedule_id);

    /**
     * 在指定日程子频道创建一个日程
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @param schedule 不带id的日程对象
     * @return 返回日程对象
     */
    public abstract Tuple<Schedule,String> createSchedule(Bot bot,String channel_id,Schedule schedule);

    /**
     * 修改日程
     * @param bot 传入机器人对象
     * @param channel_id 子频道id
     * @param schedule_id 日程ID
     * @param schedule 修改后的日程对象，不带id
     * @return 返回日程对象
     */
    public abstract Tuple<Schedule,String> modifySchedule(Bot bot,String channel_id,String schedule_id,Schedule schedule);
}

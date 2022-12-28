package cn.siriusbot.siriuspro.entity.pojo;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.ScheduleApi;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.lang.reflect.Member;
import java.util.List;

/**
 * 日程对象
 */
@Data
@Accessors(chain = true)
public class Schedule implements ScheduleApi {

    /**
     * 日程ID
     */
    private String id;

    /**
     * 日程名称
     */
    private String name;

    /**
     * 日程描述
     */
    private String description;

    /**
     * 日程开始时间戳
     */
    private String start_timestamp;

    /**
     * 日程结束时间戳
     */
    private String end_timestamp;

    /**
     * 创建者
     */
    private Member creator;

    /**
     * 日程开始时跳转的子频道ID
     */
    private String jump_channel_id;

    /**
     * 日程提醒类型,取值参考RemindType
     */
    private String remind_type;


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
    @SneakyThrows
    @Override
    public Tuple<List<Schedule>, String> getScheduleListByChannel_id(Bot bot, String channel_id, String since) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request;
        if (since != null) {
            request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/schedules?since=" + since).build();
        } else {
            request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/schedules").build();
        }

        String data = SiriusHttpUtils.getRequest(bot, request).body().string();
        Tuple<List<Schedule>, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, List.class)).setSecond(data);
        return tuple;
    }


    /**
     * 获取日程详情
     * 获取日程子频道 channel_id 下 schedule_id 指定的的日程的详情。
     *
     * @param bot         传入机器人对象
     * @param channel_id  子频道ID
     * @param schedule_id 日程ID
     * @return 返回日程对象
     */
    @SneakyThrows
    @Override
    public Tuple<Schedule, String> getScheduleInfo(Bot bot, String channel_id, String schedule_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/schedules/" + schedule_id).build();
        String data = SiriusHttpUtils.getRequest(bot, request).body().string();
        Tuple<Schedule, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }


    /**
     * 删除日程
     *
     * @param bot,传入机器人对象
     * @param channel_id  子频道ID
     * @param schedule_id 日程ID
     * @return 返回操作结果
     */
    @Override
    public Boolean deleteSchedule(Bot bot, String channel_id, String schedule_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/schedules/" + schedule_id).build();
        return SiriusHttpUtils.deleteRequest(bot, request, null).code() == 204;
    }


    /**
     * 在指定日程子频道创建一个日程
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param schedule   不带id的日程对象
     * @return 返回日程对象
     */
    @SneakyThrows
    @Override
    public Tuple<Schedule,String> createSchedule(Bot bot, String channel_id, Schedule schedule) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/schedules").build();
        JSONObject json = new JSONObject();
        json.put("schedule", schedule);
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        String data = SiriusHttpUtils.postRequest(bot, request, body).body().string();
        Schedule sc = JSONObject.parseObject(SiriusHttpUtils.postRequest(bot, request, body).body().string(), this.getClass());
        Tuple<Schedule,String> tuple = new Tuple<>();
        tuple.setFirst(schedule).setSecond(data);
        return tuple;
    }


    /**
     * 修改日程
     *
     * @param bot         传入机器人对象
     * @param channel_id  子频道id
     * @param schedule_id 日程ID
     * @param schedule    修改后的日程对象，不带id
     * @return 返回日程对象
     */
    @SneakyThrows
    @Override
    public Tuple<Schedule,String> modifySchedule(Bot bot, String channel_id, String schedule_id, Schedule schedule) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/schedules/" + schedule_id).build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("schedule", schedule);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        String data = SiriusHttpUtils.patchRequest(bot, request, body).body().string();
        Tuple<Schedule,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }

    public enum REMIND_TYPE {
        /**
         * 不提醒
         */
        NO_REMIND("0"),

        /**
         * 开始时提醒
         */
        START_REMIND("1"),

        /**
         * 开始前五分钟提醒
         */
        FIVE_MINUTE_REMIND("2"),

        /**
         * 开始前十五分钟提醒
         */
        FIFTEEN_MINUTE_REMIND("3"),

        /**
         * 开始前三十分钟提醒
         */
        THIRTY_MINUTE_REMIND("4"),

        /**
         * 开始前六十分钟提醒
         */
        SIXTY_MINUTE_REMIND("5");
        String value;

        REMIND_TYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

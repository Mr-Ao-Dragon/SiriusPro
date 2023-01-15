package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.api.ScheduleApi;
import cn.siriusbot.siriuspro.bot.api.pojo.Schedule;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class  ScheduleApiImpl implements ScheduleApi {

    @Autowired
    BotManager botManager;

    /**
     * 获取日程列表
     * 用于获取channel_id指定的子频道中当天的日程列表。
     * 若带了参数 since，则返回在 since 对应当天的日程列表；若未带参数 since，则默认返回今天的日程列表
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @param since      起始时间戳(ms)
     * @return 返回日程列表
     */
    @SneakyThrows
    @Override
    public Tuple<List<Schedule>, String> getScheduleListByChannel_id(String bot_id, String channel_id, String since) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request;
        if (since != null) {
            request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/schedules?since=" + since).build();
        } else {
            request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/schedules").build();
        }

        String data = SiriusHttpUtils.getRequest(siriusBotClient, request).body().string();
        data = EmojiParser.parseToUnicode(data);
        Tuple<List<Schedule>, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, List.class)).setSecond(data);
        return tuple;
    }


    /**
     * 获取日程详情
     * 获取日程子频道 channel_id 下 schedule_id 指定的的日程的详情。
     *
     * @param bot_id         传入机器人对象ID
     * @param channel_id  子频道ID
     * @param schedule_id 日程ID
     * @return 返回日程对象
     */
    @SneakyThrows
    @Override
    public Tuple<Schedule, String> getScheduleInfo(String bot_id, String channel_id, String schedule_id) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/schedules/" + schedule_id).build();
        String data = SiriusHttpUtils.getRequest(siriusBotClient, request).body().string();
        data = EmojiParser.parseToUnicode(data);
        Tuple<Schedule, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Schedule.class)).setSecond(data);
        return tuple;
    }


    /**
     * 删除日程
     *
     * @param bot_id,传入机器人对象ID
     * @param channel_id  子频道ID
     * @param schedule_id 日程ID
     * @return 返回操作结果
     */
    @Override
    public Boolean deleteSchedule(String bot_id, String channel_id, String schedule_id) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/schedules/" + schedule_id).build();
        return SiriusHttpUtils.deleteRequest(siriusBotClient, request, null).code() == 204;
    }


    /**
     * 在指定日程子频道创建一个日程
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @param schedule   不带id的日程对象
     * @return 返回日程对象
     */
    @SneakyThrows
    @Override
    public Tuple<Schedule,String> createSchedule(String bot_id, String channel_id, Schedule schedule) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/schedules").build();
        JSONObject json = new JSONObject();
        json.put("schedule", schedule);
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        String data = SiriusHttpUtils.postRequest(siriusBotClient, request, body).body().string();
        Schedule sc = JSONObject.parseObject(data, Schedule.class);
        Tuple<Schedule,String> tuple = new Tuple<>();
        tuple.setFirst(schedule).setSecond(data);
        return tuple;
    }


    /**
     * 修改日程
     *
     * @param bot_id         传入机器人对象ID
     * @param channel_id  子频道id
     * @param schedule_id 日程ID
     * @param schedule    修改后的日程对象，不带id
     * @return 返回日程对象
     */
    @SneakyThrows
    @Override
    public Tuple<Schedule,String> modifySchedule(String bot_id, String channel_id, String schedule_id, Schedule schedule) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/schedules/" + schedule_id).build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("schedule", schedule);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        String data = SiriusHttpUtils.patchRequest(siriusBotClient, request, body).body().string();
        Tuple<Schedule,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Schedule.class)).setSecond(data);
        return tuple;
    }
}
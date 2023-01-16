package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.api.ScheduleApi;
import cn.siriusbot.siriuspro.bot.api.pojo.Schedule;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
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
public class ScheduleApiImpl implements ScheduleApi {

    @Autowired
    BotManager botManager;

    @Autowired
    BotPool botPool;

    /**
     * 获取日程列表
     * 用于获取channel_id指定的子频道中当天的日程列表。
     * 若带了参数 since，则返回在 since 对应当天的日程列表；若未带参数 since，则默认返回今天的日程列表
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param since      起始时间戳(ms)
     * @return 返回日程列表
     */
    @SneakyThrows
    @Override
    public Tuple<List<Schedule>, String> getScheduleListByChannel_id(String bot_id, String channel_id, String since) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET);
        if (since != null) {
            botRequest = botRequest.setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/schedules?since=" + since);
        } else {
            botRequest = botRequest.setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/schedules");
        }
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<List<Schedule>, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, List.class)).setSecond(data);
        return tuple;
    }


    /**
     * 获取日程详情
     * 获取日程子频道 channel_id 下 schedule_id 指定的的日程的详情。
     *
     * @param bot_id      传入机器人对象ID
     * @param channel_id  子频道ID
     * @param schedule_id 日程ID
     * @return 返回日程对象
     */
    @SneakyThrows
    @Override
    public Tuple<Schedule, String> getScheduleInfo(String bot_id, String channel_id, String schedule_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "schedules/" + schedule_id)
                .setMethod(RequestMethod.GET);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        data = EmojiParser.parseToUnicode(data);
        Tuple<Schedule, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Schedule.class)).setSecond(data);
        return tuple;
    }


    /**
     * 删除日程
     *
     * @param bot_id,传入机器人对象ID
     * @param channel_id       子频道ID
     * @param schedule_id      日程ID
     * @return 返回操作结果
     */
    @Override
    public Boolean deleteSchedule(String bot_id, String channel_id, String schedule_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.DELETE)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/schedules/" + schedule_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }


    /**
     * 在指定日程子频道创建一个日程
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param schedule   不带id的日程对象
     * @return 返回日程对象
     */
    @SneakyThrows
    @Override
    public Tuple<Schedule, String> createSchedule(String bot_id, String channel_id, Schedule schedule) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl("channels/" + channel_id + "/schedules")
                .setMethod(RequestMethod.POST)
                .putRequestBody("schedule", schedule);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Schedule sc = JSONObject.parseObject(data, Schedule.class);
        Tuple<Schedule, String> tuple = new Tuple<>();
        tuple.setFirst(schedule).setSecond(data);
        return tuple;
    }


    /**
     * 修改日程
     *
     * @param bot_id      传入机器人对象ID
     * @param channel_id  子频道id
     * @param schedule_id 日程ID
     * @param schedule    修改后的日程对象，不带id
     * @return 返回日程对象
     */
    @SneakyThrows
    @Override
    public Tuple<Schedule, String> modifySchedule(String bot_id, String channel_id, String schedule_id, Schedule schedule) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.PATCH)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/schedules/" + schedule_id)
                .putRequestBody("schedule", schedule);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<Schedule, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Schedule.class)).setSecond(data);
        return tuple;
    }
}

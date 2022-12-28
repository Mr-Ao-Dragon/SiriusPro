package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.UserApi;
import cn.siriusbot.siriuspro.entity.pojo.Guild;
import cn.siriusbot.siriuspro.entity.pojo.User;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;

import java.util.List;

public class UserApiImpl implements UserApi {
    /**
     * 获取机器人基本信息
     * @return 返回Bot(机器人)对象
     */

    @SneakyThrows
    @Override
    public Tuple<User,String> getRobotInfo(Bot bot) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "users/@me").build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();
        User user = JSONObject.parseObject(data, User.class);
        user.setBot(true);
        Tuple<User,String> tuple = new Tuple<>();
        tuple.setFirst(user).setSecond(data);
        return tuple;
    }


    /**
     * 获取频道指定机器人频道列表
     * @param bot 传入机器人对象
     * @param before 读此 guild id 之前的数据
     * @param after 读此 guild id 之后的数据
     * @param limit 每次查询的条数，默认100，最大100
     * @return 频道数组
     * after 和 before 同时设置时， after 参数无效
     */
    @SneakyThrows
    @Override
    public Tuple<List<Guild>,String>  getGuildList(Bot bot, String before, String after, int limit) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl()).build();
        String path = bot.getOpenUrl() + "users/@me/guilds";
        try {
            if (before != null) {
                request = request.newBuilder().url(path + "?before=" + before + "&limit=" + limit).build();
            } else if (after != null) {
                request = request.newBuilder().url(path + "?after=" + after + "&limit=" + limit).build();
            } else if (after == null && before == null) {
                request = request.newBuilder().url(path + "?limit=" + limit).build();
            }
        } catch (Exception e) {
            request = request.newBuilder().url(path + "?limit=" + limit).build();
        }
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();
        List<Guild> guildList = JSONObject.parseObject(data, List.class);
        Tuple<List<Guild>,String> tuple = new Tuple<>();
        tuple.setFirst(guildList);
        tuple.setSecond(data);
        return tuple;
    }
}

package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.api.UserApi;
import cn.siriusbot.siriuspro.bot.api.pojo.Guild;
import cn.siriusbot.siriuspro.bot.api.pojo.User;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserApiImpl implements UserApi {

    @Autowired
    BotPool botPool;

    /**
     * 获取机器人基本信息
     *
     * @return 返回Bot(机器人)对象
     */

    @SneakyThrows
    @Override
    public Tuple<User, String> getRobotInfo(@NotNull String bot_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET)
                .setUrl(client.getSession().getOpenUrl() + "users/@me");
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = response.getBody();
        User user = JSONObject.parseObject(data, User.class);
        user.setBot(true);
        Tuple<User, String> tuple = new Tuple<>();
        tuple.setFirst(user).setSecond(data);
        return tuple;
    }


    /**
     * 获取频道指定机器人频道列表
     *
     * @param bot_id 传入机器人对象ID
     * @param before 读此 guild id 之前的数据
     * @param after  读此 guild id 之后的数据
     * @param limit  每次查询的条数，默认100，最大100
     * @return 频道数组
     * after 和 before   同时设置时， after 参数无效
     */
    @SneakyThrows
    @Override
    public Tuple<List<Guild>, String> getGuildList(@NotNull String bot_id, String before, String after, int limit) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET);
        String path = client.getSession().getOpenUrl() + "users/@me/guilds";
        try {
            if (before != null) {
                botRequest = botRequest.setUrl(path + "?before=" + before + "&limit=" + limit);
            } else if (after != null) {
                botRequest = botRequest.setUrl(path + "?after=" + after + "&limit=" + limit);
            } else if (after == null && before == null) {
                botRequest = botRequest.setUrl(path + "?limit=" + limit);
            }
        } catch (Exception e) {
            botRequest = botRequest.setUrl(path + "?limit=" + limit);
        }
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        List<Guild> guildList = JSONObject.parseObject(data, List.class);
        Tuple<List<Guild>, String> tuple = new Tuple<>();
        tuple.setFirst(guildList);
        tuple.setSecond(data);
        return tuple;
    }
}

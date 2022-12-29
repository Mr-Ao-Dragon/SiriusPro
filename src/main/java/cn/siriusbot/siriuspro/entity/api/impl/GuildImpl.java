package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.GuildApi;
import cn.siriusbot.siriuspro.entity.pojo.Guild;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;

public class GuildImpl implements GuildApi {
    /**
     * 获取频道详情
     * @param bot_id 传入机器人对象ID
     * @param guild_id 频道ID
     * @return 返回频道详情对象
     */
    @SneakyThrows
    public Tuple<Guild,String> getGuildInfo(String bot_id, String guild_id) {
        Bot bot = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(bot.getOpenUrl()+"guilds/"+guild_id).build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data;
        data = response.body().string();
        Tuple<Guild,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data,Guild.class)).setSecond(data);
        return tuple;
    }

}

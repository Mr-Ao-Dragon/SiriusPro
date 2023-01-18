package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.api.GuildApi;
import cn.siriusbot.siriuspro.bot.api.pojo.Guild;
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

@Component
public class GuildImpl implements GuildApi {

    @Autowired
    BotPool botPool;

    /**
     * 获取频道详情
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @return 返回频道详情对象
     */
    @SneakyThrows
    public Tuple<Guild, String> getGuildInfo(@NotNull String bot_id, @NotNull String guild_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<Guild, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Guild.class)).setSecond(data);
        return tuple;
    }

}

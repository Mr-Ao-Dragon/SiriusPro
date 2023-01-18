package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.api.MessageSettingApi;
import cn.siriusbot.siriuspro.bot.api.pojo.MessageSetting;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSettingImpl implements MessageSettingApi {

    @Autowired
    BotPool botPool;

    /**
     * 获取频道消息频率设置信息
     * 用于获取机器人在频道 guild_id 内的消息频率设置。
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @return 返回频道消息频率设置对象
     */
    @SneakyThrows
    @Override
    public Tuple<MessageSetting, String> getMessageSettingInfo(@NotNull String bot_id, @NotNull String guild_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/message/setting");
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        Tuple<MessageSetting, String> tuple = new Tuple<>();
        String data = response.getBody();
        tuple.setFirst(JSONObject.parseObject(data, MessageSetting.class)).setSecond(data);
        return tuple;
    }
}

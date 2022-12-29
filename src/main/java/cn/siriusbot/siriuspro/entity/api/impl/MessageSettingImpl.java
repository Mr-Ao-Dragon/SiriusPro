package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.MessageSettingApi;
import cn.siriusbot.siriuspro.entity.pojo.MessageSetting;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;

public class MessageSettingImpl implements MessageSettingApi {
    /**
     * 获取频道消息频率设置信息
     * 用于获取机器人在频道 guild_id 内的消息频率设置。
     *
     * @param bot_id      传入机器人对象ID
     * @param guild_id 频道ID
     * @return 返回频道消息频率设置对象
     */
    @SneakyThrows
    @Override
    public Tuple<MessageSetting,String> getMessageSettingInfo(String bot_id, String guild_id) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl()+"guilds/"+guild_id+"/message/setting").build();
        Response response = SiriusHttpUtils.getRequest(siriusBotClient, request);
        Tuple<MessageSetting,String> tuple = new Tuple<>();
        String data = response.body().string();
        tuple.setFirst(JSONObject.parseObject(data,MessageSetting.class)).setSecond(data);
        return tuple;
    }
}

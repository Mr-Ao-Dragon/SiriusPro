package cn.siriusbot.siriuspro.entity.impl;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.MessageSettingApi;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.Request;
import okhttp3.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class MessageSetting implements MessageSettingApi {

    /**
     * 是否允许创建私信
     */
    private Boolean disable_create_dm;

    /**
     * 是否允许发主动消息
     */
    private Boolean disable_push_msg;

    /**
     * 子频道ID数组
     */
    private List<String> channel_ids;

    /**
     * 每个子频道允许发送主动推送消息最大消息数
     */
    private Integer channel_push_max_num;


    /**
     * 获取频道消息频率设置信息
     * 用于获取机器人在频道 guild_id 内的消息频率设置。
     *
     * @param bot      传入机器人对象
     * @param guild_id 频道ID
     * @return 返回频道消息频率设置对象
     */
    @SneakyThrows
    @Override
    public Map<MessageSetting,Object> getMessageSettingInfo(Bot bot, String guild_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl()+"guilds/"+guild_id+"/message/setting").build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        Map<MessageSetting,Object> map = new HashMap<>();
        String data = response.body().string();
        map.put(JSONObject.parseObject(data,this.getClass()),data);
        return map;
    }
}

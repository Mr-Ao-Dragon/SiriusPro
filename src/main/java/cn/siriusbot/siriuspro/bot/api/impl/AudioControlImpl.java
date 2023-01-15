package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.api.AudioApi;
import cn.siriusbot.siriuspro.bot.api.pojo.audio.AudioControl;
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
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AudioControlImpl implements AudioApi {

    @Autowired
    BotManager botManager;

    @Autowired
    BotPool botPool;

    /**
     * 音频控制 Api
     * 频接口：仅限音频类机器人才能使用，后续会根据机器人类型自动开通接口权限，现如需调用，需联系平台申请权限。
     *
     * @param bot_id       机器人对象
     * @param channel_id   子频道ID
     * @param audioControl 音频控制对象
     * @return 操作结果
     */
    @SneakyThrows
    @Override
    public Boolean audioControl(String bot_id, String channel_id, AudioControl audioControl) {
        BotClient client = botPool.getBotById(bot_id);
        audioControl.setText(EmojiParser.parseToUnicode(audioControl.getText()));
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/audio")
                .setMethod(RequestMethod.POST)
                .putRequestBody("audio_url", audioControl.getAudio_url())
                .putRequestBody("text", audioControl.getText())
                .putRequestBody("status", audioControl.getStatus());
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 200;
    }

    /**
     * 机器人上麦
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @return 返回操作结果
     */
    @SneakyThrows
    @Override
    public Boolean singStart(String bot_id, String channel_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/mic")
                .setMethod(RequestMethod.PUT);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 200;
    }

    /**
     * 机器人下麦
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @return 操作结果
     */
    @SneakyThrows
    @Override
    public Boolean singEnd(String bot_id, String channel_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.DELETE)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/mic");
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 200;
    }
}

package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.AudioApi;
import cn.siriusbot.siriuspro.entity.pojo.audio.AudioControl;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Component;


import org.springframework.stereotype.Component;
@Component
public class  AudioControlImpl implements AudioApi {
    /**
     * 音频控制 Api
     * 频接口：仅限音频类机器人才能使用，后续会根据机器人类型自动开通接口权限，现如需调用，需联系平台申请权限。
     *
     * @param bot_id          机器人对象
     * @param channel_id   子频道ID
     * @param audioControl 音频控制对象
     * @return 操作结果
     */
    @SneakyThrows
    @Override
    public Boolean audioControl(String bot_id, String channel_id, AudioControl audioControl) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl()+"channels/"+channel_id+"/audio").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("audio_url",audioControl.getAudio_url());
        json.put("text",audioControl.getText());
        json.put("status",audioControl.getStatus());
        RequestBody body = RequestBody.create(mediaType,json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(siriusBotClient, request, body);
        System.out.println(response.body().string());
        return false;
    }

    /**
     * 机器人上麦
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @return 返回操作结果
     */
    @SneakyThrows
    @Override
    public Boolean singStart(String bot_id, String channel_id) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl()+"channels/"+channel_id+"/mic").build();
        Response response = SiriusHttpUtils.putRequest(siriusBotClient, request, RequestBody.create(MediaType.parse("text/plain;application/json"), ""));
        return  response.code()==200;
    }

    /**
     * 机器人下麦
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @return 操作结果
     */
    @SneakyThrows
    @Override
    public Boolean singEnd(String bot_id, String channel_id) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl()+"channels/"+channel_id+"/mic").build();
        Response response = SiriusHttpUtils.deleteRequest(siriusBotClient, request,null);
        return  response.code()==200;
    }
}

package cn.siriusbot.siriuspro.entity.pojo.audio;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.AudioApi;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 语音对象
 */
@Data
@Accessors(chain = true)
public class AudioControl implements AudioApi {
    /**
     * 音频数据的url,status为0时传
     */
    private String audio_url;

    /**
     * 状态文本，比如(简单爱-周杰伦),可选,status为0时传，其他操作不传
     */
    private String text;

    /**
     * 播放主题，参考枚举STATUS
     */
    private Integer status;

    /**
     * 音频控制 Api
     * 频接口：仅限音频类机器人才能使用，后续会根据机器人类型自动开通接口权限，现如需调用，需联系平台申请权限。
     *
     * @param bot          机器人对象
     * @param channel_id   子频道ID
     * @param audioControl 音频控制对象
     * @return 操作结果
     */
    @SneakyThrows
    @Override
    public Boolean audioControl(Bot bot, String channel_id, AudioControl audioControl) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl()+"channels/"+channel_id+"/audio").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("audio_url",audioControl.getAudio_url());
        json.put("text",audioControl.getText());
        json.put("status",audioControl.getStatus());
        RequestBody body = RequestBody.create(mediaType,json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        System.out.println(response.body().string());
        return false;
    }

    /**
     * 机器人上麦
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @return 返回操作结果
     */
    @SneakyThrows
    @Override
    public Boolean singStart(Bot bot, String channel_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl()+"channels/"+channel_id+"/mic").build();
        Response response = SiriusHttpUtils.putRequest(bot, request, RequestBody.create(MediaType.parse("text/plain;application/json"), ""));
        return  response.code()==200;
    }

    /**
     * 机器人下麦
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @return 操作结果
     */
    @SneakyThrows
    @Override
    public Boolean singEnd(Bot bot, String channel_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl()+"channels/"+channel_id+"/mic").build();
        Response response = SiriusHttpUtils.deleteRequest(bot, request,null);
        return  response.code()==200;
    }

    public enum STATUS{
        /**
         * 开始播放操作
         */
        START(0),

        /**
         * 暂停播放操作
         */
        PAUSE(1),

        /**
         * 继续播放操作
         */
        RESUME(2),

        /**
         * 停止播放操作
         */
        STOP(3);

        private Integer value;
        STATUS(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}

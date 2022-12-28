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
public class AudioControl  {
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

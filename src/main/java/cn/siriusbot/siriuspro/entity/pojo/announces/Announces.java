package cn.siriusbot.siriuspro.entity.pojo.announces;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.AnnouncesApi;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.List;

@Data
@Accessors(chain = true)
public class Announces{
    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 子频道ID
     */
    private String channel_id;

    /**
     * 消息ID
     */
    private String message_id;

    /**
     * 公告类别
     */

    private Integer announces_type;

    /**
     * 推荐子频道详情列表
     */
    private List<RecommendChannel> recommend_channels;




    public enum ANNOUNCES_TYPE {


        /**
         * 成员公告
         */
        MEMBER(0),

        /**
         * 欢迎公告
         */
        WELCOME(1),
        ;

        private Integer value;

        ANNOUNCES_TYPE(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}

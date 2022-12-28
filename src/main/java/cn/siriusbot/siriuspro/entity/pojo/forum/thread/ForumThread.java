package cn.siriusbot.siriuspro.entity.pojo.forum.thread;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.ForumApi;
import cn.siriusbot.siriuspro.entity.pojo.forum.responseObj.ThreadList;
import cn.siriusbot.siriuspro.entity.pojo.forum.responseObj.createThread;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 主题对象
 * <p>
 * 话题频道内发表的主贴称为主题
 * 该事件在话题频道内新发表主题或删除时生产事件中包含该对象
 */
@Data
@Accessors(chain = true)
public class ForumThread {

    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 子频道ID
     */
    private String channel_id;

    /**
     * 作者ID
     */
    private String author_id;

    /**
     * 主贴内容
     */
    ForumThreadInfo thread_info;


    /**
     * 帖子文本格式
     */
    public enum FORMAT {
        /**
         * 普通文本
         */
        FORMAT_TEXT(1),

        /**
         * HTML
         */
        FORMAT_HTML(2),

        /**
         * Markdown
         */
        FORMAT_MARKDOWN(3),
        /**
         * JSON （传值可参考 richObject下的RichText）
         */
        FORMAT_JSON(4);

        private Integer value;

        FORMAT(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    @Override
    public String toString() {
        return "ForumThread{" +
                "guild_id='" + guild_id + '\'' +
                ", channel_id='" + channel_id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", thread_info=" + thread_info +
                '}';
    }
}

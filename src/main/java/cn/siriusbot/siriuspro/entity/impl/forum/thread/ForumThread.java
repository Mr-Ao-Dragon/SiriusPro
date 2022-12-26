package cn.siriusbot.siriuspro.entity.impl.forum.thread;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.ForumApi;
import cn.siriusbot.siriuspro.entity.impl.forum.responseObj.ThreadList;
import cn.siriusbot.siriuspro.entity.impl.forum.responseObj.createThread;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 主题对象
 * <p>
 * 话题频道内发表的主贴称为主题
 * 该事件在话题频道内新发表主题或删除时生产事件中包含该对象
 */
@Data
@Accessors(chain = true)
public class ForumThread implements ForumApi {

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
     * 获取指定论坛子频道的帖子列表
     * 仅私域可用
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @return 帖子列表对象
     */
    @SneakyThrows
    @Override
    public Map<ThreadList, Object> getThreadsByChannelId(Bot bot, String channel_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/threads").build();
        String data = SiriusHttpUtils.getRequest(bot, request).body().string();
        ThreadList threadList = JSONObject.parseObject(data, ThreadList.class);
        Map<ThreadList,Object> map = new HashMap<>();
        map.put(threadList,data);
        return map;
    }

    /**
     * 获取子频道详情
     * 该接口用于获取子频道下的帖子列表。
     * 仅私域可用
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param thread_id  帖子ID
     * @return 帖子详情对象
     */
    @SneakyThrows
    @Override
    public Map<ForumThread,Object> getThreadInfo(Bot bot, String channel_id, String thread_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/threads/" + thread_id).build();
        String data = SiriusHttpUtils.getRequest(bot, request).body().string();
        JSONObject json = JSONObject.parseObject(data);
        ForumThread forumThread = json.getObject("thread", this.getClass());
        Map<ForumThread,Object> map = new HashMap<>();
        map.put(forumThread,data);
        return map;
    }

    /**
     * 发表帖子
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param title      帖子标题
     * @param content    帖子内容
     * @param format     解析模式，参考Forum下ForumThread的FORMAT枚举类型
     * @return 返回发表帖子响应对象
     */
    @SneakyThrows
    @Override
    public Map<createThread,Object> postThread(Bot bot, String channel_id, String title, String content, Integer format) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/threads").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("content", content);
        json.put("format", format);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        String data = SiriusHttpUtils.putRequest(bot, request, body).body().string();
        Map<createThread,Object> map = new HashMap<>();
        map.put(JSONObject.parseObject(data, createThread.class),data);
        return map;
    }

    /**
     * 删除帖子
     * 仅私域可用
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param thread_id  帖子ID
     * @return 操作结果
     */
    @Override
    public Boolean deleteThread(Bot bot, String channel_id, String thread_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/threads/" + thread_id).build();
        return SiriusHttpUtils.deleteRequest(bot, request, null).code() == 204;
    }

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

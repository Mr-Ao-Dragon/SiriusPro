package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.ForumApi;
import cn.siriusbot.siriuspro.entity.pojo.forum.responseObj.ThreadList;
import cn.siriusbot.siriuspro.entity.pojo.forum.responseObj.createThread;
import cn.siriusbot.siriuspro.entity.pojo.forum.thread.ForumThread;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ForumThreadImpl implements ForumApi {
    /**
     * 获取指定论坛子频道的帖子列表
     * 仅私域可用
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @return 帖子列表对象
     */
    @SneakyThrows
    @Override
    public Tuple<ThreadList,String> getThreadsByChannelId(String bot_id, String channel_id) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/threads").build();
        String data = SiriusHttpUtils.getRequest(siriusBotClient, request).body().string();
        ThreadList threadList = JSONObject.parseObject(data, ThreadList.class);
        Tuple<ThreadList,String> tuple = new Tuple<>();
        tuple.setFirst(threadList).setSecond(data);
        return tuple;
    }

    /**
     * 获取子频道详情
     * 该接口用于获取子频道下的帖子列表。
     * 仅私域可用
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @param thread_id  帖子ID
     * @return 帖子详情对象
     */
    @SneakyThrows
    @Override
    public Tuple<ForumThread,String> getThreadInfo(String bot_id, String channel_id, String thread_id) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/threads/" + thread_id).build();
        String data = SiriusHttpUtils.getRequest(siriusBotClient, request).body().string();
        JSONObject json = JSONObject.parseObject(data);
        ForumThread forumThread = json.getObject("thread", ForumThread.class);
        Tuple<ForumThread,String> tuple = new Tuple<>();
        tuple.setFirst(forumThread).setSecond(data);
        return tuple;
    }

    /**
     * 发表帖子
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @param title      帖子标题
     * @param content    帖子内容
     * @param format     解析模式，参考Forum下ForumThread的FORMAT枚举类型
     * @return 返回发表帖子响应对象
     */
    @SneakyThrows
    @Override
    public Tuple<createThread,String> postThread(String bot_id, String channel_id, String title, String content, Integer format) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/threads").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("content", content);
        json.put("format", format);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        String data = SiriusHttpUtils.putRequest(siriusBotClient, request, body).body().string();
        Tuple<createThread,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, createThread.class)).setSecond(data);
        return tuple;
    }

    /**
     * 删除帖子
     * 仅私域可用
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @param thread_id  帖子ID
     * @return 操作结果
     */
    @Override
    public Boolean deleteThread(String bot_id, String channel_id, String thread_id) {
        SiriusBotClient siriusBotClient = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "channels/" + channel_id + "/threads/" + thread_id).build();
        return SiriusHttpUtils.deleteRequest(siriusBotClient, request, null).code() == 204;
    }

}

package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.api.ForumApi;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.responseObj.CreateThread;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.responseObj.ThreadList;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.thread.ForumThread;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ForumThreadImpl implements ForumApi {

    @Autowired
    BotPool botPool;

    /**
     * 获取指定论坛子频道帖子列表
     * 仅私域可用
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @return 帖子详情对象
     */
    @SneakyThrows
    @Override
    public Tuple<ThreadList, String> getThreadsByChannelId(@NotNull String bot_id, @NotNull String channel_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/threads")
                .setMethod(RequestMethod.GET);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        ThreadList threadList = JSONObject.parseObject(data, ThreadList.class);
        Tuple<ThreadList, String> tuple = new Tuple<>();
        tuple.setFirst(threadList).setSecond(data);
        return tuple;
    }

    /**
     * 获取子频道帖子详情
     * 仅私域可用
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param thread_id  帖子ID
     * @return 帖子详情对象
     */
    @SneakyThrows
    @Override
    public Tuple<ForumThread, String> getThreadInfo(@NotNull String bot_id, @NotNull String channel_id, @NotNull String thread_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/threads/" + thread_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        JSONObject json = JSONObject.parseObject(data);
        ForumThread forumThread = json.getObject("thread", ForumThread.class);
        Tuple<ForumThread, String> tuple = new Tuple<>();
        tuple.setFirst(forumThread).setSecond(data);
        return tuple;
    }

    /**
     * 发表帖子
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param title      帖子标题
     * @param content    帖子内容
     * @param format     解析模式，参考Forum下ForumThread的FORMAT枚举类型
     * @return 返回发表帖子响应对象
     */
    @SneakyThrows
    @Override
    public Tuple<CreateThread, String> postThread(@NotNull String bot_id, @NotNull String channel_id, @NotNull String title, @NotNull String content, @NotNull Integer format) {
        BotClient client = botPool.getBotById(bot_id);
        content = EmojiParser.parseToUnicode(content);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/threads")
                .setMethod(RequestMethod.POST)
                .putRequestBody("content", content)
                .putRequestBody("format", format);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<CreateThread, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, CreateThread.class)).setSecond(data);
        return tuple;
    }

    /**
     * 删除帖子
     * 仅私域可用
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param thread_id  帖子ID
     * @return 操作结果
     */
    @Override
    public Boolean deleteThread(@NotNull String bot_id, @NotNull String channel_id, @NotNull String thread_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/threads/" + thread_id)
                .setMethod(RequestMethod.DELETE);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }

}

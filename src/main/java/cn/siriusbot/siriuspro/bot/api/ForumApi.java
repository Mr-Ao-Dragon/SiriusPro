package cn.siriusbot.siriuspro.bot.api;

import cn.siriusbot.siriuspro.bot.annotation.EDoc;
import cn.siriusbot.siriuspro.bot.annotation.EName;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.responseObj.CreateThread;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.responseObj.ThreadList;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.thread.ForumThread;
import cn.siriusbot.siriuspro.bot.api.proxy.ApiProxy;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

/**
 * 论坛消息Api
 */
public interface ForumApi extends ApiProxy {

    /**
     * 获取指定论坛子频道帖子列表
     * 仅私域可用
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @return 帖子详情对象
     */
    @EName(name = "获取帖子列表")
    Tuple<ThreadList, String> getThreadsByChannelId
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id
    );

    /**
     * 获取子频道帖子详情
     * 仅私域可用
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param thread_id  帖子ID
     * @return 帖子详情对象
     */
    @EName(name = "获取帖子详情")
    Tuple<ForumThread, String> getThreadInfo
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "帖子ID")
            @NonNull @ENonNull String thread_id);

    /**
     * 发表帖子
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param title      帖子标题
     * @param content    帖子内容
     * @param format     解析模式，参考Forum下Thread的FORMAT枚举类型
     * @return 返回发表帖子响应对象
     */
    @EName(name = "发表帖子")
    Tuple<CreateThread, String> postThread
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "帖子标题")
            @NonNull @ENonNull String title,

            @EDoc(doc = "帖子内容")
            @NonNull @ENonNull String content,

            @EDoc(doc = "解析模式")
            @NonNull @ENonNull Integer format
    );

    /**
     * 删除帖子
     * 仅私域可用
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param thread_id  帖子ID
     * @return 操作结果
     */
    @EName(name = "删除帖子")
    Boolean deleteThread
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "帖子ID")
            @NonNull @ENonNull String thread_id
    );
}

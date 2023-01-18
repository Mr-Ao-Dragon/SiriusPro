package cn.siriusbot.siriuspro.bot.api;


import cn.siriusbot.siriuspro.bot.annotation.EDoc;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.announces.Announces;
import cn.siriusbot.siriuspro.bot.api.pojo.announces.RecommendChannel;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

import java.util.List;

/**
 * 公告消息Api
 */
public interface AnnouncesApi {


    /**
     * 创建频道公告
     * 用于创建频道全局公告，公告类型分为 消息类型的频道公告 和 推荐子频道类型的频道公告 。
     * 当请求参数 message_id 有值时，优先创建消息类型的频道公告， 消息类型的频道公告只能创建成员公告类型的频道公告。
     * 创建推荐子频道类型的频道全局公告请将 message_id 设置为空，并设置对应的 announces_type 和 recommend_channels 请求参数，会一次全部替换推荐子频道公司。
     * 推荐子频道和消息类型全局公告不能同时存在，会互相顶替设置。
     * 同频道内推荐子频道最多只能创建 3 条。
     * 只有子频道权限为全体成员可见才可设置为推荐子频道。
     * 删除推荐子频道类型的频道公告请使用 删除频道公告,并将 message_id 设置为 all。
     *
     * @param bot_id     传入机器人ID
     * @param guild_id   频道ID
     * @param message_id 消息ID
     * @param channel_id 子频道ID
     * @return 返回公告对象
     */
    Tuple<Announces, String> createGuildAnnounces
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "消息ID")
            @NonNull @ENonNull String message_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id
    );

    /**
     * 删除频道公告
     * 用于删除频道 guild_id 下指定 message_id 的全局公告。
     * message_id 有值时，会校验 message_id 合法性，若不校验校验 message_id，请将 message_id 设置为 all
     *
     * @param bot_id     传入机器人ID
     * @param guild_id   频道ID
     * @param message_id 消息ID
     * @return 返回删除结果
     */
    Boolean deleteAnnouncesByGuildId
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "消息ID")
            @NonNull @ENonNull
            String message_id
    );

    /**
     * 创建频道推荐子频道列表
     *
     * @param bot_id            传入机器人ID
     * @param guild_id          频道ID
     * @param announces_type    公告类型
     * @param recommendChannels 子频道推荐列表
     * @return 返回公告对象
     */
    Tuple<Announces, String> createGuildRecommend_Channels
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "公告类型")
            @NonNull @ENonNull Integer announces_type,

            @EDoc(doc = "子频道推荐列表对象数组")
            @NonNull @ENonNull
            List<RecommendChannel> recommendChannels
    );
}

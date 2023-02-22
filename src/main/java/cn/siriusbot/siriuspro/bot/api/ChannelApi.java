package cn.siriusbot.siriuspro.bot.api;

import cn.siriusbot.siriuspro.bot.annotation.EDoc;
import cn.siriusbot.siriuspro.bot.annotation.EName;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.Channel;
import cn.siriusbot.siriuspro.bot.api.proxy.ApiProxy;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

import java.util.List;

/**
 * 子频道Api
 */
public interface ChannelApi extends ApiProxy {

    /**
     * 获取子频道列表
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @return 返回存放子频道的容器对象
     */
    @EName(name = "获取子频道列表")
    Tuple<List<Channel>, String> getChannelList
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id
    );


    /**
     * 获取子频道详情
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @return 返回子频道对象
     */
    @EName(name = "获取子频道详情")
    Tuple<Channel, String> getChannelInfo
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @ENonNull @NonNull String channel_id
    );

    /**
     * 创建子频道
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param channel  子频道对象
     * @return 返回子频道对象
     */
    @EName(name = "创建子频道")
    Tuple<Channel, String> createChannel
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "子频道对象")
            @NonNull @ENonNull
            Channel channel
    );

    /**
     * 修改子频道
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道id
     * @param channel    修改后的子频道对象
     * @return 修改后的子频道对象
     */
    @EName(name = "修改子频道")
    Tuple<Channel, String> modifyChannel
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道对象")
            @NonNull @ENonNull String channel_id,
            Channel channel
    );

    /**
     * 删除子频道
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @return 删除结果
     */

    @EName(name = "删除子频道")
    Boolean deleteChannel
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id
    );

    /**
     * 获取当前音视频/直播子频道的在线成员数
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @return 在线人数
     */
    @EName(name = "获取音视频或直播子频道在线人数")
    Integer getOnlineMemberNumber
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id);
}

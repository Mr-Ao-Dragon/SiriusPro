package cn.siriusbot.siriuspro.bot.api;

import cn.siriusbot.siriuspro.bot.api.pojo.Channel;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;

import java.util.List;

/**
 * 子频道Api
 */
public interface ChannelApi {

    /**
     * 获取子频道列表
     * @param bot_id 传入机器人ID
     * @param guild_id 频道ID
     * @return 返回存放子频道的容器对象
     */
    public abstract Tuple<List<Channel>,String> getChannelList(String bot_id, String guild_id);


    /**
     * 获取子频道详情
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @return 返回子频道对象
     */
    public abstract Tuple<Channel,String> getChannelInfo(String bot_id,String channel_id);

    /**
     * 创建子频道
     * @param bot_id 传入机器人ID
     * @param guild_id 频道ID
     * @param channel 子频道对象
     * @return 返回子频道对象
     */
    public abstract Tuple<Channel,String> createChannel(String bot_id,String guild_id,Channel channel);

    /**
     * 修改子频道
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道id
     * @param channel 修改后的子频道对象
     * @return 修改后的子频道对象
     */
    public abstract Tuple<Channel,String>  modifyChannel(String bot_id, String channel_id, Channel channel);

    /**
     * 删除子频道
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @return 删除结果
     */
    public abstract Boolean deleteChannel(String bot_id,String channel_id);

    /**
     * 获取当前音视频/直播子频道的在线成员数
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @return 在线人数
     */
    public abstract Integer getOnlineMemberNumber(String bot_id,String channel_id);
}

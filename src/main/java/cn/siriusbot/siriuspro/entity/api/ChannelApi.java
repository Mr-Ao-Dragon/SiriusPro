package cn.siriusbot.siriuspro.entity.api;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.entity.impl.Channel;

import java.util.List;
import java.util.Map;

/**
 * 子频道Api
 */
public interface ChannelApi {

    /**
     * 获取子频道列表
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @return 返回存放子频道的容器对象
     */
    public abstract Map<List<Channel>,Object> getChannelList(Bot bot, String guild_id);


    /**
     * 获取子频道详情
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @return 返回子频道对象
     */
    public abstract Map<Channel,Object> getChannelInfo(Bot bot,String channel_id);

    /**
     * 创建子频道
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @param channel 子频道对象
     * @return 返回子频道对象
     */
    public abstract Map<Channel,Object> createChannel(Bot bot,String guild_id,Channel channel);

    /**
     * 修改子频道
     * @param bot 传入机器人对象
     * @param channel_id 子频道id
     * @param channel 修改后的子频道对象
     * @return 修改后的子频道对象
     */
    public abstract Map<Channel,Object> modifyChannel(Bot bot, String channel_id, Channel channel);

    /**
     * 删除子频道
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @return 删除结果
     */
    public abstract Boolean deleteChannel(Bot bot,String channel_id);

    /**
     * 获取当前音视频/直播子频道的在线成员数
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @return 在线人数
     */
    public abstract Integer getOnlineMemberNumber(Bot bot,String channel_id);
}

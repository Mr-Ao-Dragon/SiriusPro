package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.bot.api.ChannelApi;
import cn.siriusbot.siriuspro.bot.api.pojo.Channel;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/channel")
public class ChannelApiController {

    @Autowired
    ChannelApi channelApi;

    /**
     * 获取子频道列表
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @return 返回存放子频道的容器对象
     */
    @GetMapping("/get-channels/{bot_id}/{guild_id}")
    public R getChannelList(@PathVariable("bot_id") String bot_id,@PathVariable("guild_id") String guild_id) {
        try {
            return new R().setData(channelApi.getChannelList(bot_id, guild_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 获取子频道详情
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @return 返回子频道对象
     */
    @GetMapping("/get-channel-info/{bot_id}/{channel_id}")
    public R getChannelInfo(@PathVariable String bot_id, @PathVariable String channel_id) {
        try {
            return new R().setData(channelApi.getChannelInfo(bot_id, channel_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 创建子频道
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param channel  子频道对象
     * @return 返回子频道对象
     */
    @PostMapping("/create-channel/{bot_id}/{guild_id}")
    public R createChannel(@PathVariable String bot_id, @PathVariable String guild_id, @RequestBody Channel channel) {
        try {
            return new R().setData(channelApi.createChannel(bot_id, guild_id, channel));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 修改子频道
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道id
     * @param channel    修改后的子频道对象
     * @return 修改后的子频道对象
     */
    @PatchMapping("/modify-channel/{bot_id}/{channel_id}")
    public R modifyChannel(@PathVariable String bot_id, @PathVariable String channel_id, @RequestBody Channel channel) {
        try {
            return new R().setData(channelApi.modifyChannel(bot_id, channel_id, channel));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 删除子频道
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @return 删除结果
     */
    @DeleteMapping("/delete-channel/{bot_id}/{channel_id}")
    public R deleteChannel(@PathVariable String bot_id, @PathVariable String channel_id) {
        try {
            return new R().setData(channelApi.deleteChannel(bot_id, channel_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");
        }
    }

    /**
     * 获取当前音视频/直播子频道的在线成员数
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @return 在线人数
     */
    @GetMapping("/get-online/{bot_id}/{channel_id}")
    public R getOnline(@PathVariable String bot_id, @PathVariable String channel_id) {
        try {
            return new R().setData(channelApi.getOnlineMemberNumber(bot_id, channel_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }
}



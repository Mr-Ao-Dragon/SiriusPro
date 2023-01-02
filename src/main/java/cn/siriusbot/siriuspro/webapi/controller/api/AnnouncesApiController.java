package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.entity.api.AnnouncesApi;
import cn.siriusbot.siriuspro.entity.pojo.announces.Announces;
import cn.siriusbot.siriuspro.entity.pojo.announces.RecommendChannel;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announces")
public class AnnouncesApiController {

    @Autowired
    AnnouncesApi announcesApi;

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
     * @param bot_id               传入机器人对象ID
     * @param guild_id          频道ID
     * @param message_id        消息ID
     * @param channel_id        子频道ID
     * @return 返回公告对象
     */
    @PostMapping("/create-guild-announces/{bot_id}")
    public R createGuildAnnounces( @PathVariable("bot_id") String bot_id, @RequestParam("guild_id") String guild_id, @RequestParam("message_id") String message_id, @RequestParam("channel_id") String channel_id) {
        try {
            Tuple<Announces, String> reply = announcesApi.createGuildAnnounces(bot_id, guild_id, message_id, channel_id);
            return new R()
                    .setData(reply);
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R()
                    .setCode(500)
                    .setMsg("error");
        }

    }


    /**
     * 删除频道公告
     * 用于删除频道 guild_id 下指定 message_id 的全局公告。
     * message_id 有值时，会校验 message_id 合法性，若不校验校验 message_id，请将 message_id 设置为 all
     *
     * @param bot_id        传入机器人对象ID
     * @param guild_id   频道ID
     * @param message_id 消息ID
     * @return 返回删除结果
     */

    @DeleteMapping("/delete-guild-announces/{bot_id}")
    public R deleteGuildAnnounces(@PathVariable("bot_id")String bot_id, String guild_id, String message_id){
        try {
            return new R()
                    .setData(announcesApi.deleteAnnouncesByGuildId(bot_id,guild_id,message_id).booleanValue());
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R()
                    .setCode(500)
                    .setMsg("error");
        }
    }

    @PostMapping("/create-guild-recommend-channels/{bot_id}")
    public R createGuildRecommendChannels(@PathVariable("bot_id") String bot_id, String guild_id, Integer announces_type, List<RecommendChannel> recommendChannels){
    return null;
    }
}

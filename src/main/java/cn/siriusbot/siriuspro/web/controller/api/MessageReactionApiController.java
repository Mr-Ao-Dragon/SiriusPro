package cn.siriusbot.siriuspro.web.controller.api;

import cn.siriusbot.siriuspro.bot.api.MessageReactionApi;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.web.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

/**
 * 表情表态Api
 */
@RestController
@RequestMapping("/api/messageReaction")
public class MessageReactionApiController {


    @Autowired
    MessageReactionApi messageReactionApi;

    /**
     * 拉取表情表态用户列表
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型
     * @param id         表情ID
     * @param cookie     分页参数
     * @param limit      每次拉取数量，默认20，最多50，只在第一次请求设置
     * @return 拉取表情表态响应对象
     */
    @GetMapping("/get-reaction-users/{bot_id}/{channel_id}")
    public R getReactionUsers(@PathVariable String bot_id, @PathVariable String channel_id, @RequestParam String message_id, @RequestParam Integer type, @RequestParam String id, @Nullable @RequestParam String cookie, @Nullable @RequestParam Integer limit) {
        try {
            if(limit==null)
                limit=20;
            return new R().setData(messageReactionApi.getReactionUsers(bot_id, channel_id, message_id, type, id, cookie, limit));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }


    /**
     * 删除机器人对指定消息的表态
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    @DeleteMapping("/delete-reaction/{bot_id}/{channel_id}")
    public R deleteReactionForMessageId(@PathVariable String bot_id, @PathVariable String channel_id, @RequestParam String message_id, @RequestParam Integer type, String id) {
        try {
            return new R().setData(messageReactionApi.deleteReactionForMessageId(bot_id, channel_id, message_id, type, id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 发表表情表态
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param type       表情类型，参考EmojiType
     * @param id         表情ID，参考Emoji列表
     * @return 操作结果
     */
    @PutMapping("/add-reaction/{bot_id}/{channel_id}")
    public R addReaction(@PathVariable String bot_id, @PathVariable String channel_id, @RequestParam String message_id, @RequestParam Integer type, @RequestParam String id) {
        try {
            return new R().setData(messageReactionApi.addReaction(bot_id, channel_id, message_id, type, id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }
}

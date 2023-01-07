package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.entity.api.DMS_Api;
import cn.siriusbot.siriuspro.entity.pojo.message.MessageReference;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 私信Api
 */
@RestController
@RequestMapping("/api/dms")
public class DirectApiController {

    @Autowired
    DMS_Api dmsApi;

    /**
     * 创建私信会话
     * 机器人和用户存在共同频道才能创建私信会话。
     * 创建成功后，返回创建成功的频道 id ，子频道 id 和创建时间。
     *
     * @param bot_id 传入机器人ID
     * @param json   请求体
     * @return 私信会话对象
     */
    @PostMapping("/create-dms/{bot_id}")
    public R createDMS(@PathVariable String bot_id, @RequestBody JSONObject json) {
        try {
            String recipient_id = json.getString("recipient_id");
            String source_guild_id = json.getString("source_guild_id");
            return new R().setData(dmsApi.createDMS(bot_id, recipient_id, source_guild_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 发送普通私信消息
     * 用于向 guild_id 指定的私信会话发送普通私信消息。
     * 被动回复消息有效期为 5 分钟。超时会报错。
     * 私信的 guild_id 在创建私信会话时以及私信消息事件中获取。
     * 私信场景下，每个机器人每天可以对一个用户发 2 条主动消息。
     * 私信场景下，每个机器人每天累计可以发 200 条主动消息。
     * 私信场景下，被动消息没有条数限制
     * 传入msg_id或event_id其一，此条消息视为被动消息
     *
     * @param bot_id 传入机器人ID
     * @return 消息对象
     */
    @PostMapping("/sendMessage/{bot_id}")
    public R sendMessage(@PathVariable String bot_id, @RequestBody JSONObject json) {
        try {
            String guild_id = json.getString("guild_id");
            String content = json.getString("content");
            String image_url = json.getString("image_url");
            String msg_id = json.getString("msg_id");
            String event_id = json.getString("event_id");
            return new R().setData(dmsApi.sendMessage(bot_id, guild_id, content, image_url, msg_id, event_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 发送私信引用消息
     * 用于向 guild_id 指定的私信会话发送引用私信消息。
     * 被动回复消息有效期为 5 分钟。超时会报错。
     * 私信的 guild_id 在创建私信会话时以及私信消息事件中获取。
     * 私信场景下，每个机器人每天可以对一个用户发 2 条主动消息。
     * 私信场景下，每个机器人每天累计可以发 200 条主动消息。
     * 私信场景下，被动消息没有条数限制
     * 传入msg_id或event_id其一，此条消息视为被动消息
     *
     * @param bot_id 传入机器人ID
     * @param json   请求体
     * @return 返回消息对象
     */
    @PostMapping("/sendReference/{bot_id}")
    public R sendReference(@PathVariable String bot_id, @RequestBody JSONObject json) {
        try {
            String guild_id = json.getString("guild_id");
            String content = json.getString("content");
            MessageReference reference = json.getObject("reference", MessageReference.class);
            return new R().setData(dmsApi.sendReferenceMessage(bot_id, guild_id, content, reference));
        }
        catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setMsg("error").setCode(500);
        }
    }

}

package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.bot.api.NoSpeakApi;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 禁言Api
 */
@RestController
@RequestMapping("/api/noSpeak")
public class NoSpeakApiController {
    @Autowired
    NoSpeakApi noSpeakApi;

    /**
     * 禁言指定成员
     *
     * @param bot_id             传入机器人ID
     * @param guild_id           频道ID
     * @param user_id            用户ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds       禁言秒数
     * @return 返回禁言结果
     */

    @PatchMapping("/no-speak/{bot_id}/{guild_id}/{user_id}")
    public R noSpeakByUser_id(@PathVariable String bot_id, @PathVariable String guild_id, @PathVariable String user_id, @Nullable @RequestParam String mute_end_timestamp, @Nullable @RequestParam String mute_seconds) {
        try {
            return new R().setData(noSpeakApi.noSpeakByUser_id(bot_id, guild_id, user_id, mute_end_timestamp, mute_seconds).booleanValue());
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");
        }
    }

    /**
     * 批量禁言成员
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param json     请求体对象
     * @return 返回禁言成员对象
     */
    @PatchMapping("/no-speak-users/{bot_id}/{guild_id}")
    public R noSpeakByUser_ids(@PathVariable String bot_id, @PathVariable String guild_id, @RequestBody JSONObject json) {
        try {
            List user_ids = json.getObject("user_ids", List.class);
            String mute_end_timestamp = json.getString("mute_end_timestamp");
            String mute_seconds = json.getString("mute_seconds");
            return new R().setData(noSpeakApi.noSpeakByUser_ids(bot_id, guild_id, user_ids, mute_end_timestamp, mute_seconds));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");
        }
    }

    /**
     * 全员禁言
     *
     * @param bot_id             传入机器人ID
     * @param mute_end_timestamp 禁言到期时间戳
     * @param mute_seconds       禁言秒数
     * @return 返回禁言结果
     */
    @PatchMapping("/no-speak-all/{bot_id}/{guild_id}")
    public R nodeSpeakAll(@PathVariable String bot_id, @PathVariable String guild_id, @Nullable @RequestParam String mute_end_timestamp,@Nullable @RequestParam String mute_seconds) {
        try {
            return new R().setData(noSpeakApi.nodeSpeakAll(bot_id, guild_id, mute_end_timestamp, mute_seconds).booleanValue());
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");
        }
    }
}

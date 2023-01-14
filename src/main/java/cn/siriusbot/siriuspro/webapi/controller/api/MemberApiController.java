package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.bot.api.MemberApi;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

/**
 * 成员Api
 */
@RestController()
@RequestMapping("/api/member")
public class MemberApiController {
    @Autowired
    MemberApi memberApi;

    /**
     * 获取频道成员列表
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param after    上一次回包中最后一个member的user id， 如果是第一次请求填 0，默认为 0
     * @param limit    查询条数
     * @return 返回成员列表 分页大小，1-400，默认是 1。成员较多的频道尽量使用较大的limit值，以减少请求数
     */
    @GetMapping("/get-members/{bot_id}/{guild_id}")
    public R getMemberList(@PathVariable String bot_id, @PathVariable String guild_id, @RequestParam @Nullable String after, @RequestParam @Nullable Integer limit) {
        try {
            return new R().setData(memberApi.getMemberList(bot_id, guild_id, after, limit));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 获取成员详情
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param user_id  用户ID
     * @return 返回成员对象
     */
    @GetMapping("/get-member-info/{bot_id}/{guild_id}/{user_id}")
    public R getMemberInfo(@PathVariable String bot_id, @PathVariable String guild_id, @PathVariable String user_id) {
        try {
            return new R().setData(memberApi.getMemberInfo(bot_id, guild_id, user_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 获取拥有此身份组的成员列表
     *
     * @param bot_id      传入机器人ID
     * @param guild_id    频道ID
     * @param role_id     身份组ID
     * @param start_index 上一次返回包中的next，第一次请求填0，默认0
     * @return 返回持有指定身份组ID的成员列表
     */
    @GetMapping("/get-role-member/{bot_id}/{guild_id}/{role_id}")
    public R getMemberListByRoleId(@PathVariable String bot_id, @PathVariable String guild_id, @PathVariable String role_id, @Nullable @RequestParam String start_index, @Nullable @RequestParam Integer limit) {
        try {
            if (start_index == null)
                start_index = "0";
            if (limit == null)
                limit = 0;

            return new R().setData(memberApi.getMemberListByRoleId(bot_id, guild_id, role_id, start_index, limit));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            e.printStackTrace();
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 将指定成员从频道内移除
     *
     * @param bot_id                  传入机器人ID
     * @param user_id                 用户ID
     * @param guild_id                频道ID
     * @param add_black               添加到黑名单
     * @param delete_history_msg_days 撤回消息的天数
     * @return 移除结果
     */
    @DeleteMapping("/delete-member/{bot_id}/{guild_id}/{user_id}")
    public R deleteMemberByUserId(@PathVariable String bot_id, @PathVariable String guild_id, @PathVariable String user_id, @RequestParam @Nullable Boolean add_black, @Nullable @RequestParam Integer delete_history_msg_days) {
        try {
            if (add_black == null)
                add_black = false;
            if (delete_history_msg_days == null)
                delete_history_msg_days = -1;

            return new R().setData(memberApi.deleteMemberByUserId(bot_id, guild_id, user_id, add_black, delete_history_msg_days));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }

    }

}

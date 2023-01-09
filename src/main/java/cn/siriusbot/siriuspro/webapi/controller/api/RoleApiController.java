package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.entity.api.RoleApi;
import cn.siriusbot.siriuspro.entity.pojo.Channel;
import cn.siriusbot.siriuspro.entity.pojo.role.GuildRoleList;
import cn.siriusbot.siriuspro.entity.pojo.role.NewRole;
import cn.siriusbot.siriuspro.entity.pojo.role.Role;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 身份组Api
 */
@RestController
@RequestMapping("/api/role")
public class RoleApiController {
    @Autowired
    RoleApi roleApi;

    /**
     * 创建频道身份组
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param json     请求体对象
     * @return 返回身份组对象
     */
    @PostMapping("/create-role/{bot_id}/{guild_id}")
    public R createRole(@PathVariable String bot_id, @PathVariable String guild_id, @RequestBody JSONObject json) {
        try {
            String name = json.getString("name");
            Integer color = json.getInteger("color");
            Integer hoist = json.getInteger("hoist");
            return new R().setData(roleApi.createRole(bot_id, guild_id, name, color, hoist));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 将指定用户，从指定频道的身份组中移除
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @param user_id  用户ID
     * @param channel  只传入子频道ID的子频道对象
     * @return 返回操作结果
     */
    @DeleteMapping("/delete-role-member/{bot_id}/{guild_id}/{role_id}/{user_id}")
    public R removeRoleMemberForGuild(@PathVariable String bot_id, @PathVariable String guild_id, @PathVariable String role_id, @PathVariable String user_id, @RequestBody Channel channel) {
        try {
            return new R().setData(roleApi.removeRoleMemberForGuild(bot_id, guild_id, role_id, user_id, channel).booleanValue());
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 将指定成员，加入到指定频道的，指定身份组中
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param user_id  用户ID
     * @param role_id  身份组ID
     * @param channel  只传入了子频道ID的子频道对象
     * @return 操作结果
     */
    @PutMapping("/create-role-member/{bot_id}/{guild_id}/{user_id}/{role_id}")
    public R createRoleMemberInGuild(@PathVariable String bot_id, @PathVariable String guild_id, @PathVariable String user_id, @PathVariable String role_id, @RequestBody Channel channel) {
        try {
            return new R().setData(roleApi.createRoleMemberInGuild(bot_id,guild_id,user_id,role_id,channel).booleanValue());
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 修改频道身份组
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @param json     请求体对象
     * @return 修改后的身份组信息
     */
    @PatchMapping("/modify-role/{bot_id}/{guild_id}/{role_id}")
    public R modifyRoleByGuild(@PathVariable String bot_id, @PathVariable String guild_id, @PathVariable String role_id, @RequestBody JSONObject json) {
        try {
            String name = json.getString("name");
            Integer color = json.getInteger("color");
            Integer hoist = json.getInteger("hoist");
            return new R().setData(roleApi.modifyRoleByGuild(bot_id, guild_id, role_id, name, color, hoist));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 从指定频道中删除指定身份组
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @return 操作结果
     */
    @DeleteMapping("/delete-role/{bot_id}/{guild_id}/{role_id}")
    public R deleteRoleForGuild(@PathVariable String bot_id, @PathVariable String guild_id, @PathVariable String role_id) {
        try {
            return new R().setData(roleApi.deleteRoleForGuild(bot_id, guild_id, role_id).booleanValue());
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 从指定频道中获取所有身份组
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @return 身份组列表
     */
    @GetMapping("/get-roles/{bot_id}/{guild_id}")
    public R getRoleListByGuild(@PathVariable String bot_id, @PathVariable String guild_id) {
        try {
            return new R().setData(roleApi.getRoleListByGuild(bot_id, guild_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }
}

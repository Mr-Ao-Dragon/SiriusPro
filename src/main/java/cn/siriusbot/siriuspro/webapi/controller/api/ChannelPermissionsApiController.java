package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.entity.api.ChannelPermissionsApi;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 子频道权限Api
 */
@RestController()
@RequestMapping("/api/channelPermissions")
public class ChannelPermissionsApiController {


    @Autowired
    ChannelPermissionsApi channelPermissionsApi;
    /**
     * 获取子频道用户权限
     * 用于获取 子频道channel_id 下用户 user_id 的权限。
     * 获取子频道用户权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param user_id    用户ID
     * @return 子频道权限对象
     */
    @GetMapping("/get-channel-user-permissions/{bot_id}/{channel_id}/{user_id}")
    public R getChannelUserPermissions(@PathVariable String bot_id, @PathVariable String channel_id, @PathVariable String user_id) {
        try {
            return new R().setData(channelPermissionsApi.getChannelPermissionsByUser_id(bot_id, channel_id, user_id));
        }catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setCode(500).setMsg("error");
        }
    }



    /**
     * 修改指定用户在指定子频道的权限
     * 用于获取 子频道channel_id 下用户 user_id 的权限。
     * 获取子频道用户权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员
     *
     * @param bot_id        传入机器人ID
     * @param channel_id 子频道ID
     * @param user_id    用户id
     * @param add        添加的权限
     * @param remove     移除的权限
     * @return 返回修改结果
     */
    @PutMapping("/modify-user-channel-permissions/{bot_id}/{channel_id}/user_id/{add}/{remove}")
    public R modifyUserChannelPermissions (@PathVariable String bot_id, @PathVariable String channel_id, @PathVariable String user_id,@PathVariable String add,@PathVariable String remove){
        try{
            return new R().setData(channelPermissionsApi.modifyChannelPermissionsByUser_id(bot_id, channel_id, user_id, add, remove));
        }
        catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setCode(500).setMsg("error");
        }
    }


    /**
     * 获取指定身份组在指定子频道的权限
     * 用于获取子频道 channel_id 下身份组 role_id 的权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员。
     *
     * @param bot_id        传入机器人ID
     * @param channel_id 子频道ID
     * @param role_id    身份组ID
     * @return 子频道权限对象
     */
    @GetMapping("/get-channel-role-permissions/{bot_id}/{channel_id}/{role_id}")
    public R getChannelRolePermissions(@PathVariable String bot_id,@PathVariable String channel_id,@PathVariable String role_id){
        try {
            return new R().setData(channelPermissionsApi.getChannelPermissionsByRole_id(bot_id, channel_id, role_id));
        }
        catch (MsgException e){
            return  e.getR();
        }
        catch (Exception e){
            return new R().setCode(500).setMsg("error");
        }
    }

    /**
     * 修改子频道身份组权限
     * 用于获取 子频道channel_id 下用户 user_id 的权限。
     * 获取子频道用户权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员
     *
     * @param bot_id        传入机器人ID
     * @param channel_id 子频道ID
     * @param role_id    身份组ID
     * @param add        要添加的权限
     * @param remove     要移除的权限
     * @return 修改结果
     */
    @PutMapping("/modify-role-channel-permissions/{bot_id}/{channel_id}/{role_id}/{add}/{remove}")
    public R modifyRoleChannelPermissions(@PathVariable String bot_id,@PathVariable String channel_id,@PathVariable String role_id,@PathVariable String add,@PathVariable String remove){
        try
        {
            return new R().setData(channelPermissionsApi.modifyChannelPermissionsByRole_id(bot_id, channel_id, role_id, add, remove));
        }
        catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setMsg("error").setCode(500);
        }
    }
}

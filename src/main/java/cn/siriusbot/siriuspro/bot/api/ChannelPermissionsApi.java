package cn.siriusbot.siriuspro.bot.api;


import cn.siriusbot.siriuspro.bot.annotation.EDoc;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.ChannelPermissions;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

/**
 * 子频道权限接口
 */
public interface ChannelPermissionsApi {

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
    @EDoc(doc = "获取子频道用户权限")
    Tuple<ChannelPermissions, String> getChannelPermissionsByUser_id
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "用户ID")
            @NonNull @ENonNull String user_id);

    /**
     * 修改子频道身份组权限
     * 用于获取 子频道channel_id 下用户 user_id 的权限。
     * 获取子频道用户权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param role_id    身份组ID
     * @param add        要添加的权限
     * @param remove     要移除的权限
     * @return 修改结果
     */
    @EDoc(doc = "修改子频道身份组权限")
    Boolean modifyChannelPermissionsByRole_id
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "身份组ID")
            @NonNull @ENonNull String role_id,

            @EDoc(doc = "要添加的权限")
            String add,

            @EDoc(doc = "要移除的权限")
            String remove
    );

    /**
     * 修改指定用户在指定子频道的权限
     * 用于获取 子频道channel_id 下用户 user_id 的权限。
     * 获取子频道用户权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param user_id    用户id
     * @param add        添加的权限
     * @param remove     移除的权限
     * @return 返回修改结果
     */
    @EDoc(doc = "修改子频道用户权限")
    Boolean modifyChannelPermissionsByUser_id
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "用户ID")
            @NonNull @ENonNull String user_id,

            @EDoc(doc = "要添加的权限")
            String add,

            @EDoc(doc = "要移除的权限")
            String remove
    );

    /**
     * 获取指定身份组在指定子频道的权限
     * 用于获取子频道 channel_id 下身份组 role_id 的权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员。
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param role_id    身份组ID
     * @return 子频道权限对象
     */
    @EDoc(doc = "获取子频道身份组权限")
    Tuple<ChannelPermissions, String> getChannelPermissionsByRole_id
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "身份组ID")
            @NonNull @ENonNull String role_id
    );
}

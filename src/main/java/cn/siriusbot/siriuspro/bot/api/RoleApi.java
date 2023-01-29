package cn.siriusbot.siriuspro.bot.api;


import cn.siriusbot.siriuspro.bot.annotation.EDoc;
import cn.siriusbot.siriuspro.bot.annotation.EName;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.Channel;
import cn.siriusbot.siriuspro.bot.api.pojo.role.GuildRoleList;
import cn.siriusbot.siriuspro.bot.api.pojo.role.NewRole;
import cn.siriusbot.siriuspro.bot.api.pojo.role.Role;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

/**
 * 身份组Api
 */
public interface RoleApi {

    /**
     * 创建频道身份组
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param name     身份组名称
     * @param color    身份组颜色
     * @param hoist    是否在成员列表中单独展示,0:否,1:是
     * @return 返回身份组对象
     */
    @EName(name = "创建身份组")
    Tuple<Role, String> createRole
    (

            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "身份组名称")
            @NonNull @ENonNull String name,

            @EDoc(doc = "身份组10进制颜色")
            Integer color,

            @EDoc(doc = "是否再成员列表中单独显示")
            Integer hoist
    );

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
    @EName(name = "移除身份组成员")
    Boolean removeRoleMemberForGuild
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "身份组ID")
            @NonNull @ENonNull String role_id,

            @EDoc(doc = "用户ID")
            @NonNull @ENonNull String user_id,

            @EDoc(doc = "子频道对象")
            @NonNull @ENonNull Channel channel
    );

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
    @EName(name = "创建频道身份组成员")
    Boolean createRoleMemberInGuild
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "用户ID")
            @NonNull @ENonNull String user_id,

            @EDoc(doc = "身份组ID")
            @NonNull @ENonNull String role_id,

            @EDoc(doc = "只传入带ID的子频道对象")
            @NonNull @ENonNull Channel channel
    );

    /**
     * 修改频道身份组
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @param name     最新身份组名称
     * @param color    最新颜色
     * @param hoist    是否在成员列表中单独展示,0:否,1:是
     * @return 修改后的身份组信息
     */
    @EName(name = "修改身份组")
    Tuple<NewRole, String> modifyRoleByGuild
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "身份组ID")
            @NonNull @ENonNull String role_id,

            @EDoc(doc = "身份组名称")
            String name,

            @EDoc(doc = "身份组十六进制颜色")
            Integer color,

            @EDoc(doc = "是否单独展示")
            Integer hoist
    );

    /**
     * 从指定频道中删除指定身份组
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @return 操作结果
     */
    @EName(name = "删除身份组")
    Boolean deleteRoleForGuild
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "身份组ID")
            @NonNull @ENonNull String role_id
    );

    /**
     * 从指定频道中获取所有身份组
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @return 身份组列表
     */
    @EName(name = "获取身份组列表")
    Tuple<GuildRoleList, String> getRoleListByGuild
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id
    );
}

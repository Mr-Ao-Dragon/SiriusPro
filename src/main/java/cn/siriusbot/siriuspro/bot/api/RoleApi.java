package cn.siriusbot.siriuspro.bot.api;


import cn.siriusbot.siriuspro.bot.api.pojo.Channel;
import cn.siriusbot.siriuspro.bot.api.pojo.role.GuildRoleList;
import cn.siriusbot.siriuspro.bot.api.pojo.role.NewRole;
import cn.siriusbot.siriuspro.bot.api.pojo.role.Role;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;

/**
 * 身份组Api
 */
public interface RoleApi {

    /**
     * 创建频道身份组
     *
     * @param bot_id      传入机器人ID
     * @param guild_id 频道ID
     * @param name     身份组名称
     * @param color    身份组颜色
     * @param hoist    是否在成员列表中单独展示,0:否,1:是
     * @return 返回身份组对象
     */
    public abstract Tuple<Role, String> createRole(String bot_id, String guild_id, String name, Integer color, Integer hoist);

    /**
     * 将指定用户，从指定频道的身份组中移除
     *
     * @param bot_id      传入机器人ID
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @param user_id  用户ID
     * @param channel  只传入子频道ID的子频道对象
     * @return 返回操作结果
     */
    public abstract Boolean removeRoleMemberForGuild(String bot_id, String guild_id, String role_id, String user_id, Channel channel);

    /**
     * 将指定成员，加入到指定频道的，指定身份组中
     *
     * @param bot_id      传入机器人ID
     * @param guild_id 频道ID
     * @param user_id 用户ID
     * @param role_id  身份组ID
     * @param channel  只传入了子频道ID的子频道对象
     * @return 操作结果
     */
    public abstract Boolean createRoleMemberInGuild(String bot_id, String guild_id,String user_id, String role_id, Channel channel);

    /**
     * 修改频道身份组
     *
     * @param bot_id      传入机器人ID
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @param name     最新身份组名称
     * @param color    最新颜色
     * @param hoist    是否在成员列表中单独展示,0:否,1:是
     * @return 修改后的身份组信息
     */
    public abstract Tuple<NewRole,String> modifyRoleByGuild(String bot_id, String guild_id, String role_id, String name, Integer color, Integer hoist);

    /**
     * 从指定频道中删除指定身份组
     *
     * @param bot_id      传入机器人ID
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @return 操作结果
     */
    public abstract Boolean deleteRoleForGuild(String bot_id, String guild_id, String role_id);

    /**
     * 从指定频道中获取所有身份组
     *
     * @param bot_id      传入机器人ID
     * @param guild_id 频道ID
     * @return 身份组列表
     */
    public abstract Tuple<GuildRoleList,String> getRoleListByGuild(String bot_id, String guild_id);
}

package cn.siriusbot.siriuspro.entity.pojo.role;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.RoleApi;
import cn.siriusbot.siriuspro.entity.pojo.Channel;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Data
@Accessors(chain = true)
public class Role implements RoleApi {
    /**
     * 身份组ID
     */
    private String id;

    /**
     * 身份组名称
     */
    private String name;

    /**
     * 身份组颜色-ARGB的HEX十六进制颜色值转换后的十进制数值
     */
    private Long color;

    /**
     * 身份组人数
     */
    private Integer number;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", number=" + number +
                ", hoist=" + hoist +
                ", member_limit=" + member_limit +
                '}';
    }

    /**
     * 是否在成员列表中单独展示: 0-否, 1-是
     */
    private Integer hoist;
    /**
     * 身份组人数上限
     */
    private Integer member_limit;

    /**
     * 创建频道身份组
     *
     * @param bot      传入机器人对象
     * @param guild_id 频道ID
     * @param name     身份组名称
     * @param color    身份组颜色
     * @param hoist    是否在成员列表中单独展示,0:否,1:是
     * @return 返回身份组对象
     */
    @SneakyThrows
    @Override
    public Tuple<Role, String> createRole(Bot bot, String guild_id, String name, Integer color, Integer hoist) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/roles").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("color", color);
        json.put("hoist", hoist);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        JSONObject roleObject = JSONObject.parseObject(data);
        Role role = roleObject.getJSONObject("role").toJavaObject(this.getClass());
        Tuple<Role, String> tuple = new Tuple<>();
        tuple.setFirst(role).setSecond(data);
        return tuple;
    }

    /**
     * 将指定用户，从指定频道的身份组中移除
     *
     * @param bot      传入机器人对象
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @param user_id  用户ID
     * @param channel  只传入子频道ID的子频道对象
     * @return 返回操作结果
     */
    @Override
    public Boolean removeRoleMemberForGuild(Bot bot, String guild_id, String role_id, String user_id, Channel channel) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id + "/roles/" + role_id).build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        RequestBody body = RequestBody.create(mediaType, JSONObject.toJSONString(channel));
        Response response = SiriusHttpUtils.deleteRequest(bot, request, body);
        return response.code() == 204;
    }

    /**
     * 将指定成员，加入到指定频道的，指定身份组中
     *
     * @param bot      传入机器人对象
     * @param guild_id 频道ID
     * @param user_id  用户ID
     * @param role_id  身份组ID
     * @param channel  只传入了子频道ID的子频道对象
     * @return 操作结果
     */
    @SneakyThrows
    @Override
    public Boolean createRoleMemberInGuild(Bot bot, String guild_id, String user_id, String role_id, Channel channel) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id + "/roles/" + role_id).build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        RequestBody body = RequestBody.create(mediaType, JSONObject.toJSONString(channel));
        Response response = SiriusHttpUtils.putRequest(bot, request, body);
        return response.code() == 204;
    }

    /**
     * 修改频道身份组
     *
     * @param bot      传入机器人对象
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @param name     最新身份组名称
     * @param color    最新颜色
     * @param hoist    是否在成员列表中单独展示,0:否,1:是
     * @return 修改后的身份组信息
     */
    @SneakyThrows
    @Override
    public Tuple<NewRole,String> modifyRoleByGuild(Bot bot, String guild_id, String role_id, String name, Integer color, Integer hoist) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/roles/" + role_id).build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("color", color);
        json.put("hoist", hoist);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.patchRequest(bot, request, body);
        String data = response.body().string();
        NewRole newRole = JSONObject.parseObject(data, NewRole.class);
        Tuple<NewRole,String> tuple = new Tuple<>();
        tuple.setFirst(newRole).setSecond(data);
        return tuple;
    }

    /**
     * 从指定频道中删除指定身份组
     *
     * @param bot      传入机器人对象
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @return 操作结果
     */
    @Override
    public Boolean deleteRoleForGuild(Bot bot, String guild_id, String role_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/roles/" + role_id).build();
        Response response = SiriusHttpUtils.deleteRequest(bot, request, null);
        return response.code() == 204;
    }

    /**
     * 从指定频道中获取所有身份组
     *
     * @param bot      传入机器人对象
     * @param guild_id 频道ID
     * @return 身份组列表
     */
    @SneakyThrows
    @Override
    public Tuple<GuildRoleList,String>  getRoleListByGuild(Bot bot, String guild_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/roles").build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();
        GuildRoleList guildRoleList = JSONObject.parseObject(response.body().string(), GuildRoleList.class);
        Tuple<GuildRoleList,String> tuple =new Tuple<>();
        tuple.setFirst(guildRoleList).setSecond(data);
        return tuple;
    }

    /**
     * 系统默认生成下列身份组
     */
    public enum DEFAULT_ROLE_IDS {

        /**
         * 全体成员
         */
        ALL(1),
        /**
         * 管理员
         */
        MANAGER(2),
        /**
         * 频道主
         */
        ADMIN(4),
        /**
         * 子频道管理员
         */
        CHANNEL_MANAGER(5);
        private int value;

        DEFAULT_ROLE_IDS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}

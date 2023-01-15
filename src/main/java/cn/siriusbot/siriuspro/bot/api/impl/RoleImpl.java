package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.api.RoleApi;
import cn.siriusbot.siriuspro.bot.api.pojo.Channel;
import cn.siriusbot.siriuspro.bot.api.pojo.role.GuildRoleList;
import cn.siriusbot.siriuspro.bot.api.pojo.role.NewRole;
import cn.siriusbot.siriuspro.bot.api.pojo.role.Role;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.event.v1.BotEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleImpl implements RoleApi {

    @Autowired
    BotManager botManager;

    @Autowired
    BotPool botPool;

    /**
     * 创建频道身份组
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @param name     身份组名称
     * @param color    身份组颜色
     * @param hoist    是否在成员列表中单独展示,0:否,1:是
     * @return 返回身份组对象
     */
    @SneakyThrows
    @Override
    public Tuple<Role, String> createRole(String bot_id, String guild_id, String name, Integer color, Integer hoist) {
        BotClient client = botPool.getBotById(bot_id);
        name = EmojiParser.parseToUnicode(name);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.POST)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/roles")
                .putRequestBody("name", name)
                .putRequestBody("color", color)
                .putRequestBody("hoist", hoist);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = response.getBody();
        data = EmojiParser.parseToUnicode(data);
        JSONObject roleObject = JSONObject.parseObject(data);
        Role role = roleObject.getJSONObject("role").toJavaObject(Role.class);
        Tuple<Role, String> tuple = new Tuple<>();
        tuple.setFirst(role).setSecond(data);
        return tuple;
    }

    /**
     * 将指定用户，从指定频道的身份组中移除
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @param user_id  用户ID
     * @param channel  只传入子频道ID的子频道对象
     * @return 返回操作结果
     */
    @Override
    public Boolean removeRoleMemberForGuild(String bot_id, String guild_id, String role_id, String user_id, Channel channel) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id + "/roles/" + role_id)
                .setMethod(RequestMethod.DELETE);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }

    /**
     * 将指定成员，加入到指定频道的，指定身份组中
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @param user_id  用户ID
     * @param role_id  身份组ID
     * @param channel  只传入了子频道ID的子频道对象
     * @return 操作结果
     */
    @SneakyThrows
    @Override
    public Boolean createRoleMemberInGuild(String bot_id, String guild_id, String user_id, String role_id, Channel channel) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.POST)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id + "/roles/" + role_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }

    /**
     * 修改频道身份组
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @param name     最新身份组名称
     * @param color    最新颜色
     * @param hoist    是否在成员列表中单独展示,0:否,1:是
     * @return 修改后的身份组信息
     */
    @SneakyThrows
    @Override
    public Tuple<NewRole, String> modifyRoleByGuild(String bot_id, String guild_id, String role_id, String name, Integer color, Integer hoist) {
        BotClient client = botPool.getBotById(bot_id);
        name = EmojiParser.parseToUnicode(name);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.PATCH)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/roles/" + role_id)
                .putRequestBody("name", name)
                .putRequestBody("color", color)
                .putRequestBody("hoist", hoist);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);

        String data = EmojiParser.parseToUnicode(response.getBody());
        NewRole newRole = JSONObject.parseObject(data, NewRole.class);
        Tuple<NewRole, String> tuple = new Tuple<>();
        tuple.setFirst(newRole).setSecond(data);
        return tuple;
    }

    /**
     * 从指定频道中删除指定身份组
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @param role_id  身份组ID
     * @return 操作结果
     */
    @Override
    public Boolean deleteRoleForGuild(String bot_id, String guild_id, String role_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.DELETE)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/roles/" + role_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }

    /**
     * 从指定频道中获取所有身份组
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @return 身份组列表
     */
    @SneakyThrows
    @Override
    public Tuple<GuildRoleList, String> getRoleListByGuild(String bot_id, String guild_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/roles")
                .setMethod(RequestMethod.GET);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        GuildRoleList guildRoleList = JSONObject.parseObject(data, GuildRoleList.class);
        Tuple<GuildRoleList, String> tuple = new Tuple<>();
        tuple.setFirst(guildRoleList).setSecond(data);
        return tuple;
    }

}

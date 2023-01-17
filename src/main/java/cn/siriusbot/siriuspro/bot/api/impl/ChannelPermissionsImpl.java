package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.api.ChannelPermissionsApi;
import cn.siriusbot.siriuspro.bot.api.pojo.ChannelPermissions;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChannelPermissionsImpl implements ChannelPermissionsApi {

    @Autowired
    BotManager botManager;
    @Autowired
    BotPool botPool;

    /**
     * 获取子频道用户权限
     * 用于获取 子频道channel_id 下用户 user_id 的权限。
     * 获取子频道用户权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param user_id    用户ID
     * @return 子频道权限对象
     */
    @SneakyThrows
    @Override
    public Tuple<ChannelPermissions, String> getChannelPermissionsByUser_id(@NotNull String bot_id, String channel_id, String user_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/members/" + user_id + "/permissions")
                .setMethod(RequestMethod.GET);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = response.getBody();
        Tuple<ChannelPermissions, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, ChannelPermissions.class)).setSecond(data);
        return tuple;
    }


    /**
     * 修改子频道身份组权限
     * 用于获取 子频道channel_id 下用户 user_id 的权限。
     * 获取子频道用户权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param role_id    身份组ID
     * @param add        要添加的权限
     * @param remove     要移除的权限
     * @return 修改结果
     */
    @SneakyThrows
    @Override
    public Boolean modifyChannelPermissionsByRole_id(@NotNull String bot_id, String channel_id, String role_id, String add, String remove) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.PATCH)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/roles/" + role_id + "/permissions")
                .putRequestBody("add", add)
                .putRequestBody("remove", remove);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }


    /**
     * 修改指定用户在指定子频道的权限
     * 用于获取 子频道channel_id 下用户 user_id 的权限。
     * 获取子频道用户权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param user_id    用户id
     * @param add        添加的权限
     * @param remove     移除的权限
     * @return 返回修改结果
     */
    @SneakyThrows
    @Override
    public Boolean modifyChannelPermissionsByUser_id(@NotNull String bot_id, String channel_id, String user_id, String add, String remove) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/members/" + user_id + "/permissions")
                .setMethod(RequestMethod.PATCH)
                .putRequestBody("add", add)
                .putRequestBody("remove", remove);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }


    /**
     * 获取指定身份组在指定子频道的权限
     * 用于获取子频道 channel_id 下身份组 role_id 的权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员。
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param role_id    身份组ID
     * @return 子频道权限对象
     */
    @SneakyThrows
    @Override
    public Tuple<ChannelPermissions, String> getChannelPermissionsByRole_id(@NotNull String bot_id, String channel_id, String role_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/roles/" + role_id + "/permissions");
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = response.getBody();
        Tuple<ChannelPermissions, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, ChannelPermissions.class)).setSecond(data);
        return tuple;
    }
}

package cn.siriusbot.siriuspro.entity.impl;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.ChannelPermissionsApi;
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

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
/**
 * 子频道权限对象
 */
public class ChannelPermissions implements ChannelPermissionsApi {
    /**
     * 子频道ID
     */
    String channel_id;

    /**
     * 用户ID
     */
    String user_id;

    /**
     * 身份组ID
     */
    String role_id;

    /**
     * 用户拥有的子频道权限
     */
    String permissions;


    /**
     * 获取子频道用户权限
     * 用于获取 子频道channel_id 下用户 user_id 的权限。
     * 获取子频道用户权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param user_id    用户ID
     * @return 子频道权限对象
     */
    @SneakyThrows
    @Override
    public Tuple<ChannelPermissions,String> getChannelPermissionsByUser_id(Bot bot, String channel_id, String user_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/members/" + user_id + "/permissions").build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();
        Tuple<ChannelPermissions,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }


    /**
     * 修改子频道身份组权限
     * 用于获取 子频道channel_id 下用户 user_id 的权限。
     * 获取子频道用户权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param role_id    身份组ID
     * @param add        要添加的权限
     * @param remove     要移除的权限
     * @return 修改结果
     */
    @SneakyThrows
    @Override
    public Boolean modifyChannelPermissionsByRole_id(Bot bot, String channel_id, String role_id, String add, String remove) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/roles/" + role_id + "/permissions").build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject json = new JSONObject();
        json.put("add", add);
        json.put("remove", remove);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.putRequest(bot, request, body);
        return response.code() == 204;
    }


    /**
     * 修改指定用户在指定子频道的权限
     * 用于获取 子频道channel_id 下用户 user_id 的权限。
     * 获取子频道用户权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param user_id    用户id
     * @param add        添加的权限
     * @param remove     移除的权限
     * @return 返回修改结果
     */
    @Override
    public Boolean modifyChannelPermissionsByUser_id(Bot bot, String channel_id, String user_id, String add, String remove) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/members/" + user_id + "/permissions").build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject json = new JSONObject();
        json.put("add", add);
        json.put("remove", remove);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.putRequest(bot, request, body);
        return response.code() == 204;
    }


    /**
     * 获取指定身份组在指定子频道的权限
     * 用于获取子频道 channel_id 下身份组 role_id 的权限。
     * 要求操作人具有管理子频道的权限，如果是机器人，则需要将机器人设置为管理员。
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param role_id    身份组ID
     * @return 子频道权限对象
     */
    @SneakyThrows
    @Override
    public Tuple<ChannelPermissions,String> getChannelPermissionsByRole_id(Bot bot, String channel_id, String role_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/roles/" + role_id + "/permissions").build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();
        Tuple<ChannelPermissions,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }


    public enum PERMISSIONS {

        /**
         * 可查看子频道
         */
        SEE(String.valueOf(1 << 0)),

        /**
         * 可管理子频道
         */
        ADMIN(String.valueOf(1 << 1)),

        /**
         * 可管理子频道
         */
        SPEAK(String.valueOf(1 << 2)),

        /**
         * 可直播子频道
         */
        LIVE(String.valueOf(1 << 3));
        private String value;

        PERMISSIONS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

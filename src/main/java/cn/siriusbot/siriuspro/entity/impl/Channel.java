package cn.siriusbot.siriuspro.entity.impl;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.ChannelApi;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class Channel implements ChannelApi {
    /**
     * 子频道ID
     */
    public String id;

    /**
     * 频道ID
     */
    public String guild_id;

    /**
     * 子频道名称
     */
    public String name;

    /**
     * 子频道类型
     */

    public Integer type;

    /**
     * 子频道子类型
     */
    public Integer sub_type;

    /**
     * 排序值
     */
    public Integer position;

    @Override
    public String toString() {
        return "Channel{" +
                "id='" + id + '\'' +
                ", guild_id='" + guild_id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", sub_type=" + sub_type +
                ", position=" + position +
                ", parent_id='" + parent_id + '\'' +
                ", owner_id='" + owner_id + '\'' +
                ", private_type=" + private_type +
                ", speak_permission=" + speak_permission +
                ", application_id='" + application_id + '\'' +
                ", permissions='" + permissions + '\'' +
                '}';
    }

    /**
     * 所属分组id
     */
    public String parent_id;

    /**
     * 创建人ID
     */
    public String owner_id;

    /**
     * 子频道私密类型
     */
    public Integer private_type;

    /**
     * 子频道发言权限
     */
    public Integer speak_permission;

    /**
     * 子频道应用类型
     */
    public String application_id;

    /**
     * 用户在子频道的权限
     */
    public String permissions;

    /**
     * 子频道类型
     */
    public enum CHANNEL_TYPE {

        /**
         * 文字子频道
         *
         * @param value
         */
        TEXT_CHANNEL(0),

        /**
         * 保留子频道1
         *
         * @param value
         */
        RETAIN_CHANNEL_I(1),

        /**
         * 语音子频道
         *
         * @param value
         */
        VOICE_CHANNEL(2),

        /**
         * 保留子频道2
         *
         * @param value
         */
        RETAIN_CHANNEL_II(3),

        /**
         * 子频道分组
         *
         * @param value
         */
        PARENT_CHANNEL(4),

        /**
         * 直播子频道
         *
         * @param value
         */
        LIVE_CHANNEL(10005),

        /**
         * 应用子频道
         *
         * @param value
         */
        APPLICATION_CHANNEL(10006),

        /**
         * 论坛子频道
         *
         * @param value
         */
        FORUM_CHANNEL(10007);

        private int value;

        private CHANNEL_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 子频道子类型
     */
    public enum CHANNEL_SUB_TYPE {
        /**
         * 闲聊
         *
         * @param value
         */
        CHAT_SUB_TYPE(0),

        /**
         * 公告
         *
         * @param value
         */
        NOTICE_SUB_TYPE(1),
        /**
         * 攻略
         *
         * @param value
         */
        EXP_SUB_TYPE(2),

        /**
         * 开黑
         *
         * @param value
         */
        OPEN_BLACK_SUB_TYPE(3);

        private int value;

        private CHANNEL_SUB_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 子频道私密类型
     */
    public enum CHANNEL_PRIVATE_TYPE {
        /**
         * 公开频道
         *
         * @param value
         */
        OPEN(0),

        /**
         * 仅频道主管理员可见
         *
         * @param value
         */
        MANAGER(1),

        /**
         * 频道主，管理员，指定成员可见
         *
         * @param value
         */
        MANAGER_AND_SPECIFIC_MEMBERS(2);
        private int value;

        private CHANNEL_PRIVATE_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 子频道发言权限
     */
    public enum CHANNEL_SPEAK_PERMISSION {
        /**
         * 无效
         *
         * @param value
         */
        INVALID(0),
        /**
         * 所有人可发言
         */
        ALL(1),
        /**
         * 频道主，管理员，特定人员可发言
         *
         * @param value
         */
        MANAGER_SPECIFIC_MEMBER(2);
        private int value;


        public int getValue() {
            return value;
        }

        private CHANNEL_SPEAK_PERMISSION(int value) {
            this.value = value;
        }
    }

    /**
     * 获取子频道列表
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @return 返回存放子频道的容器对象
     */
    @SneakyThrows
    @Override
    public Tuple<List<Channel>,String> getChannelList(Bot bot, String guild_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/channels").build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();

        Tuple<List<Channel>,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, List.class)).setSecond(data);
        return tuple;
    }


    /**
     * 获取子频道详情
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @return 返回子频道对象
     */
    @SneakyThrows
    @Override
    public Tuple<Channel,String> getChannelInfo(Bot bot, String channel_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id).build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();
        Tuple<Channel,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }

    /**
     * 创建子频道
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @param channel 子频道对象
     * @return 返回子频道对象
     */
    @SneakyThrows
    @Override
    public Tuple<Channel,String>createChannel(Bot bot, String guild_id, Channel channel) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/channels").build();
        RequestBody body = RequestBody.create(mediaType, JSONObject.toJSONString(channel));
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Channel,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }

    /**
     * 修改子频道
     * @param bot 传入机器人对象
     * @param channel_id 子频道id
     * @param channel 修改后的子频道对象
     * @return 修改后的子频道对象
     */
    @SneakyThrows
    @Override
    public Tuple<Channel,String> modifyChannel(Bot bot, String channel_id, Channel channel) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id).build();
        RequestBody body = RequestBody.create(mediaType, JSONObject.toJSONString(channel));
        Response response = SiriusHttpUtils.patchRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Channel,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }

    /**
     * 删除子频道
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @return 删除结果
     */
    @Override
    public Boolean deleteChannel(Bot bot, String channel_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id).build();
        Response response = SiriusHttpUtils.deleteRequest(bot, request, null);
        return response.code() == 200;
    }

    /**
     * 获取当前音视频/直播子频道的在线成员数
     * @param bot 传入机器人对象
     * @param channel_id 子频道ID
     * @return 在线人数
     */
    @SneakyThrows
    @Override
    public Integer getOnlineMemberNumber(Bot bot, String channel_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl()+"channels/"+channel_id+"/online_nums").build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        JSONObject json = JSONObject.parseObject(response.body().string());
        return json.getInteger("online_nums");
    }

}


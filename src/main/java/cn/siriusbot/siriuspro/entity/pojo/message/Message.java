package cn.siriusbot.siriuspro.entity.pojo.message;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.MessageApi;
import cn.siriusbot.siriuspro.entity.pojo.User;
import cn.siriusbot.siriuspro.entity.pojo.message.ark.MessageArk;
import cn.siriusbot.siriuspro.entity.pojo.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.entity.pojo.message.requestPack.RequestCustomKeyboard;
import cn.siriusbot.siriuspro.entity.pojo.message.requestPack.member.Member;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.*;

import java.io.File;
import java.util.List;

@Data
@Accessors(chain = true)
public class Message implements MessageApi {
    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", channel_id='" + channel_id + '\'' +
                ", guild_id='" + guild_id + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", edited_timestamp='" + edited_timestamp + '\'' +
                ", mention_everyone=" + mention_everyone +
                ", author=" + author +
                ", attachments=" + attachments +
                ", embeds=" + embeds +
                ", mentions=" + mentions +
                ", member=" + member +
                ", ark=" + ark +
                ", seq=" + seq +
                ", seq_in_channel=" + seq_in_channel +
                ", message_reference=" + message_reference +
                ", src_guild_id='" + src_guild_id + '\'' +
                '}';
    }

    /**
     * 消息id
     */
    private String id;
    /**
     * 子频道ID
     */
    private String channel_id;
    /**
     * 频道ID
     */
    private String guild_id;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息创建时间
     */
    private String timestamp;
    /**
     * 消息编辑时间
     */
    private String edited_timestamp;
    /**
     * 是否是@全员消息
     */
    private boolean mention_everyone;
    /**
     * 消息创建者
     */
    private User author;
    /**
     * 附件对象
     */
    private List<MessageAttachment> attachments;
    /**
     * embed消息
     */
    private List<MessageEmbed> embeds;
    /**
     * 消息中@的人
     */
    private List<User> mentions;
    /**
     * 消息创建者的member信息
     */
    private Member member;
    /**
     * ark消息对象
     */
    private MessageArk ark;
    /**
     * 消息之间的排序，2022年8月1日已废弃
     */
    private Integer seq;
    /**
     * 子频道消息的排序,不同子频道之间消息,无法排序
     */
    private Integer seq_in_channel;
    /**
     * 引用消息对象
     */
    private MessageReference message_reference;

    /**
     * 私信场景下识别真实的来源频道id
     */
    private String src_guild_id;

    /**
     * 用于向 channel_id 指定的子频道发送消息。
     * 要求机器人在该子频道具有发送消息的权限。
     * 主动消息在频道主或管理设置了情况下，按设置的数量进行限频。在未设置的情况遵循如下限制:
     * 主动推送消息，默认每天往每个子频道可推送的消息数是 20 条，超过会被限制。
     * 主动推送消息在每个频道中，每天可以往 2 个子频道推送消息。超过后会被限制。
     * 不论主动消息还是被动消息，在一个子频道中，每 1s 只能发送 5 条消息。
     * 被动回复消息有效期为 5 分钟。超时会报错。
     * 发送消息接口要求机器人接口需要连接到 websocket 上保持在线状态
     * 有关主动消息审核，可以通过 Intents 中审核事件 MESSAGE_AUDIT 返回 MessageAudited 对象获取结果。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param content    要发送的消息内容
     * @param image_Url  图片Url
     * @param msg_id     消息ID，
     * @param event_id   事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message,String> sendMessage(Bot bot, String channel_id, String content, String image_Url, String msg_id, String event_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages").build();
        if (channel_id == null || channel_id == "")
            return null;
        MediaType mediaType = MediaType.parse("text/plain;application/json");

        JSONObject json = new JSONObject();
        json.put("content", content);
        json.put("image", image_Url);
        json.put("msg_id", msg_id);
        json.put("event_id", event_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Message,String>tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }

    /**
     * 获取指定子频道的指定消息详情
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message,String> getMessageById(Bot bot, String channel_id, String message_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "messages/" + message_id).build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();
        Tuple<Message,String>tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }

    /**
     * 发送引用消息
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param content    消息内容
     * @param reference  引用消息对象
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message,String> sendReferenceMessage(Bot bot, String channel_id, String content, MessageReference reference) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("content", content);
        json.put("message_reference", reference);
        json.put("msg_id", reference.getMessage_id());
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Message,String>tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;

    }


    /**
     * 发送markdown消息(富文本)
     * 要求操作人在该子频道具有发送消息和对应 Markdown 模版 的权限。
     * 调用前开发者需要先在“QQ开放平台-机器人-发布设置-消息模板”入口为对应机器人创建申请Markdown消息模板，得到模板 id ，在请求时填在对应的 markdown.template_id 上。
     * 模板参数暂不支持数组。
     * 消息体中所包含的URL需要报备并通过验证，方可使用。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param msg_id     消息id
     * @param event_id   事件ID
     * @param markdown   markdown对象
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message,String> sendMarkdownMessage(Bot bot, String channel_id, String msg_id, String event_id, MessageMarkdown markdown) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("markdown", markdown);
        json.put("msg_id", msg_id);
        json.put("event_id", event_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Message,String>tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }


    /**
     * 用于撤回子频道 channel_id 下的消息 message_id。
     * 管理员可以撤回普通成员的消息。
     * 频道主可以撤回所有人的消息。
     * !!注意!!
     * 公域机器人暂不支持申请，仅私域机器人可用，选择私域机器人后默认开通。
     * 注意: 开通后需要先将机器人从频道移除，然后重新添加，方可生效
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param hidetip    是否隐藏删除消息后的小灰条
     * @return 撤回结果
     */
    @Override
    public Boolean deleteMessageById(Bot bot, String channel_id, String message_id, boolean hidetip) {
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "?hidetip=" + hidetip).build();
        Response response = SiriusHttpUtils.deleteRequest(bot, request, null);
        return response.code() == 200;
    }


    /**
     * 通过指定 ark 字段发送模板消息。
     * 要求操作人在该子频道具有发送消息和 对应ARK 模板 的权限。
     * 调用前需要先申请消息模板，这一步会得到一个模板 id，在请求时填在 ark.template_id 上。
     * 发送成功之后，会触发一个创建消息的事件。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param ark        ark消息对象
     * @param msg_id     消息id
     * @param event_id   事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message,String> sendArkMessage(Bot bot, String channel_id, MessageArk ark, String msg_id, String event_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("ark", ark);
        json.put("msg_id", msg_id);
        json.put("event_id", event_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Message,String>tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }


    /**
     * 发送embed模板消息
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param embed      embed消息对象
     * @param msg_id     消息id
     * @param event_id   事件id
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message,String> sendEmbedMessage(Bot bot, String channel_id, MessageEmbed embed, String msg_id, String event_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("embed", embed);
        json.put("msg_id", msg_id);
        json.put("event_id", event_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Message,String>tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }


    /**
     * 发送图文消息
     *
     * @param bot        传入机器人对象
     * @param channel_id 子频道ID
     * @param content    消息内容
     * @param image_path 本地图片路径
     * @param msg_id     消息ID
     * @param event_id   事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message,String> sendImageAndTextMessage(Bot bot, String channel_id, String content, String image_path, String msg_id, String event_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages").build();

        MediaType mediaType = MediaType.parse("multipart/form-data");
        File file = new File(image_path);
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("content", content)
                .addFormDataPart("msg_id", msg_id)
                .addFormDataPart("file_image", file.getAbsolutePath(), RequestBody.create(mediaType, file)).build();
        Response response = SiriusHttpUtils.postRequest(bot, request, body, "multipart/form-data");
        String data = response.body().string();
        Tuple<Message,String>tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }

    /**
     * 发送带按钮的消息
     *
     * @param bot                   传入机器人对象
     * @param channel_id            子频道ID
     * @param requestCustomKeyboard 自定义按钮请求对象
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message,String> sendCustomInLineKeyword(Bot bot, String channel_id, RequestCustomKeyboard requestCustomKeyboard) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "channels/" + channel_id + "/messages").build();
        JSONObject json = new JSONObject();
        MessageKeyboard messageKeyboard = new MessageKeyboard();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        json.put("markdown", requestCustomKeyboard.getMarkdown());
        json.put("keyboard", requestCustomKeyboard.getMessageKeyboard());
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Tuple<Message,String>tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, this.getClass())).setSecond(data);
        return tuple;
    }


    /**
     * 消息撤回时间类型
     */
    public enum DELETE_MESSAGE {
        /**
         * 近三天消息
         */
        THREE_DAY(3),
        /**
         * 近七天消息
         */
        SEVEN_DAY(7),
        /**
         * 近半个月消息
         */
        FIFTEEN_DAY(15),
        /**
         * 近一个月消息
         */
        ONE_MONTH(30),
        /**
         * 全部消息
         */
        ALL_MESSAGE(-1);
        private int value;

        DELETE_MESSAGE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}

package cn.siriusbot.siriuspro.entity.impl;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.DMS_Api;
import cn.siriusbot.siriuspro.entity.impl.message.Message;
import cn.siriusbot.siriuspro.entity.impl.message.MessageMarkdown;
import cn.siriusbot.siriuspro.entity.impl.message.MessageReference;
import cn.siriusbot.siriuspro.entity.impl.message.ark.MessageArk;
import cn.siriusbot.siriuspro.entity.impl.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
/**
 * 私信会话对象
 */
public class DMS implements DMS_Api {
    /**
     * 私信会话关联的频道ID
     */
    private String guild_id;

    /**
     * 私信会话关联的子频道ID
     */
    private String channel_id;

    /**
     * 私信会话的创建时间戳
     */
    private String create_time;


    /**
     * 创建私信会话
     * 机器人和用户存在共同频道才能创建私信会话。
     * 创建成功后，返回创建成功的频道 id ，子频道 id 和创建时间。
     *
     * @param bot             传入机器人对象
     * @param recipient_id    接收者ID
     * @param source_guild_id 源频道ID
     * @return 私信会话对象
     */
    @SneakyThrows
    @Override
    public Map<DMS,Object> createDMS(Bot bot, String recipient_id, String source_guild_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "users/@me/dms").build();
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        JSONObject json = new JSONObject();
        json.put("recipient_id", recipient_id);
        json.put("source_guild_id", source_guild_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Map<DMS,Object> map= new HashMap<>();
        map.put(JSONObject.parseObject(data, this.getClass()),data);
        return map;
    }


    /**
     * 发送普通私信消息
     * 用于向 guild_id 指定的私信会话发送普通私信消息。
     * 被动回复消息有效期为 5 分钟。超时会报错。
     * 私信的 guild_id 在创建私信会话时以及私信消息事件中获取。
     * 私信场景下，每个机器人每天可以对一个用户发 2 条主动消息。
     * 私信场景下，每个机器人每天累计可以发 200 条主动消息。
     * 私信场景下，被动消息没有条数限制
     * 传入msg_id或event_id其一，此条消息视为被动消息
     *
     * @param bot       传入机器人对象
     * @param guild_id  私信场景下的私信会话id
     * @param content   要发送的消息内容
     * @param image_Url 图片Url
     * @param msg_id    消息ID，
     * @param event_id  事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Map<Message,Object> sendMessage(Bot bot, String guild_id, String content, String image_Url, String msg_id, String event_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "dms/" + guild_id + "/messages").build();
        if (guild_id == null || guild_id == "")
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
        Map<Message,Object> map = new HashMap<>();
        map.put(JSONObject.parseObject(data,Message.class),data);
        return map;
    }


    /**
     * 发送私信引用消息
     * 用于向 guild_id 指定的私信会话发送引用私信消息。
     * 被动回复消息有效期为 5 分钟。超时会报错。
     * 私信的 guild_id 在创建私信会话时以及私信消息事件中获取。
     * 私信场景下，每个机器人每天可以对一个用户发 2 条主动消息。
     * 私信场景下，每个机器人每天累计可以发 200 条主动消息。
     * 私信场景下，被动消息没有条数限制
     * 传入msg_id或event_id其一，此条消息视为被动消息
     *
     * @param bot       传入机器人对象
     * @param guild_id  私信场景下的私信会话ID
     * @param content   消息内容
     * @param reference 引用消息对象
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Map<Message,Object> sendReferenceMessage(Bot bot, String guild_id, String content, MessageReference reference) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "dms/" + guild_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("content", content);
        json.put("message_reference", reference);
        json.put("msg_id", reference.getMessage_id());
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Map<Message,Object> map = new HashMap<>();
        map.put(JSONObject.parseObject(data,Message.class),data);
        return map;
    }


    /**
     * 发送markdown消息(富文本)
     * 要求操作人在该子频道具有发送消息和对应 Markdown 模版 的权限。
     * 调用前开发者需要先在“QQ开放平台-机器人-发布设置-消息模板”入口为对应机器人创建申请Markdown消息模板，得到模板 id ，在请求时填在对应的 markdown.template_id 上。
     * 模板参数暂不支持数组。
     * 消息体中所包含的URL需要报备并通过验证，方可使用。
     * <p>
     * 用于向 guild_id 指定的私信会话发送Markdown富文本私信消息。
     * 被动回复消息有效期为 5 分钟。超时会报错。
     * 私信的 guild_id 在创建私信会话时以及私信消息事件中获取。
     * 私信场景下，每个机器人每天可以对一个用户发 2 条主动消息。
     * 私信场景下，每个机器人每天累计可以发 200 条主动消息。
     * 私信场景下，被动消息没有条数限制
     * 传入msg_id或event_id其一，此条消息视为被动消息
     *
     * @param bot      传入机器人对象
     * @param guild_id 私信场景下的私信会话ID
     * @param msg_id   消息id
     * @param event_id 事件ID
     * @param markdown markdown对象
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Map<Message,Object> sendMarkdownMessage(Bot bot, String guild_id, String msg_id, String event_id, MessageMarkdown markdown) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "dms/" + guild_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("markdown", markdown);
        json.put("msg_id", msg_id);
        json.put("event_id", event_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Map<Message,Object> map = new HashMap<>();
        map.put(JSONObject.parseObject(data, Message.class),data);
        return map;
    }


    /**
     * 用于撤回机器人发送的，指定私信会话消息。
     *
     * @param bot        传入机器人对象
     * @param guild_id   私信场景下的私信会话ID
     * @param message_id 消息ID
     * @param hidetip    是否隐藏删除消息后的小灰条
     * @return 撤回结果
     */
    @SneakyThrows
    @Override
    public Boolean deleteMessageById(Bot bot, String guild_id, String message_id, boolean hidetip) {
        Request request = new Request.Builder().url(bot.getOpenUrl() + "dms/" + guild_id + "/messages/" + message_id + "?hidetip=" + hidetip).build();
        Response response = SiriusHttpUtils.deleteRequest(bot, request, null);
        return response.code() == 200;
    }


    /**
     * 通过指定 ark 字段发送模板消息。
     * 要求机器人具有发送消息和 对应ARK 模板 的权限。
     * 调用前需要先申请消息模板，这一步会得到一个模板 id，在请求时填在 ark.template_id 上。
     * 发送成功之后，会触发一个创建消息的事件。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot      传入机器人对象
     * @param guild_id 私信场景下私信会话ID
     * @param ark      ark消息对象
     * @param msg_id   消息id
     * @param event_id 事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public  Map<Message,Object> sendArkMessage(Bot bot, String guild_id, MessageArk ark, String msg_id, String event_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "dms/" + guild_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("ark", ark);
        json.put("msg_id", msg_id);
        json.put("event_id", event_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Map<Message,Object> map = new HashMap<>();
        map.put(JSONObject.parseObject(data, Message.class),data);
        return map;
    }


    /**
     * 发送embed模板消息
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot      传入机器人对象
     * @param guild_id 私信场景下私信会话ID
     * @param embed    embed消息对象
     * @param msg_id   消息id
     * @param event_id 事件id
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Map<Message,Object> sendEmbedMessage(Bot bot, String guild_id, MessageEmbed embed, String msg_id, String event_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "dms/" + guild_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("embed", embed);
        json.put("msg_id", msg_id);
        json.put("event_id", event_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(bot, request, body);
        String data = response.body().string();
        Map<Message,Object> map = new HashMap<>();
        map.put(JSONObject.parseObject(data, Message.class),data);
        return map;
    }


    /**
     * 发送图文消息
     *
     * @param bot        传入机器人对象
     * @param guild_id   私信场景下私信会话ID
     * @param content    消息内容
     * @param image_path 本地图片路径
     * @param msg_id     消息ID
     * @param event_id   事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Map<Message,Object> sendImageAndTextMessage(Bot bot, String guild_id, String content, String image_path, String msg_id, String event_id) {
        bot = BotManager.getBotByBotId(bot.getBotId());
        Request request = new Request.Builder().url(bot.getOpenUrl() + "dms/" + guild_id + "/messages").build();

        MediaType mediaType = MediaType.parse("multipart/form-data");
        File file = new File(image_path);
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("content", content)
                .addFormDataPart("msg_id", msg_id)
                .addFormDataPart("file_image", file.getAbsolutePath(), RequestBody.create(mediaType, file)).build();
        Response response = SiriusHttpUtils.postRequest(bot, request, body, "multipart/form-data");
        String data = response.body().string();
        Map<Message,Object> map = new HashMap<>();
        map.put(JSONObject.parseObject(data, Message.class),data);
        return map;
    }
}

package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.DMS_Api;
import cn.siriusbot.siriuspro.entity.pojo.DMS;
import cn.siriusbot.siriuspro.entity.pojo.message.Message;
import cn.siriusbot.siriuspro.entity.pojo.message.MessageMarkdown;
import cn.siriusbot.siriuspro.entity.pojo.message.MessageReference;
import cn.siriusbot.siriuspro.entity.pojo.message.ark.MessageArk;
import cn.siriusbot.siriuspro.entity.pojo.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import lombok.NonNull;
import lombok.SneakyThrows;
import okhttp3.*;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class DMSImpl implements DMS_Api {

    @Autowired
    BotManager botManager;

    /**
     * 创建私信会话
     * 机器人和用户存在共同频道才能创建私信会话。
     * 创建成功后，返回创建成功的频道 id ，子频道 id 和创建时间。
     *
     * @param bot_id          传入机器人对象ID
     * @param recipient_id    接收者ID
     * @param source_guild_id 源频道ID
     * @return 私信会话对象
     */
    @SneakyThrows
    @Override
    public Tuple<DMS, String> createDMS(String bot_id, String recipient_id, String source_guild_id) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "users/@me/dms").build();
        MediaType mediaType = MediaType.parse("application/json;text/plain");
        JSONObject json = new JSONObject();
        json.put("recipient_id", recipient_id);
        json.put("source_guild_id", source_guild_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(siriusBotClient, request, body);
        String data = response.body().string();
        Tuple<DMS, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, DMS.class)).setSecond(data);
        return tuple;
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
     * @param bot_id    传入机器人对象ID
     * @param guild_id  私信场景下的私信会话id
     * @param content   要发送的消息内容
     * @param image_Url 图片Url
     * @param msg_id    消息ID，
     * @param event_id  事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendMessage(String bot_id, String guild_id, String content, String image_Url, String msg_id, String event_id) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        content = EmojiParser.parseToUnicode(content);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "dms/" + guild_id + "/messages").build();
        if (guild_id == null || guild_id == "")
            return null;
        MediaType mediaType = MediaType.parse("text/plain;application/json");

        JSONObject json = new JSONObject();
        json.put("content", content);
        json.put("image", image_Url);
        json.put("msg_id", msg_id);
        json.put("event_id", event_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(siriusBotClient, request, body);
        String data = response.body().string();
        data = EmojiParser.parseToUnicode(data);
        Tuple<Message, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Message.class)).setSecond(data);
        return tuple;
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
     * @param bot_id    传入机器人对象ID
     * @param guild_id  私信场景下的私信会话ID
     * @param content   消息内容
     * @param reference 引用消息对象
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendReferenceMessage(String bot_id, String guild_id, String content, MessageReference reference) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        content = EmojiParser.parseToUnicode(content);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "dms/" + guild_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("content", content);
        json.put("message_reference", reference);
        json.put("msg_id", reference.getMessage_id());
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(siriusBotClient, request, body);
        String data = response.body().string();
        data = EmojiParser.parseToUnicode(data);
        Tuple<Message, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Message.class)).setSecond(data);
        return tuple;
    }


    /**
     * 发送markdown消息(富文本)
     * 要求操作人在该子频道具有发送消息和对应 Markdown 模版 的权限。
     * 调用前开发者需要先在“QQ开放平台-机器人-发布设置-消息模板”入口为对应机器人创建申请Markdown消息模板，得到模板 id ，在请求时填在对应的 markdown.template_id 上。
     * 模板参数暂不支持数组。
     * 消息体中所包含的URL需要报备并通过验证，方可使用。
     * 用于向 guild_id 指定的私信会话发送Markdown富文本私信消息。
     * 被动回复消息有效期为 5 分钟。超时会报错。
     * 私信的 guild_id 在创建私信会话时以及私信消息事件中获取。
     * 私信场景下，每个机器人每天可以对一个用户发 2 条主动消息。
     * 私信场景下，每个机器人每天累计可以发 200 条主动消息。
     * 私信场景下，被动消息没有条数限制
     * 传入msg_id或event_id其一，此条消息视为被动消息
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 私信场景下的私信会话ID
     * @param msg_id   消息id
     * @param event_id 事件ID
     * @param markdown markdown对象
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendMarkdownMessage(String bot_id, String guild_id, String msg_id, String event_id, MessageMarkdown markdown) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        markdown.setContent(EmojiParser.parseToUnicode(markdown.getContent()));
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "dms/" + guild_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("markdown", markdown);
        json.put("msg_id", msg_id);
        json.put("event_id", event_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(siriusBotClient, request, body);
        String data = response.body().string();
        data = EmojiParser.parseToUnicode(data);
        Tuple<Message, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Message.class)).setSecond(data);
        return tuple;
    }


    /**
     * 用于撤回机器人发送的，指定私信会话消息。
     *
     * @param bot_id     传入机器人对象ID
     * @param guild_id   私信场景下的私信会话ID
     * @param message_id 消息ID
     * @param hidetip    是否隐藏删除消息后的小灰条
     * @return 撤回结果
     */
    @SneakyThrows
    @Override
    public Boolean deleteMessageById(String bot_id, String guild_id, String message_id, boolean hidetip) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "dms/" + guild_id + "/messages/" + message_id + "?hidetip=" + hidetip).build();
        Response response = SiriusHttpUtils.deleteRequest(siriusBotClient, request, null);
        return response.code() == 200;
    }


    /**
     * 通过指定 ark 字段发送模板消息。
     * 要求机器人具有发送消息和 对应ARK 模板 的权限。
     * 调用前需要先申请消息模板，这一步会得到一个模板 id，在请求时填在 ark.template_id 上。
     * 发送成功之后，会触发一个创建消息的事件。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 私信场景下私信会话ID
     * @param ark      ark消息对象
     * @param msg_id   消息id
     * @param event_id 事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendArkMessage(String bot_id, String guild_id, MessageArk ark, String msg_id, String event_id) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "dms/" + guild_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("ark", ark);
        json.put("msg_id", msg_id);
        json.put("event_id", event_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(siriusBotClient, request, body);
        String data = response.body().string();
        data = EmojiParser.parseToUnicode(data);
        Tuple<Message, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Message.class)).setSecond(data);
        return tuple;
    }


    /**
     * 发送embed模板消息
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 私信场景下私信会话ID
     * @param embed    embed消息对象
     * @param msg_id   消息id
     * @param event_id 事件id
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendEmbedMessage(String bot_id, String guild_id, MessageEmbed embed, String msg_id, String event_id) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        for (int i = 0; i < embed.getFields().size(); i++) {
            embed.getFields().get(i).setName(EmojiParser.parseToUnicode(embed.getFields().get(i).getName()));
        }

        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "dms/" + guild_id + "/messages").build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("embed", embed);
        json.put("msg_id", msg_id);
        json.put("event_id", event_id);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.postRequest(siriusBotClient, request, body);
        String data = response.body().string();
        Tuple<Message, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Message.class)).setSecond(data);
        return tuple;
    }


    /**
     * 发送图文消息
     *
     * @param bot_id     传入机器人对象ID
     * @param guild_id   私信场景下私信会话ID
     * @param content    消息内容
     * @param image_path 本地图片路径
     * @param msg_id     消息ID
     * @param event_id   事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendImageAndTextMessage(String bot_id, String guild_id, String content, String image_path, String msg_id, String event_id) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "dms/" + guild_id + "/messages").build();
        content = EmojiParser.parseToUnicode(content);
        MediaType mediaType = MediaType.parse("multipart/form-data");
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        if (content != null)
            builder.addFormDataPart("content", content);
        if (msg_id != null)
            builder.addFormDataPart("msg_id", msg_id);
        if (image_path != null) {
            File file = new File(image_path);
            builder.addFormDataPart("file_image", file.getAbsolutePath(), RequestBody.create(mediaType, file));
        }
        if (event_id != null)
            builder.addFormDataPart("event_id", event_id);

        MultipartBody body = builder.build();
        Response response = SiriusHttpUtils.postRequest(siriusBotClient, request, body, "multipart/form-data");
        String data = response.body().string();
        Tuple<Message, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Message.class)).setSecond(data);
        return tuple;
    }
}

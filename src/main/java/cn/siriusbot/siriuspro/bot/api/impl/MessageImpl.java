package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.api.MessageApi;
import cn.siriusbot.siriuspro.bot.api.pojo.message.Message;
import cn.siriusbot.siriuspro.bot.api.pojo.message.MessageMarkdown;
import cn.siriusbot.siriuspro.bot.api.pojo.message.MessageReference;
import cn.siriusbot.siriuspro.bot.api.pojo.message.ark.MessageArk;
import cn.siriusbot.siriuspro.bot.api.pojo.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.bot.api.pojo.message.requestPack.RequestCustomKeyboard;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestBodyType;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MessageImpl implements MessageApi {

    @Autowired
    BotPool botPool;

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
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param content    要发送的消息内容
     * @param image_Url  图片Url
     * @param msg_id     消息ID，
     * @param event_id   事件ID
     * @return 消息对象
     */
    @Override
    public Tuple<Message, String> sendMessage(@NotNull String bot_id, @NotNull String channel_id, String content, String image_Url, String msg_id, String event_id) {
        BotClient client = botPool.getBotById(bot_id);
        content = EmojiParser.parseToUnicode(content);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.POST)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages")
                .setMediaType("text/plain;application/json")
                .putRequestBody("content", content)
                .putRequestBody("image", image_Url)
                .putRequestBody("msg_id", msg_id)
                .putRequestBody("event_id", event_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        Tuple<Message, String> tuple = new Tuple<>();
        tuple
                .setFirst(JSONObject.parseObject(response.getBody(), Message.class))
                .setSecond(response.getBody());
        return tuple;
    }

    /**
     * 获取指定子频道的指定消息详情
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> getMessageById(@NotNull String bot_id, @NotNull String channel_id, String message_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id)
                .setMethod(RequestMethod.GET);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<Message, String> tuple = new Tuple<>();
        JSONObject json = JSONObject.parseObject(data);
        tuple.setFirst(json.getObject("message", Message.class)).setSecond(data);
        return tuple;
    }

    /**
     * 发送引用消息
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param content    消息内容
     * @param reference  引用消息对象
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendReferenceMessage(@NotNull String bot_id, @NotNull String channel_id, String content, @NotNull MessageReference reference) {
        BotClient client = botPool.getBotById(bot_id);
        content = EmojiParser.parseToUnicode(content);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.POST)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages")
                .putRequestBody("content", content)
                .putRequestBody("msg_id", reference.getMessage_id())
                .putRequestBody("message_reference", reference);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
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
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param msg_id     消息id
     * @param event_id   事件ID
     * @param markdown   markdown对象
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendMarkdownMessage(@NotNull String bot_id, @NotNull String channel_id, String msg_id, String event_id, @NotNull MessageMarkdown markdown) {
        BotClient client = botPool.getBotById(bot_id);
        if(markdown.getContent()!=null){
            markdown.setContent(EmojiParser.parseToUnicode(markdown.getContent()));
        }
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages")
                .setMethod(RequestMethod.POST)
                .putRequestBody("msg_id", msg_id)
                .putRequestBody("event_id", event_id)
                .putRequestBody("markdown", markdown);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<Message, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Message.class)).setSecond(data);
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
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param hidetip    是否隐藏删除消息后的小灰条
     * @return 撤回结果
     */
    @Override
    public Boolean deleteMessageById(@NotNull String bot_id, @NotNull String channel_id, @NotNull String message_id, boolean hidetip) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.DELETE)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages/" + message_id + "?hidetip=" + hidetip);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 200;
    }


    /**
     * 通过指定 ark 字段发送模板消息。
     * 要求操作人在该子频道具有发送消息和 对应ARK 模板 的权限。
     * 调用前需要先申请消息模板，这一步会得到一个模板 id，在请求时填在 ark.template_id 上。
     * 发送成功之后，会触发一个创建消息的事件。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param ark        ark消息对象
     * @param msg_id     消息id
     * @param event_id   事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendArkMessage(@NotNull String bot_id, @NotNull String channel_id, @NotNull MessageArk ark, String msg_id, String event_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages")
                .setMethod(RequestMethod.POST)
                .putRequestBody("ark", ark)
                .putRequestBody("msg_id", msg_id)
                .putRequestBody("event_id", event_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<Message, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Message.class)).setSecond(data);
        return tuple;
    }


    /**
     * 发送embed模板消息
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param embed      embed消息对象
     * @param msg_id     消息id
     * @param event_id   事件id
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendEmbedMessage(@NotNull String bot_id, @NotNull String channel_id, @NotNull MessageEmbed embed, String msg_id, String event_id) {
        BotClient client = botPool.getBotById(bot_id);
        for (int i = 0; i < embed.getFields().size(); i++) {
            embed.getFields().get(i).setName(EmojiParser.parseToUnicode(embed.getFields().get(i).getName()));
        }
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.POST)
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages")
                .putRequestBody("embed", embed)
                .putRequestBody("msg_id", msg_id)
                .putRequestBody("event_id", event_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<Message, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Message.class)).setSecond(data);
        return tuple;
    }


    /**
     * 发送图文消息
     *
     * @param bot_id     传入机器人对象ID
     * @param channel_id 子频道ID
     * @param content    消息内容
     * @param image_path 本地图片路径
     * @param msg_id     消息ID
     * @param event_id   事件ID
     * @return 消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendImageAndTextMessage(@NotNull String bot_id, @NotNull String channel_id, String content, String image_path, String msg_id, String event_id) {
        BotClient client = botPool.getBotById(bot_id);
        content = EmojiParser.parseToUnicode(content);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.POST)
                .setBodyType(RequestBodyType.FORM)
                .setMediaType("multipart/form-data")
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages");
        if (!msg_id.isEmpty())
            botRequest.putRequestBody("msg_id", msg_id);
        if (!event_id.isEmpty())
            botRequest.putRequestBody("event_id", event_id);
        if(!content.isEmpty())
            botRequest.putRequestBody("content",content);
        if(!image_path.isEmpty())
            botRequest.putRequestBody("file_image",new File(image_path));
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<Message, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Message.class)).setSecond(data);
        return tuple;
    }

    /**
     * 发送带按钮的消息
     *
     * @param bot_id                传入机器人对象ID
     * @param channel_id            子频道ID
     * @param requestCustomKeyboard 自定义按钮请求对象
     * @return 返回消息对象
     */
    @SneakyThrows
    @Override
    public Tuple<Message, String> sendCustomInLineKeyword(@NotNull String bot_id, @NotNull String channel_id, @NotNull RequestCustomKeyboard requestCustomKeyboard) {
        BotClient client = botPool.getBotById(bot_id);
        if(requestCustomKeyboard.getMarkdown().getContent()!=null){
            requestCustomKeyboard.getMarkdown().setContent(EmojiParser.parseToUnicode(requestCustomKeyboard.getMarkdown().getContent()));
        }
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "channels/" + channel_id + "/messages")
                .setMethod(RequestMethod.POST)
                .putRequestBody("markdown", requestCustomKeyboard.getMarkdown())
                .putRequestBody("keyboard", requestCustomKeyboard.getMessageKeyboard());
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = response.getBody();
        Tuple<Message, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Message.class)).setSecond(data);
        return tuple;
    }


}

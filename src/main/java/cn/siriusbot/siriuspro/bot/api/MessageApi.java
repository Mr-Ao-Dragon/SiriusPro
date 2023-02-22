package cn.siriusbot.siriuspro.bot.api;


import cn.siriusbot.siriuspro.bot.annotation.EDoc;
import cn.siriusbot.siriuspro.bot.annotation.EName;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.message.Message;
import cn.siriusbot.siriuspro.bot.api.pojo.message.MessageMarkdown;
import cn.siriusbot.siriuspro.bot.api.pojo.message.MessageReference;
import cn.siriusbot.siriuspro.bot.api.pojo.message.ark.MessageArk;
import cn.siriusbot.siriuspro.bot.api.pojo.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.bot.api.pojo.message.requestPack.RequestCustomKeyboard;
import cn.siriusbot.siriuspro.bot.api.proxy.ApiProxy;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

/**
 * 消息Api
 */
public interface MessageApi extends ApiProxy {
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
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param content    要发送的消息内容
     * @param image_Url  图片Url
     * @param msg_id     消息ID，
     * @param event_id   事件ID
     * @return 消息对象
     */
    @EName(name = "发送消息")
    Tuple<Message, String> sendMessage
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "消息内容")
            String content,

            @EDoc(doc = "图片url地址")
            String image_Url,

            @EDoc(doc = "消息ID")
            String msg_id,

            @EDoc(doc = "事件ID")
            String event_id
    );

    /**
     * 获取指定子频道的指定消息详情
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 返回消息对象
     */
    @EName(name = "获取消息详情")
    Tuple<Message, String> getMessageById
    (
            @EDoc(doc = "传入机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "消息ID")
            String message_id
    );

    /**
     * 发送引用消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param content    消息内容
     * @param reference  引用消息对象
     * @return 返回消息对象
     */
    @EName(name = "发送引用消息")
    Tuple<Message, String> sendReferenceMessage
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "消息内容")
            String content,

            @EDoc(doc = "引用消息对象")
            @NonNull @ENonNull MessageReference reference
    );

    /**
     * 发送markdown消息(富文本)
     * 要求操作人在该子频道具有发送消息和对应 Markdown 模版 的权限。
     * 调用前开发者需要先在“QQ开放平台-机器人-发布设置-消息模板”入口为对应机器人创建申请Markdown消息模板，得到模板 id ，在请求时填在对应的 markdown.template_id 上。
     * 模板参数暂不支持数组。
     * 消息体中所包含的URL需要报备并通过验证，方可使用。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param msg_id     消息id
     * @param event_id   事件ID
     * @param markdown   markdown对象
     * @return 返回消息对象
     */
    @EName(name = "发送markdown消息")
    Tuple<Message, String> sendMarkdownMessage
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "消息ID")
            String msg_id,

            @EDoc(doc = "事件ID")
            String event_id,

            @EDoc(doc = "markdown对象")
            @NonNull @ENonNull MessageMarkdown markdown
    );

    /**
     * 用于撤回子频道 channel_id 下的消息 message_id。
     * 管理员可以撤回普通成员的消息。
     * 频道主可以撤回所有人的消息。
     * !!注意!!
     * 公域机器人暂不支持申请，仅私域机器人可用，选择私域机器人后默认开通。
     * 注意: 开通后需要先将机器人从频道移除，然后重新添加，方可生效
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @param hidetip    是否隐藏删除消息后的小灰条
     * @return 撤回结果
     */
    @EName(name = "撤回消息")
    Boolean deleteMessageById
    (

            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "消息ID")
            @NonNull @ENonNull String message_id,

            @EDoc(doc = "是否隐藏删除消息后的小灰条")
            boolean hidetip
    );

    /**
     * 通过指定 ark 字段发送模板消息。
     * 要求操作人在该子频道具有发送消息和 对应ARK 模板 的权限。
     * 调用前需要先申请消息模板，这一步会得到一个模板 id，在请求时填在 ark.template_id 上。
     * 发送成功之后，会触发一个创建消息的事件。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param ark        ark消息对象
     * @param msg_id     消息id
     * @param event_id   事件ID
     * @return 消息对象
     */
    @EName(name = "发送ark消息")
    Tuple<Message, String> sendArkMessage
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "ark消息对象")
            @NonNull @ENonNull MessageArk ark,

            @EDoc(doc = "消息ID")
            String msg_id,

            @EDoc(doc = "事件ID")
            String event_id
    );

    /**
     * 发送embed模板消息
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param embed      embed消息对象
     * @param msg_id     消息id
     * @param event_id   事件id
     * @return 消息对象
     */
    @EName(name = "发送embed消息")
    Tuple<Message, String> sendEmbedMessage
    (

            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull
            String channel_id,

            @EDoc(doc = "embed对象")
            @NonNull @ENonNull MessageEmbed embed,

            @EDoc(doc = "消息ID")
            String msg_id,

            @EDoc(doc = "事件ID")
            String event_id
    );

    /**
     * 发送图文消息
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param content    消息内容
     * @param image_path 本地图片路径
     * @param msg_id     消息ID
     * @param event_id   事件ID
     * @return 消息对象
     */
    @EName(name = "发送图文消息")
    Tuple<Message, String> sendImageAndTextMessage
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "消息内容")
            String content,

            @EDoc(doc = "图片路径")
            String image_path,

            @EDoc(doc = "消息ID")
            String msg_id,

            @EDoc(doc = "事件ID")
            String event_id
    );

    /**
     * 发送自定义按钮模板对象
     *
     * @param bot_id                传入机器人ID
     * @param channel_id            子频道ID
     * @param requestCustomKeyboard 自定义按钮请求对象
     * @return 返回消息对象
     */
    @EName(name = "发送自定义按钮模板消息")
    Tuple<Message, String> sendCustomInLineKeyword
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "子频道ID")
            @NonNull @ENonNull String channel_id,

            @EDoc(doc = "自定义按钮对象")
            @NonNull @ENonNull RequestCustomKeyboard requestCustomKeyboard
    );
}

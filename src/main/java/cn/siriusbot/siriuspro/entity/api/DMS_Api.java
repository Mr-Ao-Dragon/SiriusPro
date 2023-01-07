package cn.siriusbot.siriuspro.entity.api;

import cn.siriusbot.siriuspro.entity.pojo.DMS;
import cn.siriusbot.siriuspro.entity.pojo.message.Message;
import cn.siriusbot.siriuspro.entity.pojo.message.MessageMarkdown;
import cn.siriusbot.siriuspro.entity.pojo.message.MessageReference;
import cn.siriusbot.siriuspro.entity.pojo.message.ark.MessageArk;
import cn.siriusbot.siriuspro.entity.pojo.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.entity.temp.Tuple;

/**
 * 私信对象Api
 */
public interface DMS_Api {


    /**
     * 创建私信会话
     * 机器人和用户存在共同频道才能创建私信会话。
     * 创建成功后，返回创建成功的频道 id ，子频道 id 和创建时间。
     *
     * @param bot_id          传入机器人ID
     * @param recipient_id    接收者ID
     * @param source_guild_id 源频道ID
     * @return 私信会话对象
     */
    public abstract Tuple<DMS, String> createDMS(String bot_id, String recipient_id, String source_guild_id);

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
     * @param bot_id    传入机器人ID
     * @param guild_id  私信场景下的私信会话id
     * @param content   要发送的消息内容
     * @param image_Url 图片Url
     * @param msg_id    消息ID，
     * @param event_id  事件ID
     * @return 消息对象
     */
    public abstract Tuple<Message, String> sendMessage(String bot_id, String guild_id, String content, String image_Url, String msg_id, String event_id);


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
     * @param bot_id    传入机器人ID
     * @param guild_id  私信场景下的私信会话ID
     * @param content   消息内容
     * @param reference 引用消息对象
     * @return 返回消息对象
     */
    public abstract Tuple<Message, String> sendReferenceMessage(String bot_id, String guild_id, String content, MessageReference reference);

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
     * @param bot_id   传入机器人ID
     * @param guild_id 私信场景下的私信会话ID
     * @param msg_id   消息idx
     * @param event_id 事件ID
     * @param markdown markdown对象
     * @return 返回消息对象
     */
    public abstract Tuple<Message, String> sendMarkdownMessage(String bot_id, String guild_id, String msg_id, String event_id, MessageMarkdown markdown);

    /**
     * 用于撤回机器人发送的，指定私信会话消息。
     *
     * @param bot_id     传入机器人ID
     * @param guild_id   私信场景下的私信会话ID
     * @param message_id 消息ID
     * @param hidetip    是否隐藏删除消息后的小灰条
     * @return 撤回结果
     */
    public abstract Boolean deleteMessageById(String bot_id, String guild_id, String message_id, boolean hidetip);

    /**
     * 通过指定 ark 字段发送模板消息。
     * 要求机器人具有发送消息和 对应ARK 模板 的权限。
     * 调用前需要先申请消息模板，这一步会得到一个模板 id，在请求时填在 ark.template_id 上。
     * 发送成功之后，会触发一个创建消息的事件。
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 私信场景下私信会话ID
     * @param ark      ark消息对象
     * @param msg_id   消息id
     * @param event_id 事件ID
     * @return 消息对象
     */
    public abstract Tuple<Message, String> sendArkMessage(String bot_id, String guild_id, MessageArk ark, String msg_id, String event_id);

    /**
     * 发送embed模板消息
     * 如传入event_id和msg_id其中一个，此条消息视为被动消息
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 私信场景下私信会话ID
     * @param embed    embed消息对象
     * @param msg_id   消息id
     * @param event_id 事件id
     * @return 消息对象
     */
    public abstract Tuple<Message, String> sendEmbedMessage(String bot_id, String guild_id, MessageEmbed embed, String msg_id, String event_id);

    /**
     * 发送图文消息
     *
     * @param bot_id     传入机器人ID
     * @param guild_id   私信场景下私信会话ID
     * @param content    消息内容
     * @param image_path 本地图片路径
     * @param msg_id     消息ID
     * @param event_id   事件ID
     * @return 消息对象
     */
    public abstract Tuple<Message, String> sendImageAndTextMessage(String bot_id, String guild_id, String content, String image_path, String msg_id, String event_id);
}

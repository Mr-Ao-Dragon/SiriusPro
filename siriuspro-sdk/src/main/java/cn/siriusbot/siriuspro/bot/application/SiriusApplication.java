package cn.siriusbot.siriuspro.bot.application;

import cn.siriusbot.siriuspro.bot.BotApi;
import cn.siriusbot.siriuspro.bot.annotation.OnEventMessage;
import cn.siriusbot.siriuspro.bot.api.pojo.message.Message;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.pojo.e.MessageType;
import cn.siriusbot.siriuspro.bot.pojo.message.AudioLiveChannelEvent.AudioLiveChannelMemberEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.AudioMessageEvent.AudioMessageEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.AuditMessageEvent.AuditMessageEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.ChannelEvent.ChannelEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.DirectMessageEvent.DirectMessageEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.ForumEvent.ForumEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildEvent.GuildEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildMemberEvent.GuildMemberEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildMemberEvent.create.GuildMemberCreateEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.InterActionMessageEvent.InterActionEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.MessageReactionEvent.ReactionEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.OpenForumEvent.OpenForumEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.PrivateDomainEvent.PrivateDomainMessageInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent.PublicMessageEvent;

/**
 * 天狼星应用模板
 */
public interface SiriusApplication {

    default int versions(){
        return 3;   // 版本号
    }

    /**
     * 插件初始化
     */
    void SiriusAppInit(BotApi api);

    /**
     * 应用详情
     */
    SiriusApplicationInfo appInfo();


    /**
     * 机器人被加入频道事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.GUILD_CREATE)
    default void guild_create_event(String bot_id, GuildEventInfo event) {

    }

    /**
     * 频道信息更改事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.GUILD_UPDATE)
    default void guild_update_event(String bot_id, GuildEventInfo event) {

    }

    /**
     * 频道解散或机器人被移除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.GUILD_DELETE)
    default void guild_delete_event(String bot_id, GuildEventInfo event) {

    }

    /**
     * 子频道被创建事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.CHANNEL_CREATE)
    default void channel_create_event(String bot_id, ChannelEventInfo event) {

    }

    /**
     * 子频道被更新事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.CHANNEL_UPDATE)
    default void channel_update_event(String bot_id, ChannelEventInfo event) {

    }

    /**
     * 子频道被删除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.CHANNEL_DELETE)
    default void channel_delete_event(String bot_id, ChannelEventInfo event) {

    }

    /**
     * 新成员加入频道事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.GUILD_MEMBER_ADD)
    default void guild_member_add_event(String bot_id, GuildMemberCreateEventInfo event) {

    }

    /**
     * 频道成员信息更改事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.GUILD_MEMBER_UPDATE)
    default void guild_member_update_event(String bot_id, GuildMemberEventInfo event) {

    }

    /**
     * 成员退出频道或被移除频道事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.GUILD_MEMBER_REMOVE)
    default void guild_member_remove_event(String bot_id, GuildMemberEventInfo event) {

    }

    /**
     * 私域消息被创建事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.MESSAGE_CREATE)
    default void private_message_create_event(String bot_id, PrivateDomainMessageInfo event) {

    }

    /**
     * 私域消息被创建事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.MESSAGE_DELETE)
    default void private_message_delete_event(String bot_id, PrivateDomainMessageInfo event) {

    }

    /**
     * 表情表态增加事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.MESSAGE_REACTION_ADD)
    default void message_reaction_add_event(String bot_id, ReactionEventInfo event) {

    }

    /**
     * 表情表态被移除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.MESSAGE_REACTION_REMOVE)
    default void message_reaction_remove_event(String bot_id, ReactionEventInfo event) {

    }

    /**
     * 机器人收到私信消息事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.DIRECT_MESSAGE_CREATE)
    default void direct_message_create_event(String bot_id, DirectMessageEventInfo event) {

    }

    /**
     * 私信消息被撤回事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.DIRECT_MESSAGE_DELETE)
    default void direct_message_delete_event(String bot_id, DirectMessageEventInfo event) {

    }

    /**
     * （公域）用户创建主题事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.OPEN_FORUM_POST_CREATE)
    default void open_forum_thread_create_event(String bot_id, OpenForumEventInfo event) {

    }

    /**
     * （公域）用户更新主题事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.OPEN_FORUM_THREAD_UPDATE)
    default void open_forum_thread_update_event(String bot_id, OpenForumEventInfo event) {

    }

    /**
     * （公域）用户删除主题事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.OPEN_FORUM_THREAD_DELETE)
    default void open_forum_thread_delete_event(String bot_id, OpenForumEventInfo event) {

    }

    /**
     * （公域）用户创建帖子事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.OPEN_FORUM_POST_CREATE)
    default void open_forum_post_create_event(String bot_id, OpenForumEventInfo event) {

    }

    /**
     * （公域）用户删除帖子事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.OPEN_FORUM_POST_DELETE)
    default void open_forum_post_delete_event(String bot_id, OpenForumEventInfo event) {

    }

    /**
     * （公域）用户回复帖子事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.OPEN_FORUM_REPLY_CREATE)
    default void open_forum_reply_create_event(String bot_id, OpenForumEventInfo event) {

    }

    /**
     * （公域）用户删除回复事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.OPEN_FORUM_REPLY_DELETE)
    default void open_forum_reply_delete_event(String bot_id, OpenForumEventInfo event) {

    }

    /**
     * 当用户进入直播或视频子频道事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.AUDIO_OR_LIVE_CHANNEL_MEMBER_ENTER)
    default void audio_or_live_channel_member_enter_event(String bot_id, AudioLiveChannelMemberEvent event) {

    }

    /**
     * 当用户离开直播或视频子频道事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.AUDIO_OR_LIVE_CHANNEL_MEMBER_EXIT)
    default void audio_or_live_channel_member_exit_event(String bot_id, AudioLiveChannelMemberEvent event) {

    }

    /**
     * 互动消息被创建事件，一般用于按钮回调
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.INTERACTION_CREATE)
    default void interaction_create_event(String bot_id, InterActionEvent event) {

    }

    /**
     * 消息审核通过事件（一般用于发送主动消息）
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.MESSAGE_AUDIT_PASS)
    default void audit_message_pass_event(String bot_id, AuditMessageEvent event) {

    }

    /**
     * 消息审核不通过事件（一般用于发送主动消息）
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.MESSAGE_AUDIT_REJECT)
    default void audit_message_reject_event(String bot_id, AuditMessageEvent event) {

    }

    /**
     * （私域）主题被创建事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.FORUM_THREAD_CREATE)
    default void forum_thread_create_event(String bot_id, ForumEvent event) {

    }

    /**
     * （私域）主题被更新事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.FORUM_THREAD_UPDATE)
    default void forum_thread_update_event(String bot_id, ForumEvent event) {

    }

    /**
     * （私域）主题被删除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.FORUM_THREAD_DELETE)
    default void forum_thread_delete_event(String bot_id, ForumEvent event) {

    }

    /**
     * （私域）帖子被创建事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.FORUM_POST_CREATE)
    default void forum_post_create_event(String bot_id, ForumEvent event) {

    }

    /**
     * （私域）帖子被删除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.FORUM_POST_DELETE)
    default void forum_post_delete_event(String bot_id, ForumEvent event) {

    }

    /**
     * （私域）回复帖子事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.FORUM_REPLY_CREATE)
    default void forum_reply_create_event(String bot_id, ForumEvent event) {

    }

    /**
     * （私域）回复删除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.FORUM_REPLY_DELETE)
    default void forum_reply_delete_event(String bot_id, ForumEvent event) {

    }

    /**
     * 用户发表审核通过事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.FORUM_PUBLISH_AUDIT_RESULT)
    default void forum_publish_audit_result(String bot_id, ForumEvent event) {

    }

    /**
     * 音频开始播放事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.AUDIO_START)
    default void audio_start_event(String bot_id, AudioMessageEvent event) {

    }

    /**
     * 音频播放完毕事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.AUDIO_FINISH)
    default void audio_finish_event(String bot_id, AudioMessageEvent event) {

    }

    /**
     * 机器人上麦事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.AUDIO_ON_MIC)
    default void audio_on_mic_event(String bot_id, AudioMessageEvent event) {

    }

    /**
     * 机器人下麦事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.AUDIO_OFF_MIC)
    default void audio_off_mic_event(String bot_id, AudioMessageEvent event) {

    }

    /**
     * 公域消息创建事件
     *
     * @param bot_id 事件所属机器人id
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.AT_MESSAGE_CREATE)
    default void public_message_create_event(String bot_id, PublicMessageEvent event) {

    }

    /**
     * 公域消息撤回事件
     *
     * @param bot_id 事件所属机器人id
     * @param event  事件对象
     */
    @OnEventMessage(type = MessageType.PUBLIC_MESSAGE_DELETE)
    default void public_message_delete_event(String bot_id, PublicMessageEvent event) {
    }
}

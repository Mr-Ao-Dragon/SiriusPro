package cn.siriusbot.siriuspro.application;

import cn.siriusbot.siriuspro.message.AudioLiveChannelEvent.AudioLiveChannelMemberEvent;
import cn.siriusbot.siriuspro.message.AudioMessageEvent.AudioMessageEvent;
import cn.siriusbot.siriuspro.message.AuditMessageEvent.AuditMessageEvent;
import cn.siriusbot.siriuspro.message.ChannelEvent.ChannelEventInfo;
import cn.siriusbot.siriuspro.message.DirectMessageEvent.DirectMessageEventInfo;
import cn.siriusbot.siriuspro.message.ForumEvent.ForumEvent;
import cn.siriusbot.siriuspro.message.GuildEvent.GuildEventInfo;
import cn.siriusbot.siriuspro.message.GuildMemberEvent.GuildMemberEventInfo;
import cn.siriusbot.siriuspro.message.GuildMemberEvent.create.GuildMemberCreateEventInfo;
import cn.siriusbot.siriuspro.message.InterActionMessageEvent.InterActionEvent;
import cn.siriusbot.siriuspro.message.MessageReactionEvent.ReactionEventInfo;
import cn.siriusbot.siriuspro.message.OpenForumEvent.OpenForumEventInfo;
import cn.siriusbot.siriuspro.message.PrivateDomainEvent.PrivateDomainMessageInfo;
import cn.siriusbot.siriuspro.message.PublicMessageEvent.PublicMessageEvent;

/**
 * 天狼星应用模板
 */
public interface SiriusApplication {


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
    void guild_create_event(String bot_id, GuildEventInfo event);

    /**
     * 频道信息更改事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void guild_update_event(String bot_id, GuildEventInfo event);

    /**
     * 频道解散或机器人被移除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void guild_delete_event(String bot_id, GuildEventInfo event);

    /**
     * 子频道被创建事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void channel_create_event(String bot_id, ChannelEventInfo event);

    /**
     * 子频道被更新事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void channel_update_event(String bot_id, ChannelEventInfo event);

    /**
     * 子频道被删除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void channel_delete_event(String bot_id, ChannelEventInfo event);

    /**
     * 新成员加入频道事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void guild_member_add_event(String bot_id, GuildMemberCreateEventInfo event);

    /**
     * 频道成员信息更改事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void guild_member_update_event(String bot_id, GuildMemberEventInfo event);

    /**
     * 成员退出频道或被移除频道事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void guild_member_remove_event(String bot_id, GuildMemberEventInfo event);

    /**
     * 私域消息被创建事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void private_message_create_event(String bot_id, PrivateDomainMessageInfo event);

    /**
     * 私域消息被创建事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void private_message_delete_event(String bot_id, PrivateDomainMessageInfo event);

    /**
     * 表情表态增加事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void message_reaction_add_event(String bot_id, ReactionEventInfo event);

    /**
     * 表情表态被移除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void message_reaction_remove_event(String bot_id, ReactionEventInfo event);

    /**
     * 机器人收到私信消息事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void direct_message_create_event(String bot_id, DirectMessageEventInfo event);

    /**
     * 私信消息被撤回事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void direct_message_delete_event(String bot_id, DirectMessageEventInfo event);


    /**
     * （公域）用户创建主题事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void open_forum_thread_create_event(String bot_id, OpenForumEventInfo event);

    /**
     * （公域）用户更新主题事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void open_forum_thread_update_event(String bot_id, OpenForumEventInfo event);

    /**
     * （公域）用户删除主题事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void open_forum_thread_delete_event(String bot_id, OpenForumEventInfo event);

    /**
     * （公域）用户创建帖子事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void open_forum_post_create_event(String bot_id, OpenForumEventInfo event);

    /**
     * （公域）用户删除帖子事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void open_forum_post_delete_event(String bot_id, OpenForumEventInfo event);

    /**
     * （公域）用户回复帖子事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void open_forum_reply_create_event(String bot_id, OpenForumEventInfo event);

    /**
     * （公域）用户删除回复事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void open_forum_reply_delete_event(String bot_id, OpenForumEventInfo event);

    /**
     * 当用户进入直播或视频子频道事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void audio_or_live_channel_member_enter_event(String bot_id, AudioLiveChannelMemberEvent event);

    /**
     * 当用户离开直播或视频子频道事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void audio_or_live_channel_member_exit_event(String bot_id, AudioLiveChannelMemberEvent event);


    /**
     * 互动消息被创建事件，一般用于按钮回调
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void interaction_create_event(String bot_id, InterActionEvent event);

    /**
     * 消息审核通过事件（一般用于发送主动消息）
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void audit_message_pass_event(String bot_id, AuditMessageEvent event);


    /**
     * 消息审核不通过事件（一般用于发送主动消息）
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void audit_message_reject_event(String bot_id, AuditMessageEvent event);

    /**
     * （私域）主题被创建事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void forum_thread_create_event(String bot_id, ForumEvent event);

    /**
     * （私域）主题被更新事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void forum_thread_update_event(String bot_id, ForumEvent event);

    /**
     * （私域）主题被删除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void forum_thread_delete_event(String bot_id, ForumEvent event);

    /**
     * （私域）帖子被创建事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void forum_post_create_event(String bot_id, ForumEvent event);

    /**
     * （私域）帖子被删除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void forum_post_delete_event(String bot_id, ForumEvent event);

    /**
     * （私域）回复帖子事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void forum_reply_create_event(String bot_id, ForumEvent event);

    /**
     * （私域）回复删除事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void forum_reply_delete_event(String bot_id, ForumEvent event);


    /**
     * 用户发表审核通过事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void forum_publish_audit_result(String bot_id, ForumEvent event);

    /**
     * 音频开始播放事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void audio_start_event(String bot_id, AudioMessageEvent event);

    /**
     * 音频播放完毕事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void audio_finish_event(String bot_id, AudioMessageEvent event);

    /**
     * 机器人上麦事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void audio_on_mic_event(String bot_id, AudioMessageEvent event);

    /**
     * 机器人下麦事件
     *
     * @param bot_id 事件所属机器人ID
     * @param event  事件对象
     */
    void audio_off_mic_event(String bot_id, AudioMessageEvent event);

    /**
     * 公域消息创建事件
     *
     * @param bot_id 事件所属机器人id
     * @param event  事件对象
     */
    void public_message_create_event(String bot_id, PublicMessageEvent event);

    /**
     * 公域消息撤回事件
     *
     * @param bot_id 事件所属机器人id
     * @param event  事件对象
     */
    void public_message_delete_event(String bot_id, PublicMessageEvent event);
}

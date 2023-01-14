package cn.siriusbot.siriuspro.bot.pojo.e;

public enum MessageType {
    /**
     * 机器人被加入频道事件
     */
    GUILD_CREATE("GUILD_CREATE"),
    /**
     * 频道信息更改事件
     */
    GUILD_UPDATE("GUILD_UPDATE"),
    /**
     * 频道解散或机器人被移除事件
     */
    GUILD_DELETE("GUILD_DELETE"),
    /**
     * 子频道被创建事件
     */
    CHANNEL_CREATE("CHANNEL_CREATE"),
    /**
     * 子频道被更新事件
     */
    CHANNEL_UPDATE("CHANNEL_UPDATE"),
    /**
     * 子频道被删除事件
     */
    CHANNEL_DELETE("CHANNEL_DELETE"),
    /**
     * 新成员加入频道事件
     */
    GUILD_MEMBER_ADD("GUILD_MEMBER_ADD"),
    /**
     * 频道成员信息更改事件
     */
    GUILD_MEMBER_UPDATE("GUILD_MEMBER_UPDATE"),
    /**
     * 成员退出频道或被移除频道事件
     */
    GUILD_MEMBER_REMOVE("GUILD_MEMBER_REMOVE"),
    /**
     * 私域消息被创建事件
     */
    MESSAGE_CREATE("MESSAGE_CREATE"),
    /**
     * 私域消息被创建事件
     */
    MESSAGE_DELETE("MESSAGE_DELETE"),
    /**
     * 表情表态增加事件
     */
    MESSAGE_REACTION_ADD("MESSAGE_REACTION_ADD"),
    /**
     * 表情表态被移除事件
     */
    MESSAGE_REACTION_REMOVE("MESSAGE_REACTION_REMOVE"),
    /**
     * 机器人收到私信消息事件
     */
    DIRECT_MESSAGE_CREATE("DIRECT_MESSAGE_CREATE"),
    /**
     * 私信消息被撤回事件
     */
    DIRECT_MESSAGE_DELETE("DIRECT_MESSAGE_DELETE"),
    /**
     * （公域）用户创建主题事件
     */
    OPEN_FORUM_THREAD_CREATE("OPEN_FORUM_THREAD_CREATE"),
    /**
     * （公域）用户更新主题事件
     */
    OPEN_FORUM_THREAD_UPDATE("OPEN_FORUM_THREAD_UPDATE"),
    /**
     * （公域）用户删除主题事件
     */
    OPEN_FORUM_THREAD_DELETE("OPEN_FORUM_THREAD_DELETE"),
    /**
     * （公域）用户创建帖子事件
     */
    OPEN_FORUM_POST_CREATE("OPEN_FORUM_POST_CREATE"),
    /**
     * （公域）用户删除帖子事件
     */
    OPEN_FORUM_POST_DELETE("OPEN_FORUM_POST_DELETE"),
    /**
     * （公域）用户回复帖子事件
     */
    OPEN_FORUM_REPLY_CREATE("OPEN_FORUM_REPLY_CREATE"),
    /**
     * （公域）用户删除回复事件
     */
    OPEN_FORUM_REPLY_DELETE("OPEN_FORUM_REPLY_DELETE"),
    /**
     * 当用户进入直播或视频子频道事件
     */
    AUDIO_OR_LIVE_CHANNEL_MEMBER_ENTER("AUDIO_OR_LIVE_CHANNEL_MEMBER_ENTER"),
    /**
     * 当用户离开直播或视频子频道事件
     */
    AUDIO_OR_LIVE_CHANNEL_MEMBER_EXIT("AUDIO_OR_LIVE_CHANNEL_MEMBER_EXIT"),
    /**
     * 互动消息被创建事件，一般用于按钮回调
     */
    INTERACTION_CREATE("INTERACTION_CREATE"),
    /**
     * 消息审核通过事件（一般用于发送主动消息）
     */
    MESSAGE_AUDIT_PASS("MESSAGE_AUDIT_PASS"),
    /**
     * 消息审核不通过事件（一般用于发送主动消息）
     */
    MESSAGE_AUDIT_REJECT("MESSAGE_AUDIT_REJECT"),
    /**
     * （私域）主题被创建事件
     */
    FORUM_THREAD_CREATE("FORUM_THREAD_CREATE"),
    /**
     * （私域）主题被更新事件
     */
    FORUM_THREAD_UPDATE("FORUM_THREAD_UPDATE"),
    /**
     * （私域）主题被删除事件
     */
    FORUM_THREAD_DELETE("FORUM_THREAD_DELETE"),
    /**
     * （私域）帖子被创建事件
     */
    FORUM_POST_CREATE("FORUM_POST_CREATE"),
    /**
     * （私域）帖子被删除事件
     */
    FORUM_POST_DELETE("FORUM_POST_DELETE"),
    /**
     *  （私域）回复帖子事件
     */
    FORUM_REPLY_CREATE("FORUM_REPLY_CREATE"),
    /**
     * （私域）回复删除事件
     */
    FORUM_REPLY_DELETE("FORUM_REPLY_DELETE"),
    /**
     * 用户发表审核通过事件
     */
    FORUM_PUBLISH_AUDIT_RESULT("FORUM_PUBLISH_AUDIT_RESULT"),
    /**
     * 音频开始播放事件
     */
    AUDIO_START("AUDIO_START"),
    /**
     * 音频播放完毕事件
     */
    AUDIO_FINISH("AUDIO_FINISH"),
    /**
     * 机器人上麦事件
     */
    AUDIO_ON_MIC("AUDIO_ON_MIC"),
    /**
     * 机器人下麦事件
     */
    AUDIO_OFF_MIC("AUDIO_OFF_MIC"),
    /**
     * 公域消息创建事件
     */
    AT_MESSAGE_CREATE("AT_MESSAGE_CREATE"),
    /**
     * 公域消息撤回事件
     */
    PUBLIC_MESSAGE_DELETE("PUBLIC_MESSAGE_DELETE");

    String typeName;

    MessageType(String s){
        this.typeName = s;
    }
    public String getTypeName(){
        return typeName;
    }
}

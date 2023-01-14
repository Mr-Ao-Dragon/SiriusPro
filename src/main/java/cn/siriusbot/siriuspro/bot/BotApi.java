package cn.siriusbot.siriuspro.bot;

import cn.siriusbot.siriuspro.bot.api.*;

public interface BotApi {
    /**
     *
     * @return
     */
    AnnouncesApi announcesApi();

    /**
     *
     * @return
     */
    ApiPermissionApi apiPermissionApi();

    /**
     *
     * @return
     */
    AudioApi audioApi();

    /**
     *
     * @return
     */
    ChannelApi channelApi();

    /**
     *
     * @return
     */
    ChannelPermissionsApi channelPermissionsApi();

    /**
     *
     * @return
     */
    DMS_Api dmsApi();

    /**
     *
     * @return
     */
    ForumApi forumApi();

    /**
     *
     * @return
     */
    GuildApi guildApi();

    /**
     *
     * @return
     */
    MemberApi memberApi();

    /**
     *
     * @return
     */
    MessageApi messageApi();

    /**
     *
     * @return
     */
    MessageReactionApi messageReactionApi();

    /**
     *
     * @return
     */
    MessageSettingApi messageSettingApi();

    /**
     *
     * @return
     */
    NoSpeakApi noSpeakApi();

    /**
     *
     * @return
     */
    PinsMessageApi pinsMessageApi();

    /**
     *
     * @return
     */
    RoleApi roleApi();

    /**
     *
     * @return
     */
    ScheduleApi scheduleApi();

    /**
     *
     * @return
     */
    UserApi userApi();
}

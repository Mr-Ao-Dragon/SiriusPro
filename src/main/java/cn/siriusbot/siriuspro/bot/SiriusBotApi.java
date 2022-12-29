package cn.siriusbot.siriuspro.bot;

import cn.siriusbot.siriuspro.entity.api.*;
import cn.siriusbot.siriuspro.entity.pojo.MessageSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SiriusBotApi implements BotApi{

    @Autowired
    AnnouncesApi announcesApi;

    @Autowired
    ApiPermissionApi apiPermissionApi;

    @Autowired
    AudioApi audioApi;
    @Autowired
    ChannelApi channelApi;

    @Autowired
    ChannelPermissionsApi channelPermissionsApi;

    @Autowired
    DMS_Api dmsApi;

    @Autowired
    ForumApi forumApi;

    @Autowired
    GuildApi guildApi;

    @Autowired
    MemberApi memberApi;

    @Autowired
    MessageApi messageApi;

    @Autowired
    MessageReactionApi messageReactionApi;

    @Autowired
    MessageSettingApi messageSettingApi;

    @Autowired
    NoSpeakApi noSpeakApi;

    @Autowired
    PinsMessageApi pinsMessageApi;

    @Autowired
    RoleApi roleApi;

    @Autowired
    ScheduleApi scheduleApi;

    @Autowired
    UserApi userApi;

    /**
     * @return
     */
    @Override
    public AnnouncesApi announcesApi() {
        return announcesApi;
    }

    /**
     * @return
     */
    @Override
    public ApiPermissionApi apiPermissionApi() {
        return apiPermissionApi;
    }

    /**
     * @return
     */
    @Override
    public AudioApi audioApi() {
        return audioApi;
    }

    /**
     * @return
     */
    @Override
    public ChannelApi channelApi() {
        return channelApi;
    }

    /**
     * @return
     */
    @Override
    public ChannelPermissionsApi channelPermissionsApi() {
        return channelPermissionsApi;
    }

    /**
     * @return
     */
    @Override
    public DMS_Api dmsApi() {
        return dmsApi;
    }

    /**
     * @return
     */
    @Override
    public ForumApi forumApi() {
        return forumApi;
    }

    /**
     * @return
     */
    @Override
    public GuildApi guildApi() {
        return guildApi;
    }

    /**
     * @return
     */
    @Override
    public MemberApi memberApi() {
        return memberApi;
    }

    /**
     * @return
     */
    @Override
    public MessageApi messageApi() {
        return messageApi;
    }

    /**
     * @return
     */
    @Override
    public MessageReactionApi messageReactionApi() {
        return messageReactionApi;
    }

    /**
     * @return
     */
    @Override
    public MessageSettingApi messageSettingApi() {
        return messageSettingApi;
    }

    /**
     * @return
     */
    @Override
    public NoSpeakApi noSpeakApi() {
        return noSpeakApi;
    }

    /**
     * @return
     */
    @Override
    public PinsMessageApi pinsMessageApi() {
        return pinsMessageApi;
    }

    /**
     * @return
     */
    @Override
    public RoleApi roleApi() {
        return roleApi;
    }

    /**
     * @return
     */
    @Override
    public ScheduleApi scheduleApi() {
        return scheduleApi;
    }

    /**
     * @return
     */
    @Override
    public UserApi userApi() {
        return userApi;
    }
}

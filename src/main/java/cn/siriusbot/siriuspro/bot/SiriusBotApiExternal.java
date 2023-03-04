package cn.siriusbot.siriuspro.bot;

import cn.siriusbot.siriuspro.admin.service.BotService;
import cn.siriusbot.siriuspro.admin.service.IntentService;
import cn.siriusbot.siriuspro.admin.service.ServerConfigService;
import cn.siriusbot.siriuspro.bot.api.*;
import cn.siriusbot.siriuspro.bot.api.impl.bot.BotManageApiImpl;
import cn.siriusbot.siriuspro.bot.api.proxy.ApiStatisticsProxy;
import cn.siriusbot.siriuspro.bot.application.SiriusApplicationInfo;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import cn.siriusbot.siriuspro.config.bean.StatisticsPool;

/**
 * BotApi外部对象
 */
public class SiriusBotApiExternal implements BotApi{
    ApiStatisticsProxy proxy;

    SiriusApplicationInfo info;
    BotApi api;
    BotManageApi botManageApi;
    StatisticsPool statisticsPool;

    MessageApi messageApi;
    DMS_Api dmsApi;


    public SiriusBotApiExternal(SiriusApplicationInfo info, BotApi api, BotPool botPool, ServerConfigService serverConfigService, StatisticsPool statisticsPool, BotService botService, IntentService intentService) {
        this.info = info;
        this.api = api;
        this.proxy = new ApiStatisticsProxy(info, statisticsPool);
        this.botManageApi = new BotManageApiImpl(info.getPackageName(), botPool, serverConfigService, botService, intentService);
        this.dmsApi = proxy.getProxy(DMS_Api.class, this.api.dmsApi());
        this.messageApi = proxy.getProxy(MessageApi.class, this.api.messageApi());
    }

    @Override
    public BotManageApi botManageApi() {
        return this.botManageApi;
    }

    /**
     * @return
     */
    @Override
    public AnnouncesApi announcesApi() {
        return this.api.announcesApi();
    }

    /**
     * @return
     */
    @Override
    public ApiPermissionApi apiPermissionApi() {
        return this.api.apiPermissionApi();
    }

    /**
     * @return
     */
    @Override
    public AudioApi audioApi() {
        return this.api.audioApi();
    }

    /**
     * @return
     */
    @Override
    public ChannelApi channelApi() {
        return this.api.channelApi();
    }

    /**
     * @return
     */
    @Override
    public ChannelPermissionsApi channelPermissionsApi() {
        return this.api.channelPermissionsApi();
    }

    /**
     * @return
     */
    @Override
    public DMS_Api dmsApi() {
        return this.dmsApi;
    }

    /**
     * @return
     */
    @Override
    public ForumApi forumApi() {
        return this.api.forumApi();
    }

    /**
     * @return
     */
    @Override
    public GuildApi guildApi() {
        return this.api.guildApi();
    }

    /**
     * @return
     */
    @Override
    public MemberApi memberApi() {
        return this.api.memberApi();
    }

    /**
     * @return
     */
    @Override
    public MessageApi messageApi() {
        return this.messageApi;
    }

    /**
     * @return
     */
    @Override
    public MessageReactionApi messageReactionApi() {
        return this.api.messageReactionApi();
    }

    /**
     * @return
     */
    @Override
    public MessageSettingApi messageSettingApi() {
        return this.api.messageSettingApi();
    }

    /**
     * @return
     */
    @Override
    public NoSpeakApi noSpeakApi() {
        return this.api.noSpeakApi();
    }

    /**
     * @return
     */
    @Override
    public PinsMessageApi pinsMessageApi() {
        return this.api.pinsMessageApi();
    }

    /**
     * @return
     */
    @Override
    public RoleApi roleApi() {
        return this.api.roleApi();
    }

    /**
     * @return
     */
    @Override
    public ScheduleApi scheduleApi() {
        return this.api.scheduleApi();
    }

    /**
     * @return
     */
    @Override
    public UserApi userApi() {
        return this.api.userApi();
    }
}

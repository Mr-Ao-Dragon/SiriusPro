package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.bot.annotation.OnBotEvent;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.MessageEvent;
import cn.siriusbot.siriuspro.bot.event.v1.EventMethodHaveParam;
import cn.siriusbot.siriuspro.bot.pojo.e.BotEventType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
import cn.siriusbot.siriuspro.bot.pojo.event.BotWebSocketMessage;
import cn.siriusbot.siriuspro.bot.pojo.message.AudioLiveChannelEvent.AudioLiveChannelMemberEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.AudioMessageEvent.AudioMessageEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.AuditMessageEvent.AuditMessageEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.ChannelEvent.ChannelEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.DirectMessageEvent.DirectMessageDeleteEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.DirectMessageEvent.DirectMessageEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.ForumEvent.ForumEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildEvent.GuildEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildMemberEvent.GuildMemberEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildMemberEvent.create.GuildMemberCreateEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.InterActionMessageEvent.InterActionEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import cn.siriusbot.siriuspro.bot.pojo.message.MessageReactionEvent.ReactionEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.OpenForumEvent.OpenForumEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.PrivateDomainEvent.PrivateDomainMessageInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent.PublicMessageEvent;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;

@Log4j2
public class MessageEventImpl implements MessageEvent , EventMethodHaveParam<BotWebSocketMessage> {

    BotClient client;
    HashMap<String, Class<? extends MessageBody>> map = new HashMap<>();
    /**
     * 注入客户端对象，并初始化
     *
     * @param client
     */
    @Override
    public void init(BotClient client) {
        this.client = client;
        // 初始化map池
        map.put("GUILD_CREATE", GuildEventInfo.class);
        map.put("GUILD_UPDATE", GuildEventInfo.class);
        map.put("GUILD_DELETE", GuildEventInfo.class);
        map.put("CHANNEL_CREATE", ChannelEventInfo.class);
        map.put("CHANNEL_UPDATE", ChannelEventInfo.class);
        map.put("CHANNEL_DELETE", ChannelEventInfo.class);
        map.put("GUILD_MEMBER_ADD", GuildMemberCreateEventInfo.class);
        map.put("GUILD_MEMBER_UPDATE", GuildMemberEventInfo.class);
        map.put("GUILD_MEMBER_REMOVE", GuildMemberEventInfo.class);
        map.put("MESSAGE_CREATE", PrivateDomainMessageInfo.class);
        map.put("MESSAGE_DELETE", PrivateDomainMessageInfo.class);
        map.put("MESSAGE_REACTION_ADD", ReactionEventInfo.class);
        map.put("MESSAGE_REACTION_REMOVE", ReactionEventInfo.class);
        map.put("DIRECT_MESSAGE_CREATE", DirectMessageEventInfo.class);
        map.put("DIRECT_MESSAGE_DELETE", DirectMessageDeleteEvent.class);
        map.put("OPEN_FORUM_THREAD_CREATE", OpenForumEventInfo.class);
        map.put("OPEN_FORUM_THREAD_UPDATE", OpenForumEventInfo.class);
        map.put("OPEN_FORUM_THREAD_DELETE", OpenForumEventInfo.class);
        map.put("OPEN_FORUM_POST_CREATE", OpenForumEventInfo.class);
        map.put("OPEN_FORUM_POST_DELETE", OpenForumEventInfo.class);
        map.put("OPEN_FORUM_REPLY_CREATE", OpenForumEventInfo.class);
        map.put("OPEN_FORUM_REPLY_DELETE", OpenForumEventInfo.class);
        map.put("AUDIO_OR_LIVE_CHANNEL_MEMBER_ENTER", AudioLiveChannelMemberEvent.class);
        map.put("AUDIO_OR_LIVE_CHANNEL_MEMBER_EXIT", AudioLiveChannelMemberEvent.class);
        map.put("INTERACTION_CREATE", InterActionEvent.class);
        map.put("MESSAGE_AUDIT_PASS", AuditMessageEvent.class);
        map.put("MESSAGE_AUDIT_REJECT", AuditMessageEvent.class);
        map.put("FORUM_THREAD_CREATE", ForumEvent.class);
        map.put("FORUM_THREAD_UPDATE", ForumEvent.class);
        map.put("FORUM_THREAD_DELETE", ForumEvent.class);
        map.put("FORUM_POST_CREATE", ForumEvent.class);
        map.put("FORUM_POST_DELETE", ForumEvent.class);
        map.put("FORUM_REPLY_CREATE", ForumEvent.class);
        map.put("FORUM_REPLY_DELETE", ForumEvent.class);
        map.put("FORUM_PUBLISH_AUDIT_RESULT", ForumEvent.class);
        map.put("AUDIO_START", AudioMessageEvent.class);
        map.put("AUDIO_FINISH", AudioMessageEvent.class);
        map.put("AUDIO_ON_MIC", AudioMessageEvent.class);
        map.put("AUDIO_OFF_MIC", AudioMessageEvent.class);
        map.put("AT_MESSAGE_CREATE", PublicMessageEvent.class);
        map.put("PUBLIC_MESSAGE_DELETE", PublicMessageEvent.class);
    }

    /**
     * 机器人登录事件
     */
    @Override
    public void start() {

    }

    @OnBotEvent
    @Override
    public void onEvent(BotEventType type, BotWebSocketMessage body) {
        if (type == BotEventType.WEBSOCKET_MESSAGE && body.getOp() == 0) {
            JSONObject d = body.getBody().getJSONObject("d");
            String event = body.getBody().getString("t");
            Integer s = body.getBody().getInteger("s");
            // 构建消息对象
            BotEventMessage eventMessage = new BotEventMessage();
            eventMessage.setS(s);
            eventMessage.setMessage(body.getMessage());
            switch (event) {
                /*
                  初始化事件
                 */
                case "READY" -> {
                    JSONObject user = d.getJSONObject("user");
                    this.client.getSession()
                            .setS(s)
                            .setSessionId(d.getString("session_id"));
                    log.info("bot初始化完毕，session -> " + this.client.getSession());
                    this.client.getInfo().setState(Robot.STATE_ONLINE); // 在线中
                    this.client.getInfo().setUsername(user.getString("username"));
                    this.client.pushEvent(BotEventType.TASK_HEARTBEAT_START, null); // 开始心跳包任务
                }
                case "RESUMED" -> {
                    this.client.getInfo().setState(Robot.STATE_ONLINE); // 在线中
                    this.client.pushEvent(BotEventType.TASK_HEARTBEAT_START, null); // 开始心跳包任务
                }
            }
            if (this.map.containsKey(event)){
                /*
                    机器人通用事件
                 */
                Class<? extends MessageBody> aClass = this.map.get(event);
                eventMessage.setEventType(event);
                eventMessage.setClazz(aClass);
                eventMessage.setObject(JSONObject.parseObject(body.getMessage(), aClass));
                this.client.pushEvent(BotEventType.EVENT_MESSAGE, eventMessage);
            }
        }
    }
}

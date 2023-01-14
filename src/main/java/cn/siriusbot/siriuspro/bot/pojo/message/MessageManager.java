package cn.siriusbot.siriuspro.bot.pojo.message;


import cn.siriusbot.siriuspro.application.ApplicationManager;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.api.pojo.User;
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
import cn.siriusbot.siriuspro.task.HeartBeatTask;
import cn.siriusbot.siriuspro.timer.SiriusTimer;
import cn.siriusbot.siriuspro.uitls.AppContextUtil;
import cn.siriusbot.siriuspro.websocket.WebSocketServer;
import cn.siriusbot.siriuspro.websocket.WebSocketUtils;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.Objects;

public class MessageManager {

    /**
     * 机器人数据处理
     *
     * @param botId   机器人Id
     * @param message 原始数据
     */
    public static void messageHandle(String botId, String message) {
        BotManager botManager = AppContextUtil.getBean(BotManager.class);
        System.out.println(message);
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(botId);
        JSONObject json = JSONObject.parseObject(message);
        int code = json.getInteger("op");
        switch (code) {
            case 0:
                String event = json.getString("t");
                messageEventHandle(event, message);
                break;
            case 10:
                if (!Objects.equals(siriusBotClient.getSocket().getSession_id(), "") && siriusBotClient.getSocket().getSession_id() != null) {
                    siriusBotClient.getWebSocketClient().send(WebSocketUtils.getReconnectPack(siriusBotClient));
                    return;
                }
                JSONObject dObject = json.getJSONObject("d");
                siriusBotClient.getSocket().setHeartBeat(dObject.getInteger("heartbeat_interval"));
                siriusBotClient.getWebSocketClient().send(WebSocketUtils.getAuthPack(siriusBotClient));
                break;
            case 7:
                WebSocketUtils.Reconnect(botId);
            case 11:
                break;
        }
    }


    /**
     * 消息事件处理
     *
     * @param event_Type 事件类型
     * @param message    消息内容
     */
    public static void messageEventHandle(String event_Type, String message) {
        BotManager botManager = AppContextUtil.getBean(BotManager.class);
        JSONObject json = JSONObject.parseObject(message);
        JSONObject dObject = json.getJSONObject("d");
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(json.getString("bot_id"));
        switch (event_Type) {
            /**
             * 初始化事件
             */
            case "READY":
                JSONObject userObject = dObject.getJSONObject("user");
                siriusBotClient.setUser(new User()
                        .setUserName(userObject.getString("username"))
                        .setBot(true)
                        .setId(userObject.getString("id")));
                siriusBotClient.getSocket().setS(json.getInteger("s"))
                        .setSession_id(dObject.getString("session_id"))
                        .setHeartBeatTimer(new SiriusTimer(new HeartBeatTask(siriusBotClient.getInfo().getBotId())))
                        .setSendHeartBeat(new HeartBeatTask(siriusBotClient.getInfo().getBotId()));
                //发送心跳包
                siriusBotClient.getSocket().getHeartBeatTimer().start(new Date(), siriusBotClient.getSocket().getHeartBeat());
                botManager.update(siriusBotClient);
                break;
            case "RESUMED":
                WebSocketUtils.Resume(siriusBotClient.getInfo().getBotId());
                break;

            case "GUILD_CREATE":
                /**
                 * 机器人加入频道事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.GuildCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, GuildEventInfo.class));
                break;

            case "GUILD_UPDATE":
                /**
                 * 频道信息更改事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.GuildUpdateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, GuildEventInfo.class));
                break;

            case "GUILD_DELETE":
                /**
                 * 频道解散或机器人被移除事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.GuildDeleteEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, GuildEventInfo.class));
                break;
            case "CHANNEL_CREATE":
                /**
                 * 子频道创建事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.ChannelCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ChannelEventInfo.class));
                break;
            case "CHANNEL_UPDATE":
                /**
                 * 子频道更新事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.ChannelUpdateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ChannelEventInfo.class));
                break;
            case "CHANNEL_DELETE":
                /**
                 * 子频道删除事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.ChannelDeleteEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ChannelEventInfo.class));
                break;

            case "GUILD_MEMBER_ADD":
                /**
                 * 频道成员加入事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.GuildMemberAddEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, GuildMemberCreateEventInfo.class));
                break;
            case "GUILD_MEMBER_UPDATE":
                /**
                 * 频道成员信息更改事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.GuildMemberUpdateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, GuildMemberEventInfo.class));
                break;
            case "GUILD_MEMBER_REMOVE":
                /**
                 * 频道成员退出或被移除事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.GuildMemberRemoveEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, GuildMemberEventInfo.class));
                break;
            case "MESSAGE_CREATE":
                /**
                 * 私域消息被创建事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.PrivateMessageCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, PrivateDomainMessageInfo.class));
                break;
            case "MESSAGE_DELETE":
                /**
                 * 私域消息被撤回事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.PrivateMessageDeleteEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, PrivateDomainMessageInfo.class));
                break;

            case "MESSAGE_REACTION_ADD":
                /**
                 * 表情表态添加事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.MessageReactionAddEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ReactionEventInfo.class));
                break;
            case "MESSAGE_REACTION_REMOVE":
                /**
                 * 表情表态移除事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.MessageReactionRemoveEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ReactionEventInfo.class));
                break;

            case "DIRECT_MESSAGE_CREATE":
                /**
                 * 收到用户私信消息事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.DirectMessageCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, DirectMessageEventInfo.class));
                break;
            case "DIRECT_MESSAGE_DELETE":
                /**
                 * 私信消息撤回事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.DirectMessageDeleteEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, DirectMessageEventInfo.class));
                break;

            //公域论坛事件
            case "OPEN_FORUM_THREAD_CREATE":
                /**
                 * 公域_用户创建主题事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.OpenForumThreadCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, OpenForumEventInfo.class));
                break;
            case "OPEN_FORUM_THREAD_UPDATE":
                /**
                 * 公域_用户更新主题事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.OpenForumThreadUpdateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, OpenForumEventInfo.class));
                break;
            case "OPEN_FORUM_THREAD_DELETE":
                /**
                 * 公域_用户删除主题事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.OpenForumThreadDeleteEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, OpenForumEventInfo.class));
                break;
            case "OPEN_FORUM_POST_CREATE":
                /**
                 * 公域_用户创建帖子事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.OpenForumPostCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, OpenForumEventInfo.class));
                break;
            case "OPEN_FORUM_POST_DELETE":
                /**
                 * 公域_用户删除帖子事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.OpenForumPostDeleteEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, OpenForumEventInfo.class));
                break;
            case "OPEN_FORUM_REPLY_CREATE":
                /**
                 * 公域_用户回复评论事件
                 */WebSocketServer.sendAll(message);
                ApplicationManager.OpenForumReplyCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, OpenForumEventInfo.class));
                break;

            case "OPEN_FORUM_REPLY_DELETE":
                /**
                 * 公域_用户删除评论事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.OpenForumReplyDeleteEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, OpenForumEventInfo.class));
                break;

            //音视频/直播子频道成员进出事件
            case "AUDIO_OR_LIVE_CHANNEL_MEMBER_ENTER":
                /**
                 * 当用户进入音视频/直播子频道事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.AudioORLiveChannelMemberEnterEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, AudioLiveChannelMemberEvent.class));
                break;

            case "AUDIO_OR_LIVE_CHANNEL_MEMBER_EXIT":
                /**
                 * 当用户离开音视频/直播子频道事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.AudioORLiveChannelMemberExitEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, AudioLiveChannelMemberEvent.class));
                break;
            //互动事件，多为按钮等回调事件
            case "INTERACTION_CREATE":
                /**
                 * 互动消息创建事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.InterActonCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, InterActionEvent.class));
                break;

            //消息审核事件,一般为发送主动消息
            case "MESSAGE_AUDIT_PASS":
                /**
                 * 消息审核通过事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.MessageAuditPassEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, AuditMessageEvent.class));
                break;

            case "MESSAGE_AUDIT_REJECT":
                /**
                 * 消息审核不通过事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.MessageAuditRejectEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, AuditMessageEvent.class));
                break;

            //私域论坛事件
            case "FORUM_THREAD_CREATE":
                /**
                 * 用户创建主题事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.ForumThreadCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ForumEvent.class));
                break;
            case "FORUM_THREAD_UPDATE":
                /**
                 * 用户更新主题事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.ForumThreadUpdateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ForumEvent.class));
                break;
            case "FORUM_THREAD_DELETE":
                /**
                 * 用户删除主题事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.ForumThreadDeleteEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ForumEvent.class));
                break;
            case "FORUM_POST_CREATE":
                /**
                 * 用户创建帖子事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.ForumPostCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ForumEvent.class));
                break;
            case "FORUM_POST_DELETE":
                /**
                 * 用户删除帖子事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.ForumPostDeleteEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ForumEvent.class));
                break;
            case "FORUM_REPLY_CREATE":
                /**
                 * 用户回复评论事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.ForumReplyCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ForumEvent.class));
                break;
            case "FORUM_REPLY_DELETE":
                /**
                 * 用户删除评论事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.ForumReplyDeleteEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ForumEvent.class));
                break;
            case "FORUM_PUBLISH_AUDIT_RESULT":
                /**
                 * 用户发表审核通过事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.ForumPublishAuditResultEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, ForumEvent.class));
                break;

            //音频事件
            case "AUDIO_START":
                /**
                 * 音频开始播放事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.AudioStartEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, AudioMessageEvent.class));
                break;
            case "AUDIO_FINISH":
                /**
                 * 音频播放结束事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.AudioFinishEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, AudioMessageEvent.class));
                break;
            case "AUDIO_ON_MIC":
                /**
                 * 机器人上麦事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.AudioOnMicEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, AudioMessageEvent.class));
                break;
            case "AUDIO_OFF_MIC":
                /**
                 * 音频下麦事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.AudioOffMicEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, AudioMessageEvent.class));
                break;
            //公域消息事件
            case "AT_MESSAGE_CREATE":
                /**
                 * 收到@机器人消息事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.PublicMessageCreateEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, PublicMessageEvent.class));
                break;
            case "PUBLIC_MESSAGE_DELETE":
                /**
                 * 消息删除事件
                 */
                WebSocketServer.sendAll(message);
                ApplicationManager.PublicMessageDeleteEventPush(siriusBotClient.getInfo().getBotId(), JSONObject.parseObject(message, PublicMessageEvent.class));
                break;
        }
    }


}

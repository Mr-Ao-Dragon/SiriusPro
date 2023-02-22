package cn.siriusbot.siriuspro.bot.plugin;

import cn.siriusbot.siriuspro.bot.annotation.*;
import cn.siriusbot.siriuspro.bot.application.SiriusApplication;
import cn.siriusbot.siriuspro.bot.application.SiriusApplicationInfo;
import cn.siriusbot.siriuspro.bot.pojo.e.MessageType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
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
import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import cn.siriusbot.siriuspro.bot.pojo.message.MessageReactionEvent.ReactionEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.OpenForumEvent.OpenForumEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.PrivateDomainEvent.PrivateDomainMessageInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent.PublicMessageEvent;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.web.R.R;
import cn.siriusbot.siriuspro.web.pojo.BotHttpRequest;
import cn.siriusbot.siriuspro.web.websocket.surface.WebsocketSession;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Method;
import java.util.HashMap;

@Log4j2
public class JavaPlugInClient implements PlugInClient , ExpandClient {

    private static final HashMap<String, Class<? extends MessageBody>> eventMap = new HashMap<>();

    static {
        // 初始化map池
        eventMap.put("GUILD_CREATE", GuildEventInfo.class);
        eventMap.put("GUILD_UPDATE", GuildEventInfo.class);
        eventMap.put("GUILD_DELETE", GuildEventInfo.class);
        eventMap.put("CHANNEL_CREATE", ChannelEventInfo.class);
        eventMap.put("CHANNEL_UPDATE", ChannelEventInfo.class);
        eventMap.put("CHANNEL_DELETE", ChannelEventInfo.class);
        eventMap.put("GUILD_MEMBER_ADD", GuildMemberCreateEventInfo.class);
        eventMap.put("GUILD_MEMBER_UPDATE", GuildMemberEventInfo.class);
        eventMap.put("GUILD_MEMBER_REMOVE", GuildMemberEventInfo.class);
        eventMap.put("MESSAGE_CREATE", PrivateDomainMessageInfo.class);
        eventMap.put("MESSAGE_DELETE", PrivateDomainMessageInfo.class);
        eventMap.put("MESSAGE_REACTION_ADD", ReactionEventInfo.class);
        eventMap.put("MESSAGE_REACTION_REMOVE", ReactionEventInfo.class);
        eventMap.put("DIRECT_MESSAGE_CREATE", DirectMessageEventInfo.class);
        eventMap.put("DIRECT_MESSAGE_DELETE", DirectMessageEventInfo.class);
        eventMap.put("OPEN_FORUM_THREAD_CREATE", OpenForumEventInfo.class);
        eventMap.put("OPEN_FORUM_THREAD_UPDATE", OpenForumEventInfo.class);
        eventMap.put("OPEN_FORUM_THREAD_DELETE", OpenForumEventInfo.class);
        eventMap.put("OPEN_FORUM_POST_CREATE", OpenForumEventInfo.class);
        eventMap.put("OPEN_FORUM_POST_DELETE", OpenForumEventInfo.class);
        eventMap.put("OPEN_FORUM_REPLY_CREATE", OpenForumEventInfo.class);
        eventMap.put("OPEN_FORUM_REPLY_DELETE", OpenForumEventInfo.class);
        eventMap.put("AUDIO_OR_LIVE_CHANNEL_MEMBER_ENTER", AudioLiveChannelMemberEvent.class);
        eventMap.put("AUDIO_OR_LIVE_CHANNEL_MEMBER_EXIT", AudioLiveChannelMemberEvent.class);
        eventMap.put("INTERACTION_CREATE", InterActionEvent.class);
        eventMap.put("MESSAGE_AUDIT_PASS", AuditMessageEvent.class);
        eventMap.put("MESSAGE_AUDIT_REJECT", AuditMessageEvent.class);
        eventMap.put("FORUM_THREAD_CREATE", ForumEvent.class);
        eventMap.put("FORUM_THREAD_UPDATE", ForumEvent.class);
        eventMap.put("FORUM_THREAD_DELETE", ForumEvent.class);
        eventMap.put("FORUM_POST_CREATE", ForumEvent.class);
        eventMap.put("FORUM_POST_DELETE", ForumEvent.class);
        eventMap.put("FORUM_REPLY_CREATE", ForumEvent.class);
        eventMap.put("FORUM_REPLY_DELETE", ForumEvent.class);
        eventMap.put("FORUM_PUBLISH_AUDIT_RESULT", ForumEvent.class);
        eventMap.put("AUDIO_START", AudioMessageEvent.class);
        eventMap.put("AUDIO_FINISH", AudioMessageEvent.class);
        eventMap.put("AUDIO_ON_MIC", AudioMessageEvent.class);
        eventMap.put("AUDIO_OFF_MIC", AudioMessageEvent.class);
        eventMap.put("AT_MESSAGE_CREATE", PublicMessageEvent.class);
        eventMap.put("PUBLIC_MESSAGE_DELETE", PublicMessageEvent.class);
    }

    // =============
    SiriusApplication app;      // 插件实例
    SiriusApplicationInfo info; // 插件信息

    public JavaPlugInClient(SiriusApplication app, SiriusApplicationInfo info) {
        this.app = app;
        this.info = info;
    }

    /**
     * 插件唯一id
     */
    @Override
    public String getPackageName() {
        return info.getPackageName();
    }

    /**
     * 插件详细
     */
    @Override
    public SiriusApplicationInfo getInfo() {
        return info;
    }

    /**
     * 推送事件
     *
     * @param botId   机器人id
     * @param message 事件对象
     */
    @Override
    public void putEvent(String botId, BotEventMessage message) {
        Class<? extends SiriusApplication> clazz = app.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            OnEventMessage annotation = method.getAnnotation(OnEventMessage.class);
            if (annotation != null) {
                MessageType type = annotation.type();
                Class<? extends MessageBody> messageBodyClass = eventMap.get(type.getTypeName());
                if (messageBodyClass != message.getClazz()) {
                    continue;
                }
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 2 || parameterTypes[0] != String.class) {
                    log.error(String.format("插件[%s]监听的方法(%s)不规范，必须只包含两位参数，" +
                            "第一位为BotId，第二位为事件Body", app.appInfo().getAppName(), method.getName()));
                    continue;
                }
                if (parameterTypes[1] != messageBodyClass) {
                    log.error(String.format("插件[%s]监听的方法(%s)第二参数方法应该为%s,而实际是%s",
                            app.appInfo().getAppName(),
                            method.getName(),
                            messageBodyClass,
                            parameterTypes[1]
                    ));
                    continue;
                }
                try {
                    method.invoke(app, botId, message.getObject());
                } catch (Throwable e) {
                    log.error(String.format("插件[%s]监听的方法(%s)调用异常：%s",
                            app.appInfo().getAppName(),
                            method.getName(),
                            e.getCause()
                    ));
                }
            }
        }

    }

    /**
     * 插件web请求处理
     *
     * @return R对象
     */
    @Override
    public R webPost(BotHttpRequest request) {
        Class<? extends SiriusApplication> clazz = app.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            OnWebRequestEvent annotation = method.getAnnotation(OnWebRequestEvent.class);
            if (annotation != null && annotation.name().equals(request.getName())) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length != 1 || parameterTypes[0] != BotHttpRequest.class) {
                    break;
                }
                if (method.getReturnType() != R.class){
                    break;
                }
                try {
                    return (R) method.invoke(app, request);
                } catch (MsgException e){
                    return e.getR();
                } catch (Throwable e) {
                    log.error(String.format("插件[%s]监听的方法(%s)调用异常：%s",
                            app.appInfo().getAppName(),
                            method.getName(),
                            e.getCause()
                    ));
                }
            }
        }
        return null;
    }

    /**
     * 推送ws连接事件
     *
     * @param session
     */
    @Override
    public void putWebSocketOpen(WebsocketSession session) {
        Class<? extends SiriusApplication> clazz = app.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            OnExpandOpen annotation = method.getAnnotation(OnExpandOpen.class);
            if (annotation == null) {
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1){
                continue;
            }
            if (parameterTypes[0] != WebsocketSession.class){
                continue;
            }
            try {
                method.invoke(app, session);
            } catch (Throwable e) {
                log.error(String.format("插件[%s]监听的方法(%s)调用异常：%s",
                        app.appInfo().getAppName(),
                        method.getName(),
                        e.getCause()
                ));
            }
        }

    }

    /**
     * 推送ws消息
     *
     * @param session
     * @param message
     */
    @Override
    public void putWebSocketMessage(WebsocketSession session, String message) {
        Class<? extends SiriusApplication> clazz = app.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            OnExpandMessage annotation = method.getAnnotation(OnExpandMessage.class);
            if (annotation == null){
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 2){
                continue;
            }
            if (parameterTypes[0] != WebsocketSession.class || parameterTypes[1] != String.class){
                continue;
            }
            try {
                method.invoke(app, session, message);
            } catch (Throwable e) {
                log.error(String.format("插件[%s]监听的方法(%s)调用异常：%s",
                        app.appInfo().getAppName(),
                        method.getName(),
                        e.getCause()
                ));
            }

        }
    }

    /**
     * 推送ws关闭事件
     *
     * @param session
     */
    @Override
    public void putWebSocketClose(WebsocketSession session) {
        Class<? extends SiriusApplication> clazz = app.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            OnExpandClose annotation = method.getAnnotation(OnExpandClose.class);
            if (annotation == null) {
                continue;
            }
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length != 1){
                continue;
            }
            if (parameterTypes[0] != WebsocketSession.class){
                continue;
            }
            try {
                method.invoke(app, session);
            } catch (Throwable e) {
                log.error(String.format("插件[%s]监听的方法(%s)调用异常：%s",
                        app.appInfo().getAppName(),
                        method.getName(),
                        e.getCause()
                ));
            }
        }
    }
}

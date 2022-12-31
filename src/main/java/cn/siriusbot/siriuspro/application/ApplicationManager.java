package cn.siriusbot.siriuspro.application;

import cn.siriusbot.siriuspro.bot.BotApi;
import cn.siriusbot.siriuspro.logger.SiriusLogger;
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
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


@Component
@Log4j2
public class ApplicationManager {

    @Autowired
    BotApi botApi;


    public static List<SiriusApplication> apps = new ArrayList<>();

    /**
     * 加载全部应用
     */
    public void loadApps() {
        File file = new File(ApplicationUtils.appsPath);
        for (File appFile : Objects.requireNonNull(file.listFiles())) {
            SiriusApplication app = getAppInstance(appFile);
            if (app != null) {
                System.out.println(SiriusLogger.getFormatLogString("应用"+"["+app.appInfo().getAppName()+"-"+app.appInfo().getAppAuthor()+"]加载成功！",36,1));
                apps.add(app);
            }
        }

    }


    /**
     * 获取应用实例
     * @param app
     * @return
     */
    @SneakyThrows
    public SiriusApplication getAppInstance(File app) {
        //获取当前线程类加载器
        ClassLoader parent = Thread.currentThread().getContextClassLoader();  // 启动类加载器
        //获取类加载器
        URLClassLoader appClass = new URLClassLoader(new URL[]{new URL("file:" + app.getAbsolutePath())}, parent);

        JarFile jarFile = new JarFile(app.getAbsolutePath());
        Enumeration<JarEntry> entries = jarFile.entries();
        //遍历jar包里所有文件
        while (entries.hasMoreElements()) {
            JarEntry jarEntry = entries.nextElement();
            //获取资源名称
            String fileName = jarEntry.getName();
            if (fileName.contains(".class")) {
                fileName = fileName.replace("/", ".").replace(".class", "");
                Class<?> aClass = null;
                //判断是否为天狼星应用
                try {
                    aClass = appClass.loadClass(fileName);
                    Object o = aClass.newInstance();
                    if (o instanceof SiriusApplication application) {
                        System.out.println(fileName);
                        application.SiriusAppInit(botApi);
                        return  application;
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }

            }
        }
        System.err.println(jarFile.getName() + "中未有继承SiriusApplication的类，可能不是天狼星应用");
        return null;
    }


    /**
     * 机器人被加入频道事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void GuildCreateEventPush(String botId, GuildEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("guild_create_event").invoke(app,botId,event);
        }
    }

    /**
     * 频道信息更新事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void GuildUpdateEventPush(String botId, GuildEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("guild_update_event").invoke(app,botId,event);
        }
    }

    /**
     * 机器人被移除或频道解散事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void GuildDeleteEventPush(String botId, GuildEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("guild_delete_event").invoke(app,botId,event);
        }
    }

    /**
     * 子频道创建事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void ChannelCreateEventPush(String botId, ChannelEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("channel_create_event").invoke(app,botId,event);
        }
    }



    /**
     * 子频道信息更改事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void ChannelUpdateEventPush(String botId, ChannelEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("channel_update_event").invoke(app,botId,event);
        }
    }

    /**
     * 子频道被删除事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void ChannelDeleteEventPush(String botId, ChannelEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("channel_delete_event").invoke(app,botId,event);
        }
    }

    /**
     * 成员加入频道事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void GuildMemberAddEventPush(String botId, GuildMemberCreateEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("guild_member_add_event").invoke(app,botId,event);
        }
    }

    /**
     * 成员信息更改事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void GuildMemberUpdateEventPush(String botId, GuildMemberEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("guild_member_update_event").invoke(app,botId,event);
        }
    }

    /**
     * 成员退出或被移除频道事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void GuildMemberRemoveEventPush(String botId, GuildMemberEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("guild_member_remove_event").invoke(app,botId,event);
        }
    }

    /**
     * 私域消息被创建事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void PrivateMessageCreateEventPush(String botId, PrivateDomainMessageInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("private_message_create_event").invoke(app,botId,event);
        }
    }

    /**
     * 私域消息被撤回事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void PrivateMessageDeleteEventPush(String botId, PrivateDomainMessageInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("private_message_delete_event").invoke(app,botId,event);
        }
    }

    /**
     * 表情表态添加事件推送
     * @param botId
     * @param event
     */
    @SneakyThrows
    public static void MessageReactionAddEventPush(String botId, ReactionEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("message_reaction_add_event").invoke(app,botId,event);
        }
    }

    /**
     * 表情表态移除事件推送
     * @param botId
     * @param event
     */
    @SneakyThrows
    public static void MessageReactionRemoveEventPush(String botId, ReactionEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("message_reaction_remove_event").invoke(app,botId,event);
        }
    }

    /**
     * 私信消息撤回事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void DirectMessageCreateEventPush(String botId, DirectMessageEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("direct_message_create_event").invoke(app,botId,event);
        }
    }


    /**
     * 私信消息撤回事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void DirectMessageDeleteEventPush(String botId, DirectMessageEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("direct_message_delete_event").invoke(app,botId,event);
        }
    }

    /**
     * （公域）主题被创建事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void OpenForumThreadCreateEventPush(String botId, OpenForumEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("open_forum_thread_create_event").invoke(app,botId,event);
        }
    }

    /**
     * （公域）主题被更新事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void OpenForumThreadUpdateEventPush(String botId, OpenForumEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("open_forum_thread_update_event").invoke(app,botId,event);
        }
    }


    /**
     * （公域）主题被删除创建事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void OpenForumThreadDeleteEventPush(String botId, OpenForumEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("open_forum_thread_delete_event").invoke(app,botId,event);
        }
    }


    /**
     * （公域）帖子被创建事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void OpenForumPostCreateEventPush(String botId, OpenForumEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("open_forum_post_create_event").invoke(app,botId,event);
        }
    }

    /**
     * （公域）帖子被删除事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void OpenForumPostDeleteEventPush(String botId, OpenForumEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("open_forum_post_delete_event").invoke(app,botId,event);
        }
    }

    /**
     * （公域）回复被创建事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void OpenForumReplyCreateEventPush(String botId, OpenForumEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get(" open_forum_reply_create_event").invoke(app,botId,event);
        }
    }

    /**
     * （公域）回复被删除事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void OpenForumReplyDeleteEventPush(String botId, OpenForumEventInfo event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("open_forum_reply_delete_event").invoke(app,botId,event);
        }
    }

    /**
     * 成员进入音频或直播子频道事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void AudioORLiveChannelMemberEnterEventPush(String botId, AudioLiveChannelMemberEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("audio_or_live_channel_member_enter_event").invoke(app,botId,event);
        }
    }

    /**
     * 成员离开音频或直播子频道事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void AudioORLiveChannelMemberExitEventPush(String botId, AudioLiveChannelMemberEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("audio_or_live_channel_member_exit_event").invoke(app,botId,event);
        }
    }

    /**
     * 互动消息被创建事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void InterActonCreateEventPush(String botId, InterActionEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("interaction_create_event").invoke(app,botId,event);
        }
    }

    /**
     * 消息审核不通过事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void MessageAuditRejectEventPush(String botId, AuditMessageEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("audit_message_reject_event").invoke(app,botId,event);
        }
    }

    /**
     * 消息审核通过事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void MessageAuditPassEventPush(String botId, AuditMessageEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("audit_message_pass_event").invoke(app,botId,event);
        }
    }

    /**
     * （私域）论坛主题被创建事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */

    @SneakyThrows
    public static void ForumThreadCreateEventPush(String botId, ForumEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("forum_thread_create_event").invoke(app,botId,event);
        }
    }

    /**
     * （私域）论坛主题被更新事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void ForumThreadUpdateEventPush(String botId, ForumEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("forum_thread_update_event").invoke(app,botId,event);
        }
    }

    /**
     * （私域）论坛主题被删除事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void ForumThreadDeleteEventPush(String botId, ForumEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("forum_thread_create_event").invoke(app,botId,event);
        }
    }

    /**
     * （私域）论坛帖子被创建事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void ForumPostCreateEventPush(String botId, ForumEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("forum_post_create_event").invoke(app,botId,event);
        }
    }

    /**
     * （私域）论坛帖子被删除事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void ForumPostDeleteEventPush(String botId, ForumEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("forum_post_delete_event").invoke(app,botId,event);
        }
    }

    /**
     * （私域）论坛回复被创建事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void ForumReplyCreateEventPush(String botId, ForumEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("forum_reply_create_event").invoke(app,botId,event);
        }
    }

    /**
     * （私域）论坛回复被删除事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void ForumReplyDeleteEventPush(String botId, ForumEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("forum_reply_delete_event").invoke(app,botId,event);
        }
    }

    /**
     * （私域）论坛主题被创建事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void ForumPublishAuditResultEventPush(String botId, ForumEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("forum_publish_audit_result").invoke(app,botId,event);
        }
    }

    /**
     * 音频开始播放事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void AudioStartEventPush(String botId, AudioMessageEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("audio_start_event").invoke(app,botId,event);
        }
    }

    /**
     * 音频播放完毕事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void AudioFinishEventPush(String botId, AudioMessageEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("audio_finish_event").invoke(app,botId,event);
        }
    }

    /**
     * 机器人上麦事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void AudioOnMicEventPush(String botId, AudioMessageEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("audio_on_mic_event").invoke(app,botId,event);
        }
    }

    /**
     * 机器人下麦事件推送
     * @param botId 机器人Id
     * @param event 事件对象
     */
    @SneakyThrows
    public static void AudioOffMicEventPush(String botId, AudioMessageEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("audio_off_mic_event").invoke(app,botId,event);
        }
    }


    /**
     * 机器人被@消息事件推送
     * @param botId 传入机器人ID
     * @param event 事件对象
     */
    @SneakyThrows
    public static void PublicMessageCreateEventPush(String botId, PublicMessageEvent event){
        //循环推送事件至所有应用
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("public_message_create_event").invoke(app,botId,event);
        }
    }

    /**
     * （公域）消息被撤回事件
     * @param botId 传入机器人ID
     * @param event 事件对象
     */
    @SneakyThrows
    public static void PublicMessageDeleteEventPush(String botId, PublicMessageEvent event){
        for (SiriusApplication app : apps) {
            app.appInfo().getMethods().get("public_message_delete_event").invoke(app,botId,event);
        }
    }
}

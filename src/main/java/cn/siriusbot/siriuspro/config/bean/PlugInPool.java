package cn.siriusbot.siriuspro.config.bean;

import cn.siriusbot.siriuspro.bot.BotApi;
import cn.siriusbot.siriuspro.bot.annotation.OnEventMessage;
import cn.siriusbot.siriuspro.bot.application.SiriusApplication;
import cn.siriusbot.siriuspro.bot.pojo.e.MessageType;
import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
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
import cn.siriusbot.siriuspro.uitls.ApplicationUtils;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Component
@Log4j2
public class PlugInPool {

    List<SiriusApplication> apps = new CopyOnWriteArrayList<>();

    HashMap<String, Class<? extends MessageBody>> map = new HashMap<>();

    @Autowired
    BotApi botApi;

    @PostConstruct
    void init() {
        loadApps();
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
     * 加载全部应用
     */
    public void loadApps() {
        File file = new File(ApplicationUtils.appsPath);
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (File appFile : files) {
            SiriusApplication app = getAppInstance(appFile);
            if (app != null) {
                log.info("应用[" + app.appInfo().getAppName() + "-" + app.appInfo().getAppAuthor() + "]加载成功！");
                apps.add(app);
            }
        }

    }


    /**
     * 获取应用实例
     *
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
                        application.SiriusAppInit(botApi);
                        return application;
                    }
                } catch (Throwable e) {
                    log.error(e);
                }

            }
        }
        log.error("插件加载失败 -> " + jarFile.getName() + "中未有继承SiriusApplication的类，可能不是天狼星应用");
        return null;
    }

    /**
     * 推送事件-异步调用
     */
    @Async
    public void putEvent(String botId, BotEventMessage message) {
        for (SiriusApplication app : apps) {
            Class<? extends SiriusApplication> clazz = app.getClass();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                OnEventMessage annotation = method.getAnnotation(OnEventMessage.class);
                if (annotation != null) {
                    MessageType type = annotation.type();
                    Class<? extends MessageBody> messageBodyClass = map.get(type.getTypeName());
                    if (messageBodyClass != message.getClazz()){
                        continue;
                    }
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 2 || parameterTypes[0] != String.class){
                        log.error(String.format("插件[%s]监听的方法(%s)不规范，必须只包含两位参数，" +
                                "第一位为BotId，第二位为事件Body", app.appInfo().getAppName(), method.getName()));
                        continue;
                    }
                    if (parameterTypes[1] != messageBodyClass){
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
                                e
                        ));
                    }
                }
            }
        }
    }
}

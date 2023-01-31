package cn.siriusbot.siriuspro.config.bean;

import cn.siriusbot.siriuspro.bot.BotApi;
import cn.siriusbot.siriuspro.bot.annotation.OnEventMessage;
import cn.siriusbot.siriuspro.bot.application.SiriusApplication;
import cn.siriusbot.siriuspro.bot.event.PlugInEvent;
import cn.siriusbot.siriuspro.bot.plugin.JavaPlugInClient;
import cn.siriusbot.siriuspro.bot.plugin.PlugInFactory;
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
public class JavaPlugInPool {

    List<SiriusApplication> apps = new CopyOnWriteArrayList<>();

    @Autowired
    BotApi botApi;

    @Autowired
    PlugInFactory factory;

    @PostConstruct
    void init() {
        loadApps();
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
                factory.add(
                        new JavaPlugInClient(app, app.appInfo())
                );
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

}
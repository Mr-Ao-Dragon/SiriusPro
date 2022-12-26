package cn.siriusbot.siriuspro.application;

import cn.siriusbot.siriuspro.logger.SiriusLogger;
import cn.siriusbot.siriuspro.message.PublicMessageEvent.PublicMessageEvent;
import lombok.SneakyThrows;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ApplicationManager {
    public static List<SiriusApplication> apps = new ArrayList<>();

    /**
     * 加载全部应用
     */
    @SneakyThrows
    public static void loadApps() {
        File file = new File(ApplicationUtils.appsPath);
        for (File appFile : file.listFiles()) {
            SiriusApplication app = getAppInstance(appFile);
            if (app != null) {
                System.out.println(SiriusLogger.getFormatLogString("应用"+"["+app.appInfo.getAppName()+"-"+app.appInfo.getAppAuthor()+"]加载成功！",36,1));
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
    public static SiriusApplication getAppInstance(File app) {

        //获取类加载器
        URLClassLoader appClass = new URLClassLoader(new URL[]{new URL("file:" + app.getAbsolutePath())});
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
                    SiriusApplication application = (SiriusApplication) aClass.newInstance();
                    if (application instanceof SiriusApplication) {
                        return  application;
                    }
                } catch (Throwable e) {

                }

            }
        }
        System.err.println(jarFile.getName() + "中未有继承SiriusApplication的类，可能不是天狼星应用");
        return null;
    }


    /**
     * 机器人被@消息事件推送
     * @param botId 传入机器人ID
     * @param event 事件对象
     */
    @SneakyThrows
    public static void PublicMessageEventPush(String botId, PublicMessageEvent event){
        //循环推送事件至所有应用
        for (SiriusApplication app : apps) {
            app.appInfo.getMethods().get("public_message_event").invoke(app,botId,event);
        }
    }
}

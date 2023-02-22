package cn.siriusbot.siriuspro.bot.api.proxy;

import cn.siriusbot.siriuspro.bot.api.DMS_Api;
import cn.siriusbot.siriuspro.bot.api.MessageApi;
import cn.siriusbot.siriuspro.bot.application.SiriusApplicationInfo;
import cn.siriusbot.siriuspro.config.bean.StatisticsPool;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Proxy;

public class ApiStatisticsProxy {

    SiriusApplicationInfo info;
    StatisticsPool statisticsPool;

    public ApiStatisticsProxy(SiriusApplicationInfo info, StatisticsPool statisticsPool) {
        this.info = info;
        this.statisticsPool = statisticsPool;
    }

    private String getName(String s) {
        return s.substring(s.lastIndexOf(".") + 1);
    }

    @SuppressWarnings("unchecked")
    public <T extends ApiProxy> T getProxy(Class<T> c, T obj) {
        return (T) Proxy.newProxyInstance(c.getClassLoader(),
                new Class[]{c},
                (proxy, method, args) -> {
                    String msgId = null;
                    if (c == MessageApi.class) {
                        switch (getName(method.getName())) {
                            case "sendMessage", "sendImageAndTextMessage" -> msgId = (String) args[4];
                            case "sendMarkdownMessage", "deleteMessageById" -> msgId = (String) args[2];
                            case "sendArkMessage", "sendEmbedMessage" -> msgId = (String) args[3];
                        }
                    }
                    if (c == DMS_Api.class) {
                        switch (getName(method.getName())) {
                            case "sendMessage", "sendImageAndTextMessage" -> msgId = (String) args[4];
                            case "sendMarkdownMessage", "deleteMessageById" -> msgId = (String) args[2];
                            case "sendArkMessage", "sendEmbedMessage" -> msgId = (String) args[3];

                        }
                    }
                    Object invoke = method.invoke(obj, args);
                    if (!ObjectUtils.isEmpty(msgId)) {
                        statisticsPool.responseEventByMsgId(this.info.getPackageName(), msgId);
                    }
                    return invoke;
                });
    }

}

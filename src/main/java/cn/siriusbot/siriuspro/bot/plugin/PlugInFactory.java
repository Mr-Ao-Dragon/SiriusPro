package cn.siriusbot.siriuspro.bot.plugin;

import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Log4j2
public class PlugInFactory {
    Map<String, PlugInClient> plugInClientMap = new ConcurrentHashMap<>();



    public void putEvent(String botId, BotEventMessage body) {
        for (String key : plugInClientMap.keySet()) {
            this.putEvent(plugInClientMap.get(key), botId, body);
        }
    }

    @Async
    public void putEvent(PlugInClient plugInClient, String botId, BotEventMessage body) {
        plugInClient.putEvent(botId, body);
    }

    /**
     * 添加插件
     *
     * @param client
     */
    public void add(PlugInClient client) {
        String packageName = client.getInfo().getPackageName();
        if (ObjectUtils.isEmpty(packageName)) {
            log.error(String.format("加载插件[%s]失败，未设置唯一包名。", client.getInfo().getAppName()));
        }
        if (this.plugInClientMap.containsKey(packageName)) {
            PlugInClient temp = this.plugInClientMap.get(packageName);
            log.warn(String.format("插件[%s]的包名与[%s]插的包名重复！", client.getInfo().getAppName(), temp.getInfo().getAppName()));
        }
        plugInClientMap.put(packageName, client);
    }
}

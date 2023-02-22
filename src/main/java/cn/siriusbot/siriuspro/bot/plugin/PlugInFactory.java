package cn.siriusbot.siriuspro.bot.plugin;

import cn.siriusbot.siriuspro.bot.pojo.event.BotEventMessage;
import cn.siriusbot.siriuspro.web.R.R;
import cn.siriusbot.siriuspro.web.pojo.BotHttpRequest;
import cn.siriusbot.siriuspro.web.websocket.surface.WebsocketSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Log4j2
public class PlugInFactory {
    Map<String, PlugInClient> plugInClientMap = new ConcurrentHashMap<>();


    /**
     * 获取插件数量
     * @return
     */
    public int getCount(){
        return plugInClientMap.size();
    }

    public void putEvent(String botId, BotEventMessage body) {
        for (String key : plugInClientMap.keySet()) {
            this.putEvent(plugInClientMap.get(key), botId, body);
        }
    }

    @Async
    public void putEvent(PlugInClient plugInClient, String botId, BotEventMessage body) {
        plugInClient.putEvent(botId, body);
    }

    public R putWebEvent(String packageName, BotHttpRequest request) {
        if (plugInClientMap.containsKey(packageName)){
            return plugInClientMap.get(packageName).webPost(request);
        }
        return null;
    }


    public void putExpandWebSocketOpen(WebsocketSession session){
        for (String key : plugInClientMap.keySet()) {
            PlugInClient plugInClient = plugInClientMap.get(key);
            if (plugInClient instanceof ExpandClient expandClient){
                expandClient.putWebSocketOpen(session);
            }
        }
    }

    public void putExpandWebSocketClose(WebsocketSession session){
        for (String key : plugInClientMap.keySet()) {
            PlugInClient plugInClient = plugInClientMap.get(key);
            if (plugInClient instanceof ExpandClient expandClient){
                expandClient.putWebSocketClose(session);
            }
        }
    }

    public void putExpandWebSocketEvent(WebsocketSession session, String message){
        for (String key : plugInClientMap.keySet()) {
            PlugInClient plugInClient = plugInClientMap.get(key);
            if (plugInClient instanceof ExpandClient expandClient){
                expandClient.putWebSocketMessage(session, message);
            }
        }
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
        System.out.println(packageName);
        if (this.plugInClientMap.containsKey(packageName)) {
            PlugInClient temp = this.plugInClientMap.get(packageName);
            log.warn(String.format("插件[%s]的包名与[%s]插的包名重复！", client.getInfo().getAppName(), temp.getInfo().getAppName()));
        }
        plugInClientMap.put(packageName, client);
    }

    public void remove(String packageName){
        this.plugInClientMap.remove(packageName);
    }

    public PlugInClient getPlugInClientBySessionId(String session){
        for (String packageName : plugInClientMap.keySet()){
            PlugInClient client = plugInClientMap.get(packageName);
            if (client.getUuid().equals(session)){
                return client;
            }
        }
        return null;
    }

    public List<PlugInClient> queryAllClient(){
        return new ArrayList<>(plugInClientMap.values());
    }
}

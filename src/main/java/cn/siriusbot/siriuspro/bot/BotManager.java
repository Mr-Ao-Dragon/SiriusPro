package cn.siriusbot.siriuspro.bot;

import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import cn.siriusbot.siriuspro.websocket.SiriusWebSocketClient;
import com.alibaba.fastjson.JSONObject;
import lombok.NonNull;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 机器人管理类
 */
@Component
public class BotManager {

    /**
     * Bot客户端池
     */
    private final Map<String, SiriusBotClient> botVector = new ConcurrentHashMap<>();

    /**
     * 验证Bot
     *
     * @param siriusBotClient 机器人对象
     * @return 成功返回true，失败返回false
     */
    @SneakyThrows
    public boolean AuthBot(SiriusBotClient siriusBotClient){

        if (!this.botExist(siriusBotClient.getInfo().getBotId()))
            this.addBot(siriusBotClient);
        //获取当前机器人索引
        if (siriusBotClient.getInfo().isSandBox()) {
            siriusBotClient.getSocket().setOpenUrl("https://sandbox.api.sgroup.qq.com/");
        } else if (!(siriusBotClient.getInfo().isSandBox())) {
            siriusBotClient.getSocket().setOpenUrl("https://api.sgroup.qq.com/");
        }
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "gateway").build();

        Response response = SiriusHttpUtils.getRequest(siriusBotClient, request);
        JSONObject json;
        try {
            json = JSONObject.parseObject(Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        String url = json.getString("url");
        if (url == null)
            return false;
        siriusBotClient.getSocket().setWebSocketUri(new URI(json.getString("url")));
        siriusBotClient.setWebSocketClient(new SiriusWebSocketClient(siriusBotClient, siriusBotClient.getSocket().getWebSocketUri()));
        //初始化机器人信息
        return response.code() == 200;
    }


    /**
     * 获取机器人个数
     *
     * @return
     */
    public int getBotCount() {
        return botVector.size();
    }

    /**
     * 根据机器人ID获取机器人
     *
     * @param botId
     * @return 有则返回Bot对象，否则返回null
     */
    public SiriusBotClient getBotByBotId(String botId) {
        if (!botVector.containsKey(botId)){
            throw new RuntimeException("找不到BotId对应的机器人");
        }
        return botVector.get(botId);
    }

    /**
     * 指定机器人是否存在
     *
     * @param botId 机器人ID
     * @return 存在返回true，不存在返回false
     */
    public boolean botExist(String botId) {
        return botVector.containsKey(botId);
    }

    /**
     * 登录指定机器人
     *
     * @param botId 机器人ID
     */
    public void loginBot(String botId) {
        SiriusBotClient siriusBotClient = getBotByBotId(botId);
        siriusBotClient.getWebSocketClient().connect();
    }


    /**
     * 登出指定机器人
     *
     * @param botId 机器人ID
     */
    public void logoutBot(String botId) {
        SiriusBotClient siriusBotClient = getBotByBotId(botId);
        siriusBotClient.getWebSocketClient().close();
        botVector.remove(botId);
    }

    /**
     * 添加机器人
     *
     * @param siriusBotClient 传入机器人对象
     */
    public void addBot(SiriusBotClient siriusBotClient) {
        if (botExist(siriusBotClient.getInfo().getBotId())) {
            throw new MsgException(90001, "机器人已存在！");
        }
        botVector.put(siriusBotClient.getInfo().getBotId(), siriusBotClient);
    }

    public void update(@NonNull SiriusBotClient siriusBotClient){
        if (!botVector.containsKey(siriusBotClient.getInfo().getBotId())){
            throw new MsgException(90002, "机器人不存在！");
        }
        botVector.put(siriusBotClient.getInfo().getBotId(), siriusBotClient);
    }


    /**
     * 获取机器人列表
     *
     * @return 机器人列表
     */
    public Map<String, SiriusBotClient> getAllBot() {
        return botVector;
    }
}

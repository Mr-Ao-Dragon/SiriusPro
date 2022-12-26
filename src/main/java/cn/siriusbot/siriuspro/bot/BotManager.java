package cn.siriusbot.siriuspro.bot;

import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import cn.siriusbot.siriuspro.webapi.pojo.BotInfo;
import cn.siriusbot.siriuspro.websocket.SiriusWebSocketClient;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.Request;
import okhttp3.Response;
import org.java_websocket.drafts.Draft_6455;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 机器人管理类
 */
public class BotManager{

    /**
     * 验证Bot
     *
     * @param bot 机器人对象
     * @return 成功返回true，失败返回false
     */
    @SneakyThrows
    public static boolean AuthBot(Bot bot) throws URISyntaxException {

        if(!BotManager.botExist(bot.getBotId()))
            BotManager.addBot(bot);
        //获取当前机器人索引
        if (bot.isSandBox()) {
            bot.setOpenUrl("https://sandbox.api.sgroup.qq.com/");
        } else if (!(bot.isSandBox())) {
            bot.setOpenUrl("https://api.sgroup.qq.com/");
        }
        Request request = new Request.Builder().url(bot.getOpenUrl() + "gateway").build();

        Response response = SiriusHttpUtils.getRequest(bot, request);
        JSONObject json;
        try {
            json = JSONObject.parseObject(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        String url = json.getString("url");
        if (url == null)
            return false;
        bot.setWebSocketUri(new URI(json.getString("url")));
        bot.setWebSocketClient(new SiriusWebSocketClient(bot,bot.getWebSocketUri()));
        //初始化机器人信息
        return response.code() == 200;
    }

    public static Map<Integer, Bot> botVector = new HashMap<>();

    /**
     * 获取机器人个数
     *
     * @return
     */
    public static Integer getBotCount() {
        return botVector.size();
    }

    /**
     * 根据机器人ID获取机器人
     *
     * @param botId
     * @return 有则返回Bot对象，否则返回null
     */
    public static Bot getBotByBotId(String botId) {
        Set<Integer> keys = botVector.keySet();
        for (Integer key : keys) {
            Bot bot = botVector.get(key);
            if (bot.getBotId().equals(botId)) {
                return bot;
            }
        }
        return null;
    }

    /**
     * 指定机器人是否存在
     *
     * @param botId 机器人ID
     * @return 存在返回true，不存在返回false
     */
    public static boolean botExist(String botId) {
        Set<Integer> keys = botVector.keySet();
        for (Integer key : keys) {
            if (botVector.get(key).getBotId().equals(botId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 登录指定机器人
     *
     * @param botId 机器人ID
     * @return 登录结果，成功返回true,失败返回false;
     */
    public static void loginBot(String botId) {
        Bot bot = getBotByBotId(botId);
        if (bot == null)
            return;
        bot.getWebSocketClient().connect();
    }

    /**
     * 添加机器人
     *
     * @param bot 传入机器人对象
     */
    public static String addBot(Bot bot) {
        if (botExist(bot.getBotId())) {
            return "机器人已存在！";
        }
        botVector.put(botVector.size() + 1, bot);
        return "Id:" + bot.getBotId() + "添加成功！";
    }

    /**
     * 根据BotID返回索引
     *
     * @param botId 机器人ID
     * @return 找到返回索引，未找到返回-1
     */
    public static int getIdByBotId(String botId) {
        Set<Integer> keys = botVector.keySet();
        for (Integer key : keys) {
            if (botVector.get(key).getBotId().equals(botId)) {
                return key;
            }
        }
        return -1;
    }

    /**
     * 获取机器人列表
     * @return 机器人列表
     */
    public static Map<Integer, Bot> getAllBot(){
        return botVector;
    }
}

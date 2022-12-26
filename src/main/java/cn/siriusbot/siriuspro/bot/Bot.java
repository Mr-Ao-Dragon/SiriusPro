package cn.siriusbot.siriuspro.bot;

import cn.siriusbot.siriuspro.entity.impl.User;
import cn.siriusbot.siriuspro.message.MessageManager;
import cn.siriusbot.siriuspro.timer.SiriusTimer;
import cn.siriusbot.siriuspro.webapi.pojo.BotInfo;
import cn.siriusbot.siriuspro.websocket.SiriusWebSocketClient;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import okhttp3.OkHttpClient;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.TimerTask;

@Data
@Accessors(chain = true)
public class Bot{
    public Bot() {
    }

    public Bot(String botId, String token, Bot.botType botType, boolean sandBox) {
        this.botId = botId;
        this.token = token;
        this.botType = botType;
        this.sandBox = sandBox;
    }

    /**
     * 机器人ID
     */
    String botId;

    /**
     * 机器人token
     */
    String token;

    /**
     * 机器人类型，参考枚举botType，默认为公域
     */
    botType botType ;

    /**
     * 是否为沙箱模式,false非沙箱环境,true沙箱环境
     */
    boolean sandBox;



    /**
     * 机器人频道场景基本信息
     */
     User user;


    /**
     * 用于openApi请求的url
     */
     String openUrl;

    /**
     * 用于请求openApi的http客户端
     */
     OkHttpClient httpClient = new OkHttpClient();

    /**
     * 理论上是消息顺序
     */
     int s;

    /**
     * 当前会话ID
     */
     String session_id;

    /**
     * 用于WebSocket客户端连接的URI
     */
     URI webSocketUri;

    /**
     * 心跳间隔,毫秒
     */
     int heartBeat;

    /**
     * 心跳定时器
     */
     SiriusTimer heartBeatTimer;

    /**
     * 心跳任务
     */
     TimerTask sendHeartBeat;

    /**
     * webSocketClient客户端
     */
    SiriusWebSocketClient webSocketClient = new SiriusWebSocketClient();
    /**
     * PUBLIC_TYPE公域类型,_TYPE私域类型
     */
    public enum botType {
        PUBLIC_TYPE(0),
        PRIVATE_TYPE(1);

         Integer value;
        botType(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}

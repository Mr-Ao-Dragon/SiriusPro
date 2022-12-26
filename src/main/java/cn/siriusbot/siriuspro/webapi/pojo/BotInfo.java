package cn.siriusbot.siriuspro.webapi.pojo;

import cn.siriusbot.siriuspro.bot.Bot;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BotInfo {
    /**
     * 机器人ID
     */
    String botId;

    /**
     * 机器人token
     */
    String token;

    /**
     * 机器人类型,0:公域,1:私域
     */
    Integer botType;

    /**
     * 是否为沙箱模式,false非沙箱环境,true沙箱环境
     */
    boolean sandBox;

}

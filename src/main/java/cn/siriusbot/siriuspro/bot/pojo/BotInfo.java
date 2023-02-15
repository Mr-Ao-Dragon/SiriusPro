package cn.siriusbot.siriuspro.bot.pojo;

import cn.siriusbot.siriuspro.bot.pojo.e.BotType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 机器人信息
 */
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
     * 机器人类型，参考枚举botType，默认为公域
     */
    BotType botType ;

    /**
     * 是否为沙箱模式,false非沙箱环境,true沙箱环境
     */
    boolean sandBox;

    /**
     * 机器人昵称
     */
    String username;

    /**
     * 是否在线 0 未登录 1 登录中 2 已登录  3 登录失败
     */
    int state = 0;

    /**
     * 错误原因
     */
    String errorInfo;
}

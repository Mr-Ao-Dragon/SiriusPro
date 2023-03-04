package cn.siriusbot.siriuspro.admin.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Robot {
    Integer id;

    /**
     * 机器人类型 - 0公域 1私域
     */
    Integer botType;

    /**
     * 是否沙河模式
     */
    Boolean sandBox;

    /**
     * 机器人ID
     */
    String botId;
    /**
     * 机器人密钥
     */
    String token;

    /**
     * 自动登录
     */
    Boolean autoLogin;

    /**
     * 是否在线 0 未登录 1 登录中 2 已登录  3 登录失败
     */
    Integer state = 0;

    /**
     * 机器人昵称
     */
    String username;


    /**
     * 错误原因
     */
    String errorInfo;


    List<Intent> intents;

    public static final int STATE_READY = 0;    // 未登录
    public static final int STATE_PROCEED = 1;    // 登录中
    public static final int STATE_ONLINE = 2;    // 已登录
    public static final int STATE_ERROR = 3;    // 登录异常
}

package cn.siriusbot.siriuspro.admin.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Robot {
    Integer id;

    /**
     * 机器人类型 - 公域 私域
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
    Boolean autoLogon;
}

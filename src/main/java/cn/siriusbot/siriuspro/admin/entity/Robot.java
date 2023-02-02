package cn.siriusbot.siriuspro.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
    Boolean autoLogin;

    /**
     * 是否在线 0 未登录 1 已登录 2 登录中 3 登录失败
     */
    @TableField(exist = false)
    Integer state = 0;

    public static final int STATE_READY = 0;    // 未登录
    public static final int STATE_ONLINE = 1;    // 已登录
}

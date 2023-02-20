package cn.siriusbot.siriuspro.web.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BotHttpRequest {
    /**
     * 请求者的权限 -1 为未登录 0 为最高管理员 1,2,3...权限大小依次递减
     */
    int power;

    /**
     * 请求者IP
     */
    String sourceIp;

    /**
     * 本地IP
     */
    String localIp;

    /**
     * 请求名称
     */
    String name;

    /**
     * 请求Body体
     */
    String body;
}

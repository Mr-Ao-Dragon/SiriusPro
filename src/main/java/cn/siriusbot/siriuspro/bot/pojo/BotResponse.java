package cn.siriusbot.siriuspro.bot.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 机器人专用http请求响应体
 */
@Data
@Accessors(chain = true)
public class BotResponse {

    /**
     * 数据体
     */
    String body;

    /**
     * 响应码
     */
    Integer code;

    /**
     * X-Tps-trace-ID 链路追踪ID，用于定位问题
     */
    String traceId;
}

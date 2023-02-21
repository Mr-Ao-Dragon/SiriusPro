package cn.siriusbot.siriuspro.config.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatisticsData {

    /**
     * 机器人数量
     */
    int botNum;

    /**
     * 插件数量
     */
    int plugInNUm;

    /**
     * 消息数量
     */
    int msgNum;

    /**
     * 运行时间(毫秒)
     */
    long runTime;

    /**
     * 机器人在线率(%)
     */
    double onLineRate;

    /**
     * 消息响应率(%)
     */
    double messageResponseRate;

    /**
     * CPU占用率(%)
     */
    double cpuUsage;

    /**
     * 内存占用率(%)
     */
    double memoryUsage;

    /**
     * 虚拟机内存占用率(%)
     */
    double virtualMemoryUsage;
}

package cn.siriusbot.siriuspro.bot.application;

import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 天狼星应用
 */
@Data
@Accessors(chain = true)
public class SiriusApplicationInfo {
    /**
     * 应用包名，用于判断应用的唯一标识，比如 cn.siriusbot.siriuspro
     */
    private String packageName;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用作者
     */
    private String appAuthor;

    /**
     * 应用介绍
     */
    private String appDesc;

    /**
     * 应用版本
     */
    private String appVersion;

    /**
     * 应用路径
     */
    private String appPath;

    /**
     * 应用图标
     */
    private byte[] appIcon;

}

package cn.siriusbot.siriuspro.admin.entity;

import cn.siriusbot.siriuspro.bot.application.SiriusApplicationInfo;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Plugins {

    private Integer id;

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

    public Plugins() {
    }

    public Plugins(SiriusApplicationInfo info) {
        this.packageName = info.getPackageName();
        this.appName = info.getAppName();
        this.appAuthor = info.getAppAuthor();
        this.appDesc = info.getAppDesc();
        this.appVersion = info.getAppVersion();
    }
}

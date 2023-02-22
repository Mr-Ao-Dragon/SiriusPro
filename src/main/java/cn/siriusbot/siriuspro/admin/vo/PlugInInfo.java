package cn.siriusbot.siriuspro.admin.vo;

import cn.siriusbot.siriuspro.bot.application.SiriusApplicationInfo;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PlugInInfo {
    /**
     * 基本信息
     */
    SiriusApplicationInfo basic;

    /**
     * 响应次数
     */
    int responseNum = 0;
}

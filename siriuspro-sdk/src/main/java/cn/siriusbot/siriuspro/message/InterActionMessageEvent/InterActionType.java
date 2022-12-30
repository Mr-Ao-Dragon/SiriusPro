package cn.siriusbot.siriuspro.message.InterActionMessageEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 不知道干嘛的
 */
@Accessors(chain = true)
@Data
public class InterActionType {
    /**
     * 类型
     */
    private Integer type;


    /**
     * 主要数据
     */
    private Resolved resolved;
}

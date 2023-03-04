package cn.siriusbot.siriuspro.admin.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Intent {

    Integer id;

    /**
     * 机器人id
     */
    String robotId;

    /**
     * 订阅事件
     */
    Integer intentsType;

    /**
     * 事件名称
     */
    String intentsName;

}

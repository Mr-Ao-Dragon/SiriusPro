package cn.siriusbot.siriuspro.admin.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors
public class Intent {

    Integer id;

    /**
     * 机器人id
     */
    Integer robotId;

    /**
     * 订阅事件
     */
    Integer intentsType;

}

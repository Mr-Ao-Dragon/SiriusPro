package cn.siriusbot.siriuspro.message.InterActionMessageEvent;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 互动事件详情对象
 */
@Data
@Accessors(chain = true)
public class InterActionEventDObject {


    /**
     * 包含主要数据
     */
    private InterActionType data;


    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 不确定作用
     */
    private String id;

    /**
     * 不确定作用
     */
    private Integer type;

    /**
     * 应用id
     */
    private String application_id;

    /**
     * 子频道ID
     */
    private String channel_id;

    /**
     * 版本
     */
    private String version;
}
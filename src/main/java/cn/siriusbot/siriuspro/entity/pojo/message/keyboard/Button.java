package cn.siriusbot.siriuspro.entity.pojo.message.keyboard;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 按钮对象
 */
public class Button {
    /**
     * 按钮id
     */
    private String id;
    /**
     * 按钮渲染对象，用于设定按钮的显示效果
     */
    private RenderData render_data;
    /**
     * 用户点击按钮后的操作对象
     */
    private Action action;
}
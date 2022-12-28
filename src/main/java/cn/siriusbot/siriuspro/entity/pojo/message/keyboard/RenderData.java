package cn.siriusbot.siriuspro.entity.pojo.message.keyboard;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 按钮渲染对象
 */
public class RenderData {
    /**
     * 按钮上的文字
     */
    private String label;
    /**
     * 点击后按钮上文字
     */
    private String visited_label;
    /**
     * 按钮样式,参考RenderStyle
     */
    private Integer style;

    /**
     * 按钮样式
     */
    public enum RENDER_STYLE {
        /**
         * 灰色边框
         */
        GRAY_FRAME(0),

        /**
         * 蓝色边框
         */
        BLUE_FRAME(1);
        private int value;

        RENDER_STYLE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}

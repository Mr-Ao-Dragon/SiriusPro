package cn.siriusbot.siriuspro.bot.api.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本-段落属性对象
 */
@Data
@Accessors(chain = true)
public class ParagraphProps {
    /**
     * 段落对其方向属性类型,可参考枚举类型Alignment
     */
    private Integer alignment;

    public enum ALIGNMENT{
        /**
         * 左对齐
         */
        ALIGNMENT_LEFT(0),

        /**
         * 居中对齐
         */
        ALIGNMENT_MIDDLE(1),

        /**
         * 右对齐
         */
        ALIGNMENT_RIGHT(2);

        private Integer value;

        ALIGNMENT(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}

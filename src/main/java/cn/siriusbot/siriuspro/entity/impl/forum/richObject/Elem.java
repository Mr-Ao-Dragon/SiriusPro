package cn.siriusbot.siriuspro.entity.impl.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本-元素列表结构对象
 */
@Data
@Accessors(chain = true)
public class Elem {
    /**
     * 文本元素
     */
    private TextElem text;

    /**
     * 图片元素
     */
    private ImageElem image;

    /**
     *视频元素
     */
    private VideoElem video;

    /**
     * URL元素
     */
    private UrlElem url;

    /**
     * 元素类型,请参考枚举->ELEM_TYPE
     */
    private Integer type;

    /**
     * 元素类型
     */
    public enum ELEM_TYPE{

        /**
         * 文本
         */
        ELEM_TYPE_TEXT(1),

        /**
         * 图片
         */
        ELEM_TYPE_IMAGE(2),

        /**
         * 视频
         */
        ELEM_TYPE_VIDEO(3),

        /**
         * URL
         */
        ELEM_TYPE_URL(4);
        private Integer value;
        ELEM_TYPE(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }


}

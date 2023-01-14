package cn.siriusbot.siriuspro.bot.api.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 富文本内容对象
 */
@Data
@Accessors(chain = true)
public class RichObject {

    /**
     * 富文本类型，请参考RichType
     */
    private Integer type;

    /**
     * TexfInfo文本
     */
    private TextInfo text_info;

    /**
     * AtInfo @内容
     */
    private AtInfo at_info;

    /**
     * URLInfo链接
     */
    private UrlInfo url_info;

    /**
     * EmojiInfo表情
     */
    private EmojiInfo emoji_info;

    /**
     * 提到的子频道
     */
    private ChannelInfo channel_info;

    public enum RICH_TYPE{

        /**
         * 普通文本
         */
        TEXT(1),

        /**
         * at信息
         */
        at(2),

        /**
         * url信息
         */

        URL(3),

        /**
         * 表情
         */
        EMOJI(4),

        /**
         * #子频道
         */

        CHANNEL(5),

        /**
         * 视频
         */
        VIDEO(10),

        /**
         * 图片
         */
        IMAGE(11);

        private Integer value;

        RICH_TYPE(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}

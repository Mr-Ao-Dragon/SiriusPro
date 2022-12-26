package cn.siriusbot.siriuspro.entity.impl.emoji;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 表情对象
 */
@Data
@Accessors(chain = true)
public class Emoji {
    /**
     * 表情ID
     */
    private String id;

    /**
     * 表情类型，参考 EmojiEype
     */
    private Integer type;
    public enum  TYPE{

        /**
         * 系统表情
         */
        SYSTEM_EMOJI(1),

        /**
         * emoji表情
         */
        EMOJI_TYPE(2);
        public Integer value;
        TYPE(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}

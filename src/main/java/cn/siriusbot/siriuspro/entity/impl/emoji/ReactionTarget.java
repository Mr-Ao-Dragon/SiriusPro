package cn.siriusbot.siriuspro.entity.impl.emoji;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ReactionTarget {
    /**
     * 表情对象ID
     */
    private String id;

    /**
     * 表态对象类型,参考ReactionTargetType
     */
    private Integer type;
    public enum REACTION_TARGET_TYPE{

        /**
         * 文本消息
         */
        TEXT(0),

        /**
         * 帖子
         */
        POSTS(1),

        /**
         * 评论
         */
        COMMENT(2),

        /**
         * 回复
         */

        REPLY(3);

        private Integer value;

        REACTION_TARGET_TYPE(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}

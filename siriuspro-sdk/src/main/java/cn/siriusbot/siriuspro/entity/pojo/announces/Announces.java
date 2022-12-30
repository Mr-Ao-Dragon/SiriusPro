package cn.siriusbot.siriuspro.entity.pojo.announces;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Announces{
    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 子频道ID
     */
    private String channel_id;

    /**
     * 消息ID
     */
    private String message_id;

    /**
     * 公告类别
     */

    private Integer announces_type;

    /**
     * 推荐子频道详情列表
     */
    private List<RecommendChannel> recommend_channels;




    public enum ANNOUNCES_TYPE {


        /**
         * 成员公告
         */
        MEMBER(0),

        /**
         * 欢迎公告
         */
        WELCOME(1),
        ;

        private Integer value;

        ANNOUNCES_TYPE(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}

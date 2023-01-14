package cn.siriusbot.siriuspro.bot.api.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 论坛帖子审核结果事件对象
 */
@Data
@Accessors(chain = true)
public class AuditResult {

    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 子频道ID
     */
    private String channel_id;

    /**
     * 作者ID
     */
    private String author_id;

    /**
     * 主题ID
     */
    private String thread_id;

    /**
     * 帖子ID
     */
    private String post_id;

    /**
     * 回复ID
     */
    private String reply_id;

    /**
     * 审核类型 请参考枚举类型->AuditType
     */
    private Integer type;

    /**
     * 审核结果,0:成功,1:失败
     */
    private Integer result;

    /**
     * result不为0时的错误信息
     */
    private String err_msg;
    public enum AUDIT_TYPE{
        /**
         * 帖子
         */
        PUBLISH_THREAD(1),

        /**
         * 评论
         */
        PUBLISH_POST(2),

        /**
         * 回复
         */
        PUBLISH_REPLY(3);
        private Integer value;
        AUDIT_TYPE(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}

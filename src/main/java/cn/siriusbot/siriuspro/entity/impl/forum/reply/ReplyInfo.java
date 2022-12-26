package cn.siriusbot.siriuspro.entity.impl.forum.reply;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 回复内容详情对象
 *
 * 回复事件包含的回复内容消息详情
 */
@Data
@Accessors(chain = true)
public class ReplyInfo {

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
     * 回复内容
     */
    private String content;

    /**
     * 回复时间
     */
    private String date_time;
}

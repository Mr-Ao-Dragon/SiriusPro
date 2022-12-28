package cn.siriusbot.siriuspro.entity.pojo.forum.post;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 帖子(回复)详情对象
 *
 * 帖子事件包含的帖子内容信息
 */
@Data
@Accessors(chain = true)
public class PostInfo {
    /**
     * 主题ID
     */
    private String thread_id;

    /**
     * 帖子(回复)ID
     */

    private String post_id;

    /**
     * 帖子(回复)内容
     */
    private String content;

    /**
     * 评论时间
     */
    private String data_time;
}

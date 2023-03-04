package cn.siriusbot.siriuspro.bot.api.pojo.forum.thread;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 主题详情对象
 *
 * 帖子事件保护你的主题内容像关系信息
 */
@Data
@Accessors(chain = true)
public class ForumThreadInfo {
    /**
     * 主题ID
     */
    private String thread_id;

    /**
     * 主题标题
     */
    private String title;

    /**
     * 主题内容
     */
    private String content;

    /**
     * 发表时间
     */
    private String date_time;

}

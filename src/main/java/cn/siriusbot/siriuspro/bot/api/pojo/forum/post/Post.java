package cn.siriusbot.siriuspro.bot.api.pojo.forum.post;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 评论对象
 *
 * 话题频道内对主题的评论称为帖子
 * 话题频道内对帖子主题评论或删除时产生的事件中包含该对象
 *
 */
@Data
@Accessors(chain = true)
public class Post {
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
     * 帖子内容
     */
    private PostInfo post_info;


}

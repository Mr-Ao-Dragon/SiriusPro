package cn.siriusbot.siriuspro.bot.pojo.message.ForumEvent;


import cn.siriusbot.siriuspro.entity.pojo.forum.post.PostInfo;
import cn.siriusbot.siriuspro.entity.pojo.forum.reply.ReplyInfo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 私域论坛事件详情
 */
@Data
@Accessors(chain = true)
public class ForumEventDObject {
    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 用户id
     */
    private String author_id;

    /**
     * 评论详情对象
     */
    private ReplyInfo reply_info;

    /**
     * 子频道ID
     */
    private String channel_id;

    /**
     * 回复详情对象
     */
    private PostInfo post_info;

}

package cn.siriusbot.siriuspro.entity.impl.forum.reply;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 评论回复对象(当有人对某条评论进行评论时的对象)
 *
 * 话题频道对帖子回复或删除时生成该事件中，包含该对象
 * 话题频道内，对帖子的"评论"称为"回复"
 */
@Data
@Accessors(chain = true)
public class Reply {

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
     * 回复详情内容对象
     */
    private ReplyInfo reply_info;
}

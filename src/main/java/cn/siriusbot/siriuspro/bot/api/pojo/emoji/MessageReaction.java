package cn.siriusbot.siriuspro.bot.api.pojo.emoji;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 表情表态对象
 */
@Data
@Accessors(chain = true)
public class MessageReaction  {

    /**
     * 用户ID
     */
    private String user_id;

    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 子频道ID
     */
    private String channel_id;

    /**
     * 表态对象
     */
    private ReactionTarget target;

    /**
     * 表态所用表情
     */
    private Emoji emoji;

}

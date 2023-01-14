package cn.siriusbot.siriuspro.bot.api.pojo.announces;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 子频道推荐对象
 */
@Data
@Accessors(chain = true)
public class RecommendChannel {
    /**
     *子频道id
     */
    String channel_id;

    /**
     * 推荐语
     */
    String introduce;
}

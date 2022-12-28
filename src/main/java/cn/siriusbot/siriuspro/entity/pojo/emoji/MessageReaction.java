package cn.siriusbot.siriuspro.entity.pojo.emoji;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.MessageReactionApi;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

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

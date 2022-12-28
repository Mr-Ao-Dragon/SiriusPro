package cn.siriusbot.siriuspro.entity.pojo;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.DMS_Api;
import cn.siriusbot.siriuspro.entity.pojo.message.Message;
import cn.siriusbot.siriuspro.entity.pojo.message.MessageMarkdown;
import cn.siriusbot.siriuspro.entity.pojo.message.MessageReference;
import cn.siriusbot.siriuspro.entity.pojo.message.ark.MessageArk;
import cn.siriusbot.siriuspro.entity.pojo.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.*;

import java.io.File;

@Data
@Accessors(chain = true)
/**
 * 私信会话对象
 */
public class DMS {
    /**
     * 私信会话关联的频道ID
     */
    private String guild_id;

    /**
     * 私信会话关联的子频道ID
     */
    private String channel_id;

    /**
     * 私信会话的创建时间戳
     */
    private String create_time;


}

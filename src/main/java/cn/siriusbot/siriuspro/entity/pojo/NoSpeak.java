package cn.siriusbot.siriuspro.entity.pojo;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.NoSpeakApi;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.List;


@Data
@Accessors(chain = true)
public class NoSpeak{

    /**
     * 禁言到期时间戳
     */
    private String mute_end_timestamp;

    /**
     * 禁言秒数
     */
    private String seconds;

    /**
     * 禁言人员列表
     */
    private List<String> user_ids;



}

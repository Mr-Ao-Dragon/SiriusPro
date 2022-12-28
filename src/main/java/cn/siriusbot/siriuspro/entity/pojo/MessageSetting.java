package cn.siriusbot.siriuspro.entity.pojo;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.MessageSettingApi;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.Request;
import okhttp3.Response;

import java.util.List;

@Data
@Accessors(chain = true)
public class MessageSetting  {

    /**
     * 是否允许创建私信
     */
    private Boolean disable_create_dm;

    /**
     * 是否允许发主动消息
     */
    private Boolean disable_push_msg;

    /**
     * 子频道ID数组
     */
    private List<String> channel_ids;

    /**
     * 每个子频道允许发送主动推送消息最大消息数
     */
    private Integer channel_push_max_num;



}

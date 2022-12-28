package cn.siriusbot.siriuspro.entity.pojo;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.entity.api.GuildApi;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.Request;
import okhttp3.Response;

@Data
@Accessors(chain = true)
public class Guild  {

    /**
     * 频道ID
     */

    private String id;

    /**
     * 频道名称
     */
    private String name;

    /**
     * 频道图标地址
     */
    private String icon;

    /**
     * 创建人ID
     */
    private String owner_id;

    /**
     * 是否为创建人
     */
    private boolean owner;

    /**
     * 频道人数
     */
    private int member_count;

    /**
     * 频道人数上限
     */
    private int max_member;

    /**
     * 频道介绍
     */
    private String description;

    /**
     * 加入时间
     */
    private String joined_at;



}

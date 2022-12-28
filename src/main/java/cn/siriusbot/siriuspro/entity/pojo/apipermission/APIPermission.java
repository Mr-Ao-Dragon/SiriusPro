package cn.siriusbot.siriuspro.entity.pojo.apipermission;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.ApiPermissionApi;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.util.List;

/**
 * 接口权限对象
 */
@Data
@Accessors(chain = true)
public class APIPermission {
    /**
     * 接口名称,例如/guilds/{guild_id}/members/{user_id}
     */
    private String path;

    /**
     * 请求方法,例如 GET
     */
    private String method;

    /**
     * Api接口名称,例如获取频道信息
     */
    private String desc;

    /**
     * 授权状态,auth_status为1时，代表已授权
     */
    private Integer auth_status;


}

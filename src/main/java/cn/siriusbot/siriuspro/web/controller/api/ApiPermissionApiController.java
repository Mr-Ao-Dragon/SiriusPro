package cn.siriusbot.siriuspro.web.controller.api;

import cn.siriusbot.siriuspro.bot.api.ApiPermissionApi;
import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.APIPermission;
import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.ApiPermissionDemand;
import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.ApiPermissionDemandIdentify;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.web.R.R;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 频道api权限
 */
@RestController
@RequestMapping("/api/apiPermission")
public class ApiPermissionApiController{
    @Autowired
    ApiPermissionApi apiPermissionApi;


    /**
     * 创建频道Api接口权限，授权链接
     *
     * @param bot_id          传入机器人对象ID
     * @param json
     * @return Api接口权限需求对象
     */
    @PostMapping("/create-api-grant-link/{bot_id}")
    public R createApiGrantLink(@PathVariable("bot_id")String bot_id, @RequestBody JSONObject json){
        try{
            String guild_id = json.getString("guild_id");
            String channel_id = json.getString("channel_id");
            ApiPermissionDemandIdentify api_identify = json.getObject("api_identify", ApiPermissionDemandIdentify.class);
            String desc = json.getString("desc");
            Tuple<ApiPermissionDemand, String> reply = apiPermissionApi.createApiGrantLink(bot_id, guild_id, channel_id, api_identify, desc);
            return new R().setData(reply);
        }catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setCode(500).setMsg("error");
        }
    }

    /**
     * 获取频道可用权限列表
     * @param bot_id 传入机器人ID
     * @param guild_id 频道ID
     * @return 返回可用Api权限对象列表
     */
    @GetMapping("/get-api-permissions/{bot_id}")
    public R getAPIPermissions(@PathVariable("bot_id")String bot_id,  String guild_id){
        try{
            Tuple<List<APIPermission>, String> reply = apiPermissionApi.getAPIPermissions(bot_id, guild_id);
            return new R().setData(reply);
        }
        catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setCode(500).setData("error");
        }
    }
}

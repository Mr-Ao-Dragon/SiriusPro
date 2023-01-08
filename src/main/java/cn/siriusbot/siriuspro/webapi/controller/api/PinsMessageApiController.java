package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.entity.api.PinsMessageApi;
import cn.siriusbot.siriuspro.entity.pojo.PinsMessage;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pinsMessage")
public class PinsMessageApiController {
    @Autowired
    PinsMessageApi pinsMessageApi;
    /**
     * 添加精华消息
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 返回精华消息对象
     */
    @PutMapping("/addPinsMessage/{bot_id}/{channel_id}")
    public R addPinsMessage(@PathVariable String bot_id,@PathVariable String channel_id,@RequestParam String message_id){
        try {
            return new R().setData(pinsMessageApi.addPinsMessage(bot_id, channel_id, message_id));
        }
        catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setMsg("error").setCode(500);
        }
    }


    /**
     * 获取当前子频道精华消息
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @return 返回精华消息对象
     */
    @GetMapping("/getPinsMessages/{bot_id}/{channel_id}")
    public R getPinsMessage(@PathVariable String bot_id,@PathVariable String channel_id){
        try {
            return new R().setData(pinsMessageApi.getPinsMessage(bot_id, channel_id));
        }
        catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 删除精华消息
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @param message_id 消息ID
     * @return 删除结果
     */
    @DeleteMapping("/deletePinsMessage/{bot_id}/{channel_id}")
    public R deletePinsMessage(@PathVariable String bot_id,@PathVariable String channel_id,@RequestParam String message_id){
        try {
            return new R().setData(pinsMessageApi.deletePinsMessage(bot_id, channel_id, message_id).booleanValue());
        }
        catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setMsg("error").setCode(500);
        }
    }
}

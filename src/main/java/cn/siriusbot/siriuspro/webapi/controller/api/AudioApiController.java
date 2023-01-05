package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.entity.api.AudioApi;
import cn.siriusbot.siriuspro.entity.pojo.audio.AudioControl;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 音频Api
 */
@RestController
@RequestMapping("/api/audio")
public class AudioApiController {

    @Autowired
    AudioApi audioApi;
    /**
     * 音频控制 Api
     * 频接口：仅限音频类机器人才能使用，后续会根据机器人类型自动开通接口权限，现如需调用，需联系平台申请权限。
     *
     * @param bot_id          机器人对象
     * @param json 请求体
     * @return 操作结果
     */
    @PostMapping("/audio-control/{bot_id}")
    public R audioControl(@PathVariable("bot_id")String bot_id, @RequestBody JSONObject json){
        try{
            String channel_id = json.getString("channel_id");
            AudioControl audio_control = json.getObject("audio_control", AudioControl.class);
            return new R().setData(audioApi.audioControl(bot_id,channel_id,audio_control).booleanValue());
        }catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 机器人上麦
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @return 返回操作结果
     */
    @PutMapping("/sing-start/{bot_id}/{channel_id}")
    public R singStart(@PathVariable("bot_id") String bot_id,@PathVariable("channel_id")String channel_id){
        try{
            return new R().setData(audioApi.singStart(bot_id,channel_id).booleanValue());
        }catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 机器人下麦
     * @param bot_id 传入机器人ID
     * @param channel_id 子频道ID
     * @return 返回操作结果
     */
    @DeleteMapping("/sing-end/{bot_id}/{channel_id}")
    public R singDelete(@PathVariable("bot_id") String bot_id,@PathVariable("channel_id")String channel_id){
        try{
            return new R().setData(audioApi.singEnd(bot_id,channel_id).booleanValue());
        }catch (MsgException e){
            return e.getR();
        }
        catch (Exception e){
            return new R().setMsg("error").setCode(500);
        }
    }
}

package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.bot.api.MessageSettingApi;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 频道消息频率设置Api
 */
@RestController
@RequestMapping("/api/messageSetting")
public class MessageSettingApiController {

    @Autowired
    MessageSettingApi messageSettingApi;

    /**
     * 获取频道消息频率设置信息
     * 用于获取机器人在频道 guild_id 内的消息频率设置。
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @return 返回频道消息频率设置对象
     */
    @GetMapping("/get-setting/{bot_id}/{guild_id}")
    public R getMessageSettingInfo(@PathVariable String bot_id, @PathVariable String guild_id) {
        try {
            return new R().setData(messageSettingApi.getMessageSettingInfo(bot_id, guild_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }
}

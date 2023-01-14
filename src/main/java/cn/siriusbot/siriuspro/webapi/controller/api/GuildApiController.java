package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.bot.api.GuildApi;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 频道Api
 */
@RestController
@RequestMapping("/api/guild")
public class GuildApiController {
    @Autowired
    GuildApi guildApi;

    /**
     * 获取频道详情
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @return 返回频道详情对象
     */
    @GetMapping("/get-guild-info/{bot_id}/{guild_id}")
    public R getGuildInfo(@PathVariable String bot_id, @PathVariable String guild_id) {
        try {
            return new R().setData(guildApi.getGuildInfo(bot_id, guild_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }
}

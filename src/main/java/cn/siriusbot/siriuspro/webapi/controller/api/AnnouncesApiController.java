package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.entity.api.AnnouncesApi;
import cn.siriusbot.siriuspro.entity.pojo.announces.Announces;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announces")
public class AnnouncesApiController {

    @Autowired
    AnnouncesApi announcesApi;

    @RequestMapping("/create-guild-announces/{bot_id}")
    public R createGuildAnnounces(
            @PathVariable("bot_id") String bot_id,
            @RequestParam("guild_id") String guild_id,
            @RequestParam("message_id") String message_id,
            @RequestParam("channel_id") String channel_id
    ) {
        try {
            Tuple<Announces, String> reply = announcesApi.createGuildAnnounces(bot_id, guild_id, message_id, channel_id);
            return new R()
                    .setData(reply);
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R()
                    .setCode(500)
                    .setMsg("error");
        }

    }

}

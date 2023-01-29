package cn.siriusbot.siriuspro.web.controller.api;

import cn.siriusbot.siriuspro.bot.api.UserApi;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.web.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @Autowired
    UserApi userApi;

    /**
     * 获取机器人基本信息
     *
     * @return 返回Bot(机器人)对象
     */
    @GetMapping("/get-bot-info/{bot_id}")
    public R getRobotInfo(@PathVariable String bot_id) {
        try {
            return new R().setData(userApi.getRobotInfo(bot_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 获取频道指定机器人频道列表
     *
     * @param bot_id 传入机器人ID
     * @param before 读此 guild id 之前的数据
     * @param after  读此 guild id 之后的数据
     * @param limit  每次查询的条数，默认100，最大100
     * @return 频道数组
     * after 和 before 同时设置时， after 参数无效
     */
    @GetMapping("/get-guilds/{bot_id}")
    public R getGuildList(@PathVariable String bot_id, @RequestParam @Nullable String before, @Nullable String after, @Nullable Integer limit) {
        try {
            if(limit==null)
                limit=100;
            return new R().setData(userApi.getGuildList(bot_id, before, after, limit));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            e.printStackTrace();
            return new R().setMsg("error").setCode(500);
        }
    }
}

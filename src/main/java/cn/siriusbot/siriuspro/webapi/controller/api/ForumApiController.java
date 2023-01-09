package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.entity.api.ForumApi;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 论坛Api
 */
@RestController
@RequestMapping("/api/forum")
public class ForumApiController {

    @Autowired
    ForumApi forumApi;

    /**
     * 获取指定论坛子频道帖子列表
     * 仅私域可用
     *
     * @param bot_id        传入机器人ID
     * @param channel_id 子频道ID
     * @return 帖子详情对象
     */
    @GetMapping("/get-threads/{bot_id}/{channel_id}")
    public R getThreads(@PathVariable String bot_id, @PathVariable String channel_id) {
        try {
            return new R().setData(forumApi.getThreadsByChannelId(bot_id, channel_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 获取子频道帖子详情
     * 仅私域可用
     *
     * @param bot_id        传入机器人对象ID
     * @param channel_id 子频道ID
     * @param thread_id  帖子ID
     * @return 帖子详情对象
     */
    @GetMapping("/get-thread-info/{bot_id}/{channel_id}/{thread_id}")
    public R getThreadInfo(@PathVariable String bot_id, @PathVariable String channel_id, @PathVariable String thread_id) {
        try {
            return new R().setData(forumApi.getThreadInfo(bot_id, channel_id, thread_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 发表帖子
     *
     * @param bot_id 传入机器人ID
     * @param json   请求体对象
     * @return 返回发表帖子响应对象
     */
    @PostMapping("/create-thread/{bot_id}")
    public R postThread(@PathVariable String bot_id, @RequestBody JSONObject json) {
        try {
            String channel_id = json.getString("channel_id");
            String title = json.getString("title");
            String content = json.getString("content");
            Integer format = json.getInteger("format");
            return new R().setData(forumApi.postThread(bot_id, channel_id, title, content, format));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");
        }
    }

    /**
     * 删除帖子
     * 仅私域可用
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param thread_id  帖子ID
     * @return 操作结果
     */
    @DeleteMapping("/delete-thread/{bot_id}/{channel_id}/{thread_id}")
    public R deleteThread(@PathVariable String bot_id, @PathVariable String channel_id, @PathVariable String thread_id) {
        try {
            return new R().setData(forumApi.deleteThread(bot_id, channel_id, thread_id).booleanValue());
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setCode(500).setMsg("error");
        }
    }
}

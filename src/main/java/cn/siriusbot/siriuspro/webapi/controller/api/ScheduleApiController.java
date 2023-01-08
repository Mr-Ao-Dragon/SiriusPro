package cn.siriusbot.siriuspro.webapi.controller.api;

import cn.siriusbot.siriuspro.entity.api.ScheduleApi;
import cn.siriusbot.siriuspro.entity.pojo.Schedule;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 日常Api
 */
@RestController
@RequestMapping("/api/schedule")
public class ScheduleApiController {
    @Autowired
    ScheduleApi scheduleApi;

    /**
     * 获取日程列表
     * 用于获取channel_id指定的子频道中当天的日程列表。
     * 若带了参数 since，则返回在 since 对应当天的日程列表；若未带参数 since，则默认返回今天的日程列表
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param since      起始时间戳(ms)
     * @return 返回日程列表
     */
    @GetMapping("/get-schedules/{bot_id}/{channel_id}")
    public R getScheduleListByChannel_id(@PathVariable String bot_id, @PathVariable String channel_id, @RequestParam String since) {
        try {
            return new R().setData(scheduleApi.getScheduleListByChannel_id(bot_id, channel_id, since));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 获取日程详情
     * 获取日程子频道 channel_id 下 schedule_id 指定的的日程的详情。
     *
     * @param bot_id      传入机器人ID
     * @param channel_id  子频道ID
     * @param schedule_id 日程ID
     * @return 返回日程对象
     */
    @GetMapping("/get-schedule-info/{bot_id}/{channel_id}/{schedule_id}")
    public R getScheduleInfo(@PathVariable String bot_id, @PathVariable String channel_id, @PathVariable String schedule_id) {
        try {
            return new R().setData(scheduleApi.getScheduleInfo(bot_id, channel_id, schedule_id));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 删除日程
     *
     * @param bot_id,传入机器人ID
     * @param channel_id     子频道ID
     * @param schedule_id    日程ID
     * @return 返回操作结果
     */
    @DeleteMapping("/delete-schedule/{bot_id}/{channel_id}/{schedule_id}")
    public R deleteSchedule(@PathVariable String bot_id, @PathVariable String channel_id, @PathVariable String schedule_id) {
        try {
            return new R().setData(scheduleApi.deleteSchedule(bot_id, channel_id, schedule_id).booleanValue());
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 在指定日程子频道创建一个日程
     *
     * @param bot_id     传入机器人ID
     * @param channel_id 子频道ID
     * @param schedule   不带id的日程对象
     * @return 返回日程对象
     */
    @PostMapping("/create-schedule/{bot_id}/{channel_id}")
    public R createSchedule(@PathVariable String bot_id, @PathVariable String channel_id, @RequestBody Schedule schedule) {
        try {
            return new R().setData(scheduleApi.createSchedule(bot_id, channel_id, schedule));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

    /**
     * 修改日程
     *
     * @param bot_id      传入机器人ID
     * @param channel_id  子频道id
     * @param schedule_id 日程ID
     * @param schedule    修改后的日程对象，不带id
     * @return 返回日程对象
     */
    @PatchMapping("/{bot_id}/{schedule_id}")
    public R modifySchedule(@PathVariable String bot_id, @PathVariable String channel_id, @PathVariable String schedule_id, @RequestBody Schedule schedule) {
        try {
            return new R().setData(scheduleApi.modifySchedule(bot_id, channel_id, schedule_id, schedule));
        } catch (MsgException e) {
            return e.getR();
        } catch (Exception e) {
            return new R().setMsg("error").setCode(500);
        }
    }

}

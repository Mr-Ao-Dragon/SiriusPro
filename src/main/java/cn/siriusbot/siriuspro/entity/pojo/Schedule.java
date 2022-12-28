package cn.siriusbot.siriuspro.entity.pojo;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.ScheduleApi;
import cn.siriusbot.siriuspro.entity.temp.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.lang.reflect.Member;
import java.util.List;

/**
 * 日程对象
 */
@Data
@Accessors(chain = true)
public class Schedule{

    /**
     * 日程ID
     */
    private String id;

    /**
     * 日程名称
     */
    private String name;

    /**
     * 日程描述
     */
    private String description;

    /**
     * 日程开始时间戳
     */
    private String start_timestamp;

    /**
     * 日程结束时间戳
     */
    private String end_timestamp;

    /**
     * 创建者
     */
    private Member creator;

    /**
     * 日程开始时跳转的子频道ID
     */
    private String jump_channel_id;

    /**
     * 日程提醒类型,取值参考RemindType
     */
    private String remind_type;



    public enum REMIND_TYPE {
        /**
         * 不提醒
         */
        NO_REMIND("0"),

        /**
         * 开始时提醒
         */
        START_REMIND("1"),

        /**
         * 开始前五分钟提醒
         */
        FIVE_MINUTE_REMIND("2"),

        /**
         * 开始前十五分钟提醒
         */
        FIFTEEN_MINUTE_REMIND("3"),

        /**
         * 开始前三十分钟提醒
         */
        THIRTY_MINUTE_REMIND("4"),

        /**
         * 开始前六十分钟提醒
         */
        SIXTY_MINUTE_REMIND("5");
        String value;

        REMIND_TYPE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

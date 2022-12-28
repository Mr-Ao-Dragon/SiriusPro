package cn.siriusbot.siriuspro.entity.pojo.forum.responseObj;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 创建帖子Api响应对象
 */
@Data
@Accessors(chain = true)
public class createThread {

    /**
     * 帖子任务ID
     */
    private String task_id;

    /**
     * 发帖时间戳,单位:秒
     */
    private String create_time;
}

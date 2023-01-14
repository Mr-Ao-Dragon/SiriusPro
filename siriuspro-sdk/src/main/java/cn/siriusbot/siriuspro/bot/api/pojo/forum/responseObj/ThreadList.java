package cn.siriusbot.siriuspro.bot.api.pojo.forum.responseObj;

import cn.siriusbot.siriuspro.bot.api.pojo.forum.thread.ForumThread;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 获取帖子列表响应对象
 */
@Data
@Accessors(chain = true)
public class ThreadList {
    /**
     * 帖子列表对象
     */
    private List<ForumThread> threads;

    /**
     * 是否拉取完毕,0:否,1:是
     */
    private Integer is_finish;
}

package cn.siriusbot.siriuspro.bot.api.pojo.emoji;

import cn.siriusbot.siriuspro.bot.api.pojo.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 拉取表情表态响应对象
 */
@Data
@Accessors(chain = true)
public class ReactionReply {

    /**
     * 用户对象列表
     */
    private List<User> users;

    /**
     * 分页参数，用于拉取下一页
     */
    private String cookie;

    /**
     * 是否一拉取完成到最后一页，true代表完成
     */
    private Boolean is_end;
}

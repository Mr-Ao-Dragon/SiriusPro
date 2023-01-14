package cn.siriusbot.siriuspro.bot.api.pojo.member;

import cn.siriusbot.siriuspro.bot.api.pojo.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Member {
    /**
     * 用户的频道基础信息
     */
    private User user;

    /**
     * 用户的昵称
     */
    private String nick;

    /**
     * 用户在频道内的身份组ID数组
     */
    private List<String> roles;

    /**
     * 用户加入频道的时间
     */
    private String joined_at;

}

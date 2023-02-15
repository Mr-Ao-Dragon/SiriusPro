package cn.siriusbot.siriuspro.bot.api.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户对象
 */
@Data
@Accessors(chain = true)
public class User{
    /**
     * 用户ID
     */
    public String id;

    /**
     * 用户名称
     */
    public String username;

    /**
     * 机器人头像地址
     */
    public String avatar;

    /**
     * 当前用户是否为机器人
     */
    public boolean bot;

    /**
     * 特殊关联应用的 openid，需要特殊申请并配置后才会返回。
     */
    public String union_openid;

    /**
     * 机器人关联的互联应用的用户信息，与union_openid关联的应用是同一个
     */
    public String union_user_account;



}

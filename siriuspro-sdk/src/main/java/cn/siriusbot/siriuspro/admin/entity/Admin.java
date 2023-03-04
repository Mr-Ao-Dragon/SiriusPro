package cn.siriusbot.siriuspro.admin.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Admin {
    Integer id;

    /**
     * 账号
     */
    String account;

    /**
     * 密码
     */
    String passwd;

    /**
     * 权限等级 0为最高等级
     */
    Integer power;
}

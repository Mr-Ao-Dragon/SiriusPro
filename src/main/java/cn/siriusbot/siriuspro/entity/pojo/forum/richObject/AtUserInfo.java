package cn.siriusbot.siriuspro.entity.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 艾特用户信息对象
 */
@Data
@Accessors(chain = true)
public class AtUserInfo {
    /**
     * 身份组ID
     */
    private String id;

    /**
     * 用户昵称
     */
    private String nick;

}

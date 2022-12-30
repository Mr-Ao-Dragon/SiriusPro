package cn.siriusbot.siriuspro.entity.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 艾特身份组信息对象
 */
@Data
@Accessors(chain = true)
public class AtRoleInfo {

    /**
     * 身份组ID
     */
    private Long role_id;

    /**
     * 身份组名称
     */
    private String name;
}

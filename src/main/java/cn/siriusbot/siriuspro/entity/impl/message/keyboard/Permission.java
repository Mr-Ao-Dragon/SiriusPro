package cn.siriusbot.siriuspro.entity.impl.message.keyboard;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
/**
 * 按钮操作权限对象
 */
public class Permission {
    /**
     * 权限类型，参考 PermissionType
     */
    private Integer type;
    /**
     * 有权限的身分组id的列表
     */
    private List<String> specify_role_ids;

    /**
     * 有权限的用户id列表
     */
    private List<String> specify_user_ids;

    /**
     * 权限类型
     */
    public enum PERMISSION_TYPE {
        /**
         * 指定成员可操作
         */
        SPECIFIC_MEMBER(0),
        /**
         * 仅管理人员可操作
         */
        MANAGER(1),
        /**
         * 所有成员可操作
         */
        ALL_MEMBERS(2),
        /**
         * 拥有指定身份组的成员可操作
         */
        SPECIFIC_ROLE(3);

        private int value;

        PERMISSION_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
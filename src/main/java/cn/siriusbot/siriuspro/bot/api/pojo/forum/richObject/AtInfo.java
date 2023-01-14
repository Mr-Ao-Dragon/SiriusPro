package cn.siriusbot.siriuspro.bot.api.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 富文本里的@内容对象
 */
public class AtInfo {

    /**
     * 艾特类型
     */
    public String type;

    /**
     * 被At用户
     */
    private AtUserInfo user_info;

    /**
     * 身份组信息
     */
    private AtRoleInfo role_info;

    /**
     * 频道信息
     */
    private AtGuildInfo guild_info;

    /**
     * 艾特类型
     */
    public enum AT_TYPE{
        /**
         * at指定人员
         */
        AT_EXPLCI_USER(1),

        /**
         * at角色组所有人
         */
        AT_ROLE_GROUP(2),

        /**
         * at频道内所有人
         */
        AT_GUILD(3);

        private Integer value;
        AT_TYPE(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}

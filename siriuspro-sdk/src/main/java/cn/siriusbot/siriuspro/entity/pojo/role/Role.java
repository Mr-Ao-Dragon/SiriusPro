package cn.siriusbot.siriuspro.entity.pojo.role;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Role {
    /**
     * 身份组ID
     */
    private String id;

    /**
     * 身份组名称
     */
    private String name;

    /**
     * 身份组颜色-ARGB的HEX十六进制颜色值转换后的十进制数值
     */
    private Long color;

    /**
     * 身份组人数
     */
    private Integer number;

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", number=" + number +
                ", hoist=" + hoist +
                ", member_limit=" + member_limit +
                '}';
    }

    /**
     * 是否在成员列表中单独展示: 0-否, 1-是
     */
    private Integer hoist;
    /**
     * 身份组人数上限
     */
    private Integer member_limit;


    /**
     * 系统默认生成下列身份组
     */
    public enum DEFAULT_ROLE_IDS {

        /**
         * 全体成员
         */
        ALL(1),
        /**
         * 管理员
         */
        MANAGER(2),
        /**
         * 频道主
         */
        ADMIN(4),
        /**
         * 子频道管理员
         */
        CHANNEL_MANAGER(5);
        private int value;

        DEFAULT_ROLE_IDS(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}

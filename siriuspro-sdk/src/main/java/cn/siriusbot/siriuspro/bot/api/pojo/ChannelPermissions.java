package cn.siriusbot.siriuspro.bot.api.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 子频道权限对象
 */
public class ChannelPermissions {
    /**
     * 子频道ID
     */
    String channel_id;

    /**
     * 用户ID
     */
    String user_id;

    /**
     * 身份组ID
     */
    String role_id;

    /**
     * 用户拥有的子频道权限
     */
    String permissions;




    public enum PERMISSIONS {

        /**
         * 可查看子频道
         */
        SEE(String.valueOf(1 << 0)),

        /**
         * 可管理子频道
         */
        ADMIN(String.valueOf(1 << 1)),

        /**
         * 可管理子频道
         */
        SPEAK(String.valueOf(1 << 2)),

        /**
         * 可直播子频道
         */
        LIVE(String.valueOf(1 << 3));
        private String value;

        PERMISSIONS(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

package cn.siriusbot.siriuspro.bot;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BotToken {
    /**
     * 机器人ID
     */
    String botId;

    /**
     * 机器人token
     */
    String token;

    /**
     * 机器人类型，参考枚举botType，默认为公域
     */
    botType botType ;

    /**
     * 是否为沙箱模式,false非沙箱环境,true沙箱环境
     */
    boolean sandBox;

    /**
     * PUBLIC_TYPE公域类型,_TYPE私域类型
     */
    public enum botType {
        PUBLIC_TYPE(0),
        PRIVATE_TYPE(1);

        Integer value;
        botType(Integer value){
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}

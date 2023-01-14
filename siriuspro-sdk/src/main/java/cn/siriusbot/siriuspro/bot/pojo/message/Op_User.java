package cn.siriusbot.siriuspro.bot.pojo.message;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 操作人对象
 */
public class Op_User {
    private String id;
}

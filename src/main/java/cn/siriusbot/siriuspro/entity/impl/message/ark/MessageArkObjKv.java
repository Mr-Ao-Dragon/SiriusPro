package cn.siriusbot.siriuspro.entity.impl.message.ark;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * MessageArkObjKv
 */
public class MessageArkObjKv {
    /**
     * key
     */
    String key;
    /**
     * value
     */
    String value;
}

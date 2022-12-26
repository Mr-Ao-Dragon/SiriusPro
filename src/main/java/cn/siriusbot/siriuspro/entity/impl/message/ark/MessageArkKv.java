package cn.siriusbot.siriuspro.entity.impl.message.ark;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * arkKv值对象
 */
@Data
@Accessors(chain = true)
public class MessageArkKv {
    /**
     * key
     */
    private String key;
    /**
     * value
     */
    private String value;
    /**
     * ark obj类型的列表
     */
    private List<MessageArkObj> obj;
}
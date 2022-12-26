package cn.siriusbot.siriuspro.entity.impl.message.ark;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
/**
 * arkobj对象
 */
public class MessageArkObj {
    /**
     * ark objkv列表
     */
    private List<MessageArkObjKv> obj_kv;
}
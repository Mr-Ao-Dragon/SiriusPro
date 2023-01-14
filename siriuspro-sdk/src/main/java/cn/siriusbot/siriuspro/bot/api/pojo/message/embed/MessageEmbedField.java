package cn.siriusbot.siriuspro.bot.api.pojo.message.embed;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * embed字段数据对象
 */
public class MessageEmbedField {
    /**
     * 字段名
     */
    private String name;
}
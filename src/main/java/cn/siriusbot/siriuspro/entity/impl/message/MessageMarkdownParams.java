package cn.siriusbot.siriuspro.entity.impl.message;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * markdown 模板模板参数对象
 */
@Data
@Accessors(chain = true)
public class MessageMarkdownParams {
    /**
     * markdown 模板key
     */
    private String key;
    /**
     * markdownm模板key对于的values,长度大小为1，传多了会报错
     */
    private List<String> values;
}
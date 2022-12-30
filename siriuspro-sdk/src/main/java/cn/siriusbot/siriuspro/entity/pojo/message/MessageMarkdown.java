package cn.siriusbot.siriuspro.entity.pojo.message;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
/**
 * markdown消息对象
 */
public class MessageMarkdown {
    /**
     * 模板id
     */
    private Integer template_id;

    /**
     * markdown 自定义模板ID
     */
    private String custom_template_id;

    /**
     * markdown 模板模板参数
     */
    private List<MessageMarkdownParams> params;

    /**
     * 原生markdown消息,与上面三个参数互斥，大概率没权限
     */
    private String content;


    /**
     * 生成markdown模板消息对象
     * @param custom_template_id 模板ID
     * @param messageMarkdownParams 变量值
     * @return 返回markdown消息对象
     */
    public static MessageMarkdown createMarkdownMessage(String custom_template_id,List<MessageMarkdownParams> messageMarkdownParams){
        MessageMarkdown markdown = new MessageMarkdown();
        markdown.setCustom_template_id(custom_template_id).setParams(messageMarkdownParams);
        return markdown;
    }

}


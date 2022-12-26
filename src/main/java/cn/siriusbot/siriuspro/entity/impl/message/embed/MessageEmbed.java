package cn.siriusbot.siriuspro.entity.impl.message.embed;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
/**
 * embed消息对象
 */
public class MessageEmbed {
    /**
     * 标题
     */
    private String title;
    /**
     * 消息弹窗内容
     */
    private String prompt;
    /**
     * embed消息缩略图对象
     */
    private MessageEmbedThumbnail thumbnail;
    /**
     * embed字段数据
     */
    private List<MessageEmbedField> fields;


    /**
     * 创建Embed消息对象
     * @param title 标题
     * @param prompt 消息弹窗内容
     * @param thumbnail 缩略图对象
     * @param embedFields embed字段数据列表
     * @return embed消息对象
     */
    public static MessageEmbed createEmbedTemplate(String title,String prompt,MessageEmbedThumbnail thumbnail,List<MessageEmbedField> embedFields){
        MessageEmbed embed = new MessageEmbed();
        embed.setTitle(title)
                .setPrompt(prompt)
                .setThumbnail(thumbnail)
                .setFields(embedFields);
        return embed;
    }
}


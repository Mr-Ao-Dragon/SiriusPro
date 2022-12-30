package cn.siriusbot.siriuspro.entity.pojo.message;

import cn.siriusbot.siriuspro.entity.pojo.message.keyboard.InlineKeyboard;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 按钮消息对象
 */
public class MessageKeyboard{
    /**
     * keyboard模板ID
     */
    private String id;
    /**
     * InlineKeyboard对象,与id参数互斥，都传值会报错
     */
    private InlineKeyboard content;

    /**
     * 生成模板按钮对象
     * @param id 模板ID
     * @return 消息按钮对象
     */
    public static MessageKeyboard createKeyBoard(String id){

        MessageKeyboard keyboard = new MessageKeyboard();
        keyboard.setId(id);
        return keyboard;
    }

    /**
     * 生成自定义按钮对象
     * @param inlineKeyboard 传入按钮对象
     * @return 消息按钮对象
     */
    public static MessageKeyboard createKeyBoard(InlineKeyboard inlineKeyboard){
        MessageKeyboard keyboard = new MessageKeyboard();
        keyboard.setContent(inlineKeyboard);
        return keyboard;
    }
}

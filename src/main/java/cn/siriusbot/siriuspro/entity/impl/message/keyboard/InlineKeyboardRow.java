package cn.siriusbot.siriuspro.entity.impl.message.keyboard;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
/**
 * 按钮行对象
 */
public class InlineKeyboardRow {
    /**
     * 数组的每一个成员代表一个按钮，每一行最多可以放5个按钮
     */
    private List<Button> buttons;
}
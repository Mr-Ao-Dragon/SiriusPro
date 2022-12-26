package cn.siriusbot.siriuspro.entity.impl.message.keyboard;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
/**
 * 消息按钮对象
 */
public class InlineKeyboard {
    /**
     * 消息按钮组件行对象数组,数组的一项，代表消息按钮组件的一行,最多可以放5行按钮
     */
    private List<InlineKeyboardRow> rows;

    /**
     * 按钮操作类型
     */
    public enum ACTION_TYPE {
        /**
         * 打开一个链接或小程序
         */
        HTTP_REQUEST(0),

        /**
         * 回调后台接口,传递data至后台
         */
        CONSOLE_CALLBACK(1),

        /**
         * at机器人 并根据at_bot_show_channel_list决定在当前频道或用户选择频道，自动输入@bot 指令
         */
        AT_BOT(2);

        private int value;

        ACTION_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}

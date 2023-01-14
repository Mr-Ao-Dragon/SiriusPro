package cn.siriusbot.siriuspro.bot.api.pojo.message.keyboard;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
/**
 * 按钮操作对象
 */
public class Action {
    /**
     * 操作类型,参考ActionType
     */
    private Integer type;
    /**
     * 按钮操作权限对象，用于判断点击按钮的人的身份，比如是否是管理员，否则不允许操作
     */
    private Permission permission;
    /**
     * 按钮可点击的次数,默认不限制
     */
    private Integer click_limit;
    /**
     * 按钮点击后传递的数据
     */
    private String data;
    /**
     * 是否弹出频道选择器,true弹出,falst不弹出
     */
    private boolean at_bot_show_channel_list;

}
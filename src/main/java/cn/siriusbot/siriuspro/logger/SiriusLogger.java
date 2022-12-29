package cn.siriusbot.siriuspro.logger;

import java.util.HashMap;
import java.util.Map;

public class SiriusLogger {


    public static final Map<Integer,String> colorMap = new HashMap() {
        {
            put(31, "红色字体");
            put(32, "绿色字体");
            put(33, "黄色字体");
            put(34, "蓝色字体");
            put(35, "紫色字体");
            put(36, "青色字体");
            put(37, "灰色字体");
            put(40, "黑色背景");
            put(41, "红色背景");
            put(42, "绿色背景");
            put(43, "黄色背景");
            put(44, "蓝色背景");
            put(45, "紫色背景");
            put(46, "青色背景");
            put(47, "灰色背景");
        }
    };

    /**
     * @param color  颜色代号：背景颜色代号(41-46)；前景色代号(31-36)
     * @param type    样式代号：0无；1加粗；3斜体；4下划线
     * @param content 要打印的内容
     */
    public static String getFormatLogString(String content, int color, int type) {
        boolean hasType = type != 1 && type != 3 && type != 4;
        if (hasType) {
            return String.format("\033[%dm%s\033[0m", color, content);
        } else {
            return String.format("\033[%d;%dm%s\033[0m", color, type, content);
        }
    }
}

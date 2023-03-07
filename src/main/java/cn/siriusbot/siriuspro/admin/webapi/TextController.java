package cn.siriusbot.siriuspro.admin.webapi;

import cn.siriusbot.siriuspro.config.aop.PowerInterceptor;
import cn.siriusbot.siriuspro.web.R.R;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PowerInterceptor(power = 0)
@RequestMapping("/api/text")
public class TextController {

    /**
     * 获取转义
     * @param text
     * @return
     */
    @RequestMapping("/escape")
    public R getEscape(
            String text
    ){
        String s = EmojiParser.parseToAliases(text);
        return new R()
                .setCode(0)
                .setData(s);
    }
}

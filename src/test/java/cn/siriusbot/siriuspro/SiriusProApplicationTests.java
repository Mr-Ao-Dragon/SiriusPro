package cn.siriusbot.siriuspro;

import cn.siriusbot.siriuspro.admin.dao.RobotMapper;
import cn.siriusbot.siriuspro.bot.BotApi;
import cn.siriusbot.siriuspro.bot.BotManager;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiManager;
import com.vdurmont.emoji.EmojiParser;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = {SiriusProApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SiriusProApplicationTests {

    @Test
    void test1(){
        String str = "哈哈哈,🚐ces \uD83D\uDE06矮矮";
        String result = EmojiParser.parseToAliases(str);
        System.out.println(result);
        String s = EmojiParser.parseToUnicode(result + "🚐");
        System.out.println(s);
    }

}

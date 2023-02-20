package cn.siriusbot.siriuspro;

import com.vdurmont.emoji.EmojiParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {SiriusProApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SiriusProApplicationTests {


    void test1(){
        String str = "哈哈哈,🚐ces \uD83D\uDE06矮矮";
        String result = EmojiParser.parseToAliases(str);
        System.out.println(result);
        String s = EmojiParser.parseToUnicode(result + "🚐");
        System.out.println(s);
    }

    @Test
    void test2(){

    }

}

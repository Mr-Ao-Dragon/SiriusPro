package cn.siriusbot.siriuspro;

import cn.siriusbot.siriuspro.admin.service.IntentService;
import cn.siriusbot.siriuspro.bot.BotApi;
import cn.siriusbot.siriuspro.bot.api.pojo.message.MessageKeyboard;
import cn.siriusbot.siriuspro.bot.api.pojo.message.MessageMarkdown;
import cn.siriusbot.siriuspro.bot.api.pojo.message.MessageMarkdownParams;
import cn.siriusbot.siriuspro.bot.api.pojo.message.keyboard.*;
import cn.siriusbot.siriuspro.bot.api.pojo.message.requestPack.RequestCustomKeyboard;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {SiriusProApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SiriusProApplicatioApiTest {

    @Autowired
    BotApi botApi;

    @Autowired
    IntentService intentService;


    void api_1(){
        List<MessageMarkdownParams> messageMarkdownParams = new ArrayList<>();
        List<String> value1 = new ArrayList<>();
        List<String> value2 = new ArrayList<>();
        List<String> value3 = new ArrayList<>();
        List<String> value4 = new ArrayList<>();
        List<String> value5 = new ArrayList<>();
        List<String> value6 = new ArrayList<>();
        value1.add("https://qq.yhaox.top/dsq/rule.png");
        value2.add("欢迎来到辽大校园生活QQ频道");
        value3.add("这里，你可以与其他同样热爱音乐的小伙伴们一起看电影，听音乐，共建一处属于你们的秘密基地");
        value4.add("请点击下方按钮选择校区");
        value5.add("https://qq.yhaox.top/dsq/rule.png");
        value6.add("欢迎加入");
        messageMarkdownParams.add(new MessageMarkdownParams().setKey("picture").setValues(value1));
        messageMarkdownParams.add(new MessageMarkdownParams().setKey("title").setValues(value2));
        messageMarkdownParams.add(new MessageMarkdownParams().setKey("line1").setValues(value3));
        messageMarkdownParams.add(new MessageMarkdownParams().setKey("line2").setValues(value4));
        messageMarkdownParams.add(new MessageMarkdownParams().setKey("avatar").setValues(value5));
        messageMarkdownParams.add(new MessageMarkdownParams().setKey("line3").setValues(value6));
        MessageMarkdown markdown = MessageMarkdown.createMarkdownMessage("101999871_1670336535", messageMarkdownParams);
        InlineKeyboard inlineKeyboard = new InlineKeyboard();


        List<InlineKeyboardRow> inlineKeyboardRows = new ArrayList<>();
        List<Button> buttons = new ArrayList<>();
        Action action = new Action();
        Permission permission = new Permission();
        permission.setType(Permission.PERMISSION_TYPE.ALL_MEMBERS.getValue());
        action.setType(InlineKeyboard.ACTION_TYPE.CONSOLE_CALLBACK.getValue()).setAt_bot_show_channel_list(false).setClick_limit(10).setData("/chongshan").setPermission(permission);
        RenderData renderData = new RenderData();
        renderData.setLabel("崇山校区").setVisited_label("崇山校区");

        buttons.add(new Button().setAction(action));

        buttons.add(new Button().setAction(action).setId("1"));
        buttons.add(new Button().setAction(action.setData("puhe")).setId("2"));
        inlineKeyboardRows.add(new InlineKeyboardRow().setButtons(buttons));
        inlineKeyboard.setRows(inlineKeyboardRows);
        RequestCustomKeyboard requestCustomKeyboard = new RequestCustomKeyboard();
        requestCustomKeyboard.setMessageKeyboard(MessageKeyboard.createKeyBoard("101999871_1670331777"));
        requestCustomKeyboard.setBot_appid("101999871");
        requestCustomKeyboard.setMarkdown(markdown);

        System.out.println(botApi.messageApi().sendMarkdownMessage("101999871", "7770989", null, null, markdown));

        System.out.println(botApi.messageApi().sendCustomInLineKeyword("101999871", "7770989", requestCustomKeyboard));
        System.out.println(JSONObject.toJSONString(requestCustomKeyboard));
    }

    @Test
    void test_1(){
        System.out.println(botApi.messageApi().sendMessage("102003612","6034803","测试",null,"08d8beb89ca4cae1801d10f3aaf00238f72d48fcc1cc9f06",null));
    }
}

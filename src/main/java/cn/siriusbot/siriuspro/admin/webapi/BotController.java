package cn.siriusbot.siriuspro.admin.webapi;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.admin.service.BotService;
import cn.siriusbot.siriuspro.admin.service.IntentService;
import cn.siriusbot.siriuspro.admin.vo.PageRobotList;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.pojo.e.IntentsType;
import cn.siriusbot.siriuspro.config.aop.PowerInterceptor;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.web.R.R;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@PowerInterceptor(power = 0)
@RequestMapping("/api/bot")
public class BotController {
    @Autowired
    BotService botService;

    @Autowired
    IntentService intentService;


    @RequestMapping("/add")
    public R addBot(
            @RequestParam(value = "bot_type", required = false) Integer botType,
            @RequestParam(value = "sand_box", required = false) Boolean sandBox,
            @RequestParam(value = "bot_id", required = false) String botId,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "auto_login", required = false) Boolean autoLogin
    ) {
        botService.addBot(
                new Robot()
                        .setBotType(botType)
                        .setSandBox(sandBox)
                        .setBotId(botId)
                        .setToken(token)
                        .setAutoLogin(autoLogin)
        );
        return new R().setMsg("添加机器人成功!");
    }

    @RequestMapping("/modify")
    public R modifyBot(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "bot_type", required = false) Integer botType,
            @RequestParam(value = "sand_box", required = false) Boolean sandBox,
            @RequestParam(value = "bot_id", required = false) String botId,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "auto_login", required = false) Boolean autoLogin
    ) {
        botService.modifyBot(
                new Robot()
                        .setId(id)
                        .setBotType(botType)
                        .setSandBox(sandBox)
                        .setBotId(botId)
                        .setToken(token)
                        .setAutoLogin(autoLogin)
        );
        return new R().setMsg("修改机器人成功!");
    }

    @RequestMapping("/del")
    public R modifyBot(
            @RequestParam(value = "id", required = false) Integer id
    ) {
        if (id == null){
            throw new MsgException(500, "删除失败,ID不能为空!");
        }
        botService.delBotById(id);
        return new R().setMsg("删除机器人成功!");
    }

    @RequestMapping("/login")
    public R loginBot(
            @RequestParam(value = "bot_id", required = false) String botId
    ) {
        botService.loginBotByBotId(botId);
        return new R().setMsg("机器人登录成功!");
    }

    @RequestMapping("/logout")
    public R logoutBot(
            @RequestParam(value = "bot_id") String botId
    ) {
        botService.logoutBotByBotId(botId);
        return new R().setMsg("机器人登出成功!");
    }

    @RequestMapping("/get-online-all")
    public R getOnlineAll() {
        List<BotClient> botClients = botService.queryBotClientAll();
        List<JSONObject> list = new ArrayList<>();
        for (BotClient client : botClients) {
            JSONObject bean = (JSONObject) JSON.toJSON(client.getInfo());
            bean.put("s", client.getSession().getS());
            list.add(bean);
        }
        return new R()
                .setData(list);
    }

    @RequestMapping("/get-online-one")
    public R getOnlineOne(
            @RequestParam(value = "bot_id") String botId
    ) {
        BotClient client = botService.queryBotClientByBotId(botId);
        JSONObject bean = (JSONObject) JSON.toJSON(client.getInfo());
        bean.put("s", client.getSession().getS());
        return new R()
                .setData(bean);
    }


    @RequestMapping("/get-database-all")
    public R getDatabaseAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "bot_id", required = false) String botId,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "state", required = false) Integer state,
            @RequestParam(value = "bot_type", required = false) Integer botType,
            @RequestParam(value = "sand_box", required = false) Boolean sandBox

    ) {
        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size < 1) {
            size = 1000;
        }
        System.out.println(sandBox);
        PageRobotList pageRobotList = botService.queryRobotAllByCondition(page, size, botId, username, state, botType, sandBox);
        JSONObject extra = new JSONObject();
        extra.put("count", pageRobotList.getCount());
        return new R()
                .setData(pageRobotList.getRobots())
                .setExtra(extra);
    }

    @RequestMapping("/get-database-one")
    public R getDatabaseAll(
            @RequestParam(value = "bot_id") String botId
    ) {
        Robot robot = botService.queryRobotByBotId(botId);
        return new R()
                .setData(robot);
    }

    @RequestMapping("/set-intents" )
    public R setIntents(
            @RequestBody JSONObject json
    ) {
        String botId = json.getString("botId");
        JSONArray intents = json.getJSONArray("intents");
        List<IntentsType> typeList = new ArrayList<>();
        for (Object item : intents){
            if (item instanceof String o){
                IntentsType instance = IntentsType.getInstance(o);
                if (instance == null){
                    throw new MsgException(500, "订阅事件类型错误！");
                }
                typeList.add(IntentsType.getInstance(o));
            }
        }
        intentService.subscription(botId, typeList.toArray(new IntentsType[0]));
        return new R();
    }
}

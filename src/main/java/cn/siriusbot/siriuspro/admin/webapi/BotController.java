package cn.siriusbot.siriuspro.admin.webapi;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.admin.service.BotService;
import cn.siriusbot.siriuspro.admin.service.IntentService;
import cn.siriusbot.siriuspro.admin.vo.BotList;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.pojo.e.IntentsType;
import cn.siriusbot.siriuspro.config.aop.PowerInterceptor;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.web.R.R;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@PowerInterceptor(power = 0)
@RequestMapping
public class BotController {
    @Autowired
    BotService botService;

    @Autowired
    IntentService intentService;


    @RequestMapping("/add")
    public R addBot(
            @RequestParam(value = "bot-type", required = false) Integer botType,
            @RequestParam(value = "sand-box", required = false) Boolean sandBox,
            @RequestParam(value = "bot-id", required = false) String botId,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "auto-login", required = false) Boolean autoLogin
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

    @RequestMapping("/login")
    public R loginBot(
            @RequestParam(value = "bot-id", required = false) String botId
    ) {
        botService.loginBotByBotId(botId);
        return new R().setMsg("机器人登录成功!");
    }

    @RequestMapping("/logout")
    public R logoutBot(
            @RequestParam(value = "bot-id") String botId
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
            @RequestParam(value = "bot-id") String botId
    ) {
        BotClient client = botService.queryBotClientByBotId(botId);
        JSONObject bean = (JSONObject) JSON.toJSON(client.getInfo());
        bean.put("s", client.getSession().getS());
        return new R()
                .setData(bean);
    }


    @GetMapping("/api/rule")
    public BotList getDatabaseAll(
            @RequestParam(value = "current", required = false) Integer page,
            @RequestParam(value = "pageSize", required = false) Integer size

    ) {
        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size < 1) {
            size = 1000;
        }
        List<Robot> robots = botService.queryRobotAll(page, size);

        BotList botList = new BotList();
        botList
                .setCurrent(page)
                .setPageSize(String.valueOf(size))
                .setSuccess(true)
                .setTotal(robots.size())
                .setData(new ArrayList<>());    // todo 临时
        int i = 1;
        for (Robot robot : robots){
            //botList.getData().add()
            BotList.BotData botData = new BotList.BotData()
                    .setAvatar("https://gw.alipayobjects.com/zos/rmsportal/udxAbMEhpwthVVcjLXik.png")
                    .setCallNo(0)
                    .setCreatedAt("2023-02-13")
                    .setDesc("测试昵称")
                    .setDisabled(false)
                    .setHref("https://ant.design")
                    .setKey(i++)
                    .setName(robot.getBotId())
                    .setOwner("")
                    .setProgress(0)
                    .setServer(robot.getSandBox() ? 1 : 0)
                    .setStatus(robot.getState())
                    .setType(robot.getBotType())
                    .setUpdatedAt("2023-02-13");
            botList.getData().add(botData);
        }

        return botList;
    }


    @PostMapping("/api/rule")
    public BotList addBot(
            @RequestBody JSONObject body
    ) {
        String botId = body.getString("desc");
        String token = body.getString("name");
        botService.addBot(
                new Robot()
                        .setBotType(0)
                        .setSandBox(true)
                        .setBotId(botId)
                        .setToken(token)
                        .setAutoLogin(false)
        );
        return null;
    }

    @RequestMapping("/get-database-one")
    public R getDatabaseAll(
            @RequestParam(value = "bot-id") String botId
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
            if (item instanceof Integer o){
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

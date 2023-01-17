package cn.siriusbot.siriuspro.admin.webapi;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.admin.service.BotService;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.config.aop.PowerInterceptor;
import cn.siriusbot.siriuspro.webapi.R.R;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
            JSONObject bean = (JSONObject) JSONObject.toJSON(client.getInfo());
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
        JSONObject bean = (JSONObject) JSONObject.toJSON(client.getInfo());
        bean.put("s", client.getSession().getS());
        return new R()
                .setData(bean);
    }


    @RequestMapping("/get-database-all")
    public R getDatabaseAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size

    ) {
        if (page == null || page < 0) {
            page = 0;
        }
        if (size == null || size < 1) {
            size = 1000;
        }
        List<Robot> robots = botService.queryRobotAll(page, size);
        return new R()
                .setData(robots);
    }

    @RequestMapping("/get-database-one")
    public R getDatabaseAll(
            @RequestParam(value = "bot-id") String botId
    ) {
        Robot robot = botService.queryRobotByBotId(botId);
        return new R()
                .setData(robot);
    }


}

package cn.siriusbot.siriuspro.webapi.controller;


import cn.siriusbot.siriuspro.admin.dao.RobotMapper;
import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.BotToken;
import cn.siriusbot.siriuspro.webapi.R.R;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.siriusbot.siriuspro.webapi.pojo.BotInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class BotController {

    /**
     *  添加机器人
     * @param botTemp 机器人模板对象
     * @return 添加结果
     */
    @PostMapping("/bot/add")
    public R addBot(@RequestBody BotInfo botTemp) {
        BotToken bot = new BotToken();
        bot.setBotId(botTemp.getBotId())
                .setToken(botTemp.getToken());
            if(botTemp.getBotType()==0){
                bot.setBotType(BotToken.botType.PUBLIC_TYPE);
            }else if(botTemp.getBotType()==1){
                bot.setBotType(BotToken.botType.PRIVATE_TYPE);
            }
            bot.setSandBox(botTemp.isSandBox());
        return new R().setData(BotManager.addBot(new SiriusBotClient(bot)));
    }

    /**
     * 获取机器人列表
     * @return 机器人列表
     */
    @GetMapping("/bot/getAll")
    public R getAllBot(){
        List<BotInfo> list = new ArrayList<>();
        Map<Integer, SiriusBotClient> botMap = BotManager.getAllBot();
        Set<Integer> keys = botMap.keySet();
        for (Integer key : keys) {
            SiriusBotClient siriusBotClient = botMap.get(key);
            BotInfo botInfo = new BotInfo();
            botInfo.setBotId(siriusBotClient.getInfo().getBotId()).setToken(siriusBotClient.getInfo().getToken()).setSandBox(siriusBotClient.getInfo().isSandBox()).setBotType(siriusBotClient.getInfo().getBotType().getValue());
            list.add(botInfo);
        }
        return new R().setData(JSONObject.toJSONString(list));
    }


    @Autowired
    RobotMapper robotMapper;

    @GetMapping("/bot/test")
    public void test(){
        robotMapper.insert(
                new Robot()
                        .setBot_id("102004321")
                        .setToken("VwUd3zkSBZIbLlWZOXNlhrBsZDCtn6Dn")
        );
    }

}

package cn.siriusbot.siriuspro.webapi.controller;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.webapi.R.R;
import com.alibaba.fastjson.JSONObject;
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
        Bot bot = new Bot();
        bot.setBotId(botTemp.getBotId())
                .setToken(botTemp.getToken());
            if(botTemp.getBotType()==0){
                bot.setBotType(Bot.botType.PUBLIC_TYPE);
            }else if(botTemp.getBotType()==1){
                bot.setBotType(Bot.botType.PRIVATE_TYPE);
            }
            bot.setSandBox(botTemp.isSandBox());
        return new R().setData(BotManager.addBot(bot));
    }

    /**
     * 获取机器人列表
     * @return 机器人列表
     */
    @GetMapping("/bot/getAll")
    public R getAllBot(){
        List<BotInfo> list = new ArrayList<>();
        Map<Integer, Bot> botMap = BotManager.getAllBot();
        Set<Integer> keys = botMap.keySet();
        for (Integer key : keys) {
            Bot bot = botMap.get(key);
            BotInfo botInfo = new BotInfo();
            botInfo.setBotId(bot.getBotId()).setToken(bot.getToken()).setSandBox(bot.isSandBox()).setBotType(bot.getBotType().getValue());
            list.add(botInfo);
        }
        return new R().setData(JSONObject.toJSONString(list));
    }
}

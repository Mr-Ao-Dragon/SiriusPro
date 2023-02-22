package cn.siriusbot.siriuspro.config.bean;

import cn.siriusbot.siriuspro.admin.dao.RobotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BotServiceCache {

    @Autowired
    RobotMapper robotMapper;

    public int getDatabaseBotCount(){
        return robotMapper.queryAllCount();
    }
}

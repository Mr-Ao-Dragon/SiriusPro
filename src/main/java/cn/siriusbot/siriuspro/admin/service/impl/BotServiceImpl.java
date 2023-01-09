package cn.siriusbot.siriuspro.admin.service.impl;

import cn.siriusbot.siriuspro.admin.dao.RobotMapper;
import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.admin.service.BotService;
import cn.siriusbot.siriuspro.bot.BotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.uitls.AppContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotServiceImpl implements BotService {

    @Autowired
    BotManager botManager;

    @Autowired
    RobotMapper robotMapper;

    /**
     * 添加机器人到数据库
     *
     * @param robot 机器人信息
     */
    @Override
    public void addBot(Robot robot) {


    }

    /**
     * 根据数据库id删除机器人信息
     *
     * @param id 唯一id
     */
    @Override
    public void delBotById(int id) {

    }

    /**
     * 根据机器人id删除机器人信息
     *
     * @param botId 机器人id
     */
    @Override
    public void delBotByBotId(String botId) {

    }

    /**
     * 登录机器人
     *
     * @param robot 机器人信息
     */
    @Override
    public void loginBot(Robot robot) {

    }

    /**
     * 根据数据库id登出机器人
     *
     * @param id 唯一id
     */
    @Override
    public void logoutBotById(int id) {

    }

    /**
     * 根据机器人id登出机器人
     *
     * @param botId 机器人id
     */
    @Override
    public void logoutBotByBotId(String botId) {

    }

    /**
     * 根据id查询机器人信息
     *
     * @param id 唯一id
     * @return 机器人信息实体类
     */
    @Override
    public Robot queryRobotById(int id) {
        return null;
    }

    /**
     * 分页查询所有机器人信息
     *
     * @param page 页数 从0开始
     * @param size 每页大小
     * @return 机器人信息实体类列表
     */
    @Override
    public List<Robot> queryRobotAll(int page, int size) {
        return null;
    }

    /**
     * 根据botId获取机器人客户端实例
     *
     * @param botId 机器人ID
     * @return 机器人客户端实例
     */
    @Override
    public BotClient queryBotClientByBotId(String botId) {
        return null;
    }
}

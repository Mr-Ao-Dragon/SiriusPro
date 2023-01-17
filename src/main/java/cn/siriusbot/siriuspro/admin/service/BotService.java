package cn.siriusbot.siriuspro.admin.service;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.bot.client.BotClient;

import java.util.List;

public interface BotService {
    /**
     * 添加机器人到数据库
     * @param robot 机器人信息
     */
    void addBot(Robot robot);

    /**
     * 根据数据库id删除机器人信息
     * @param id 唯一id
     */
    void delBotById(int id);

    /**
     * 根据机器人id删除机器人信息
     * @param botId 机器人id
     */
    void delBotByBotId(String botId);

    /**
     * 根据机器人ID登录机器人
     * @param botId 机器人ID
     */
    void loginBotByBotId(String botId);

    /**
     * 根据数据库id登录机器人
     * @param id 唯一id
     */
    void loginBotById(int id);

    /**
     * 根据数据库id登出机器人
     * @param id 唯一id
     */
    void logoutBotById(int id);

    /**
     * 根据机器人id登出机器人
     * @param botId 机器人id
     */
    void logoutBotByBotId(String botId);

    /**
     * 根据id查询机器人信息
     * @param id 唯一id
     * @return 机器人信息实体类
     */
    Robot queryRobotById(int id);

    /**
     * 根据机器人id查询机器人信息
     * @param botId 机器人ID
     * @return 机器人信息实体类
     */
    Robot queryRobotByBotId(String botId);

    /**
     * 分页查询所有机器人信息
     * @param page 页数 从0开始
     * @param size 每页大小
     * @return 机器人信息实体类列表
     */
    List<Robot> queryRobotAll(int page, int size);

    /**
     * 根据botId获取机器人客户端实例
     * @param botId 机器人ID
     * @return 机器人客户端实例
     */
    BotClient queryBotClientByBotId(String botId);


    /**
     * 获取当前在线的机器人客户端列表
     * @return 机器人客户端列表
     */
    List<BotClient> queryBotClientAll();
}

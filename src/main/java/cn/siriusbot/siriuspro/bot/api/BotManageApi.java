package cn.siriusbot.siriuspro.bot.api;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.admin.vo.PageRobotList;
import cn.siriusbot.siriuspro.bot.api.proxy.ApiProxy;
import cn.siriusbot.siriuspro.bot.pojo.BotInfoJson;
import cn.siriusbot.siriuspro.bot.pojo.e.IntentsType;

import java.util.List;

public interface BotManageApi extends ApiProxy {
    /**
     * 获取在线的机器人列表
     * @return
     */
    List<BotInfoJson> queryOnLineBotList();

    /**
     * 写配置
     * @param key
     * @param val
     */
    void setServerConfig(String key, String val);

    /**
     * 读配置，不存在返回空字符串
     * @param key
     * @return
     */
    String getServerConfig(String key);

    /**
     * 关闭框架
     */
    void closeFrame();

    /**
     * 添加机器人到数据库
     *
     * @param robot 机器人信息
     */
    void addBot(Robot robot);

    /**
     * 修改机器人到数据库
     *
     * @param robot 机器人信息
     */
    void modifyBot(Robot robot);

    /**
     * 根据数据库id删除机器人信息
     *
     * @param id 唯一id
     */
    void delBotById(int id);

    /**
     * 根据机器人ID登录机器人
     *
     * @param botId 机器人ID
     */
    void loginBotByBotId(String botId);

    /**
     * 根据机器人id登出机器人
     *
     * @param botId 机器人id
     */
    void logoutBotByBotId(String botId);

    /**
     * 分页查询所有机器人信息 [模糊查询]
     *
     * @param page 页数 从0开始
     * @param size 每页大小
     * @return 机器人信息实体类列表
     */
    PageRobotList queryRobotAllByCondition(int page, int size, String botId, String username, Integer state, Integer botType, Boolean sandBox);

    /**
     * 订阅事件
     *
     */
    void subscription(String robotId, int[] intents);
}

package cn.siriusbot.siriuspro.bot.api.impl.bot;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.admin.service.BotService;
import cn.siriusbot.siriuspro.admin.service.IntentService;
import cn.siriusbot.siriuspro.admin.service.ServerConfigService;
import cn.siriusbot.siriuspro.admin.vo.PageRobotList;
import cn.siriusbot.siriuspro.bot.api.BotManageApi;
import cn.siriusbot.siriuspro.bot.pojo.BotInfo;
import cn.siriusbot.siriuspro.bot.pojo.BotInfoJson;
import cn.siriusbot.siriuspro.bot.pojo.e.IntentsType;
import cn.siriusbot.siriuspro.config.bean.BotPool;

import java.util.ArrayList;
import java.util.List;

public class BotManageApiImpl implements BotManageApi {

    String packageName; // 包名
    BotPool botPool;
    ServerConfigService serverConfigService;
    BotService botService;
    IntentService intentService;

    public BotManageApiImpl(String packageName, BotPool botPool, ServerConfigService serverConfigService, BotService botService, IntentService intentService) {
        this.packageName = packageName;
        this.botPool = botPool;
        this.serverConfigService = serverConfigService;
        this.botService = botService;
        this.intentService = intentService;
    }

    /**
     * 获取在线的机器人列表
     *
     * @return
     */
    @Override
    public List<BotInfoJson> queryOnLineBotList() {
        List<BotInfoJson> botInfoJsons = new ArrayList<>();
        List<BotInfo> botInfos = this.botPool.queryOnLineBotList();
        for (BotInfo botInfo : botInfos){
            BotInfoJson botInfoJson = new BotInfoJson(botInfo);
            botInfoJson.setToken(null);
            botInfoJsons.add(botInfoJson);
        }
        return botInfoJsons;
    }

    /**
     * 写配置
     *
     * @param key
     * @param val
     */
    @Override
    public void setServerConfig(String key, String val) {
        serverConfigService.addConfig(packageName + "." + key, val);
    }

    /**
     * 读配置，不存在返回空字符串
     *
     * @param key
     * @return
     */
    @Override
    public String getServerConfig(String key) {
        return serverConfigService.getString(packageName + "." + key);
    }

    /**
     * 关闭框架
     */
    @Override
    public void closeFrame() {
        new Thread(()->{
            try {
                Thread.sleep(1000);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 添加机器人到数据库
     *
     * @param robot 机器人信息
     */
    @Override
    public void addBot(Robot robot) {
        botService.addBot(robot);
    }

    /**
     * 修改机器人到数据库
     *
     * @param robot 机器人信息
     */
    @Override
    public void modifyBot(Robot robot) {
        botService.modifyBot(robot);
    }

    /**
     * 根据数据库id删除机器人信息
     *
     * @param id 唯一id
     */
    @Override
    public void delBotById(int id) {
        botService.delBotById(id);
    }

    /**
     * 根据机器人ID登录机器人
     *
     * @param botId 机器人ID
     */
    @Override
    public void loginBotByBotId(String botId) {
        botService.loginBotByBotId(botId);
    }

    /**
     * 根据机器人id登出机器人
     *
     * @param botId 机器人id
     */
    @Override
    public void logoutBotByBotId(String botId) {
        botService.logoutBotByBotId(botId);
    }

    /**
     * 分页查询所有机器人信息 [模糊查询]
     *
     * @param page     页数 从0开始
     * @param size     每页大小
     * @return 机器人信息实体类列表
     */
    @Override
    public PageRobotList queryRobotAllByCondition(int page, int size, String botId, String username, Integer state, Integer botType, Boolean sandBox) {
        return botService.queryRobotAllByCondition(page, size, botId, username, state, botType, sandBox);
    }

    /**
     * 订阅事件
     *
     * @param robotId
     * @param intents
     */
    @Override
    public void subscription(String robotId, int[] intents) {
        this.intentService.subscription(robotId, intents);
    }
}

package cn.siriusbot.siriuspro.admin.service.impl;

import cn.siriusbot.siriuspro.admin.dao.IntentMapper;
import cn.siriusbot.siriuspro.admin.dao.RobotMapper;
import cn.siriusbot.siriuspro.admin.entity.Intent;
import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.admin.service.BotService;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.client.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.event.IntentsEvent;
import cn.siriusbot.siriuspro.bot.event.impl.IntentsEventImpl;
import cn.siriusbot.siriuspro.bot.pojo.BotInfo;
import cn.siriusbot.siriuspro.bot.pojo.e.BotType;
import cn.siriusbot.siriuspro.bot.pojo.e.IntentsType;
import cn.siriusbot.siriuspro.config.aop.PowerInterceptor;
import cn.siriusbot.siriuspro.config.bean.BotConfig;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.web.R.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class BotServiceImpl implements BotService {

    @Autowired
    BotPool botPool;

    @Autowired
    RobotMapper robotMapper;

    @Autowired
    BotConfig botConfig;

    @Autowired
    IntentMapper intentMapper;

    /**
     * 添加机器人到数据库
     *
     * @param robot 机器人信息
     */
    @Override
    public void addBot(Robot robot) {
        verificationInfo(robot);
        LambdaQueryWrapper<Robot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Robot::getBotId, robot.getBotId());
        Robot temp = robotMapper.selectOne(wrapper);
        if (temp != null) {
            throw new MsgException(10301, "robot已存在！");
        }
        robotMapper.insert(robot);
    }

    /**
     * 修改机器人到数据库
     *
     * @param robot 机器人信息
     */
    @Override
    public void modifyBot(Robot robot) {
        Robot select = robotMapper.selectById(robot.getId());
        if (select == null) {
            throw new MsgException(500, "修改失败，该记录不存在!");
        }
        if (robot.getBotId() != null){
            select.setBotId(robot.getBotId());
        }
        if (robot.getToken() != null){
            select.setToken(robot.getToken());
        }
        if (robot.getSandBox() != null){
            select.setSandBox(robot.getSandBox());
        }
        if (robot.getBotType() != null){
            select.setBotType(robot.getBotType());
        }
        if (robot.getAutoLogin() != null){
            select.setAutoLogin(robot.getAutoLogin());
        }
        robotMapper.updateById(select);
    }

    /**
     * 根据数据库id删除机器人信息
     *
     * @param id 唯一id
     */
    @Override
    public void delBotById(int id) {
        Robot robot = robotMapper.selectById(id);
        if (robot == null) {
            throw new MsgException(10201, "robot对应ID不存在！");
        }
        robotMapper.deleteById(id);
    }

    /**
     * 根据机器人id删除机器人信息
     *
     * @param botId 机器人id
     */
    @Override
    public void delBotByBotId(String botId) {
        LambdaQueryWrapper<Robot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Robot::getBotId, botId);
        Robot robot = robotMapper.selectOne(wrapper);
        if (robot == null) {
            throw new MsgException(10301, "robot对应ID不存在！");
        }
        robotMapper.deleteById(robot.getId());
    }

    /**
     * 登录机器人
     *
     * @param robot 机器人信息
     */
    private void loginBot(Robot robot) {
        verificationInfo(robot);
        BotInfo token = this.robotToBotToken(robot);
        if (botPool.botWhetherThereIs(token.getBotId())) {
            throw new MsgException(10401, String.format("Bot[%s]当前robot已经登录中！", robot.getBotId()));
        }
        BotClient client;
        try {
            client = new SiriusBotClient(token, botConfig);
        } catch (MsgException e){
            throw new MsgException(10401, String.format("Bot[%s]检查令牌失败，请重试！", robot.getBotId()));
        } catch (Throwable e){
            botPool.addErrorBot(token);
            throw e;
        }
        botPool.addBot(client);
        // 配置订阅属性
        LambdaQueryWrapper<Intent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Intent::getRobotId, robot.getBotId());
        List<Intent> intentList = intentMapper.selectList(wrapper);
        List<String> intentNameList = new ArrayList<>();
        IntentsEvent intentsEvent = new IntentsEventImpl();
        for (Intent intent : intentList) {
            IntentsType instance = IntentsType.getInstance(intent.getIntentsType());
            if (instance == null) {
                throw new MsgException(10402, String.format("Bot[%s]订阅事件(%d)失败，参数有误！", robot.getBotId(), intent.getIntentsType()));
            }
            intentsEvent.setIntents(instance);
            intentNameList.add(instance.getName());
        }
        log.info(String.format("Bot[%s]订阅事件 -> %s", robot.getBotId(), intentNameList));
        client.setConfig(
                IntentsEvent.class, intentsEvent
        );
        client.start();
    }

    /**
     * 根据机器人ID登录机器人
     *
     * @param botId 机器人ID
     */
    @Override
    @PowerInterceptor(power = 1)
    public void loginBotByBotId(String botId) {
        LambdaQueryWrapper<Robot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Robot::getBotId, botId);
        Robot robot = robotMapper.selectOne(wrapper);
        if (robot == null) {
            throw new MsgException(10301, "robot对应ID不存在！");
        }
        loginBot(robot);
    }

    /**
     * Robot转BotToken
     *
     * @param robot 数据库实例
     * @return 客户端信息
     */
    private BotInfo robotToBotToken(Robot robot) {
        BotInfo bot = new BotInfo();
        bot
                .setBotId(robot.getBotId())
                .setToken(robot.getToken())
                .setBotType(robot.getBotType() == 0 ? BotType.PUBLIC_TYPE : BotType.PRIVATE_TYPE)
                .setSandBox(robot.getSandBox());
        return bot;
    }

    /**
     * 验证参数数据
     *
     * @param robot 实例对象
     */
    private void verificationInfo(Robot robot) {
        if (robot.getBotType() == null) {
            throw new MsgException(10001, "机器人类别不能为空！");
        }
        if (robot.getSandBox() == null) {
            throw new MsgException(10102, "机器人是否沙河模式不能为空！");
        }
        if (robot.getBotId() == null) {
            throw new MsgException(10103, "机器人ID不能为空！");
        }
        if (robot.getToken() == null) {
            throw new MsgException(10104, "机器人token不能为空！");
        }
        if (robot.getAutoLogin() == null) {
            robot.setAutoLogin(true);   // 默认自动登录
        }
    }

    /**
     * 根据数据库id登录机器人
     *
     * @param id 唯一id
     */
    @Override
    public void loginBotById(int id) {
        Robot robot = robotMapper.selectById(id);
        if (robot == null) {
            throw new MsgException(10201, "robot对应ID不存在！");
        }
        loginBot(robot);
    }

    /**
     * 根据数据库id登出机器人
     *
     * @param id 唯一id
     */
    @Override
    public void logoutBotById(int id) {
        Robot robot = robotMapper.selectById(id);
        if (robot == null) {
            throw new MsgException(10201, "robot对应ID不存在！");
        }
        BotClient client = botPool.getBotById(robot.getBotId());
        client.close();
    }

    /**
     * 根据机器人id登出机器人
     *
     * @param botId 机器人id
     */
    @Override
    public void logoutBotByBotId(String botId) {
        BotClient client = botPool.getBotById(botId);
        client.close();
    }

    /**
     * 根据id查询机器人信息
     *
     * @param id 唯一id
     * @return 机器人信息实体类
     */
    @Override
    public Robot queryRobotById(int id) {
        Robot robot = robotMapper.selectById(id);
        if (robot == null) {
            throw new MsgException(10401, "robot对应ID不存在！");
        }
        this.formatBotOnLine(robot);    // 格式化在线信息
        return robot;
    }

    /**
     * 根据机器人id查询机器人信息
     *
     * @param botId 机器人ID
     * @return 机器人信息实体类
     */
    @Override
    public Robot queryRobotByBotId(String botId) {
        LambdaQueryWrapper<Robot> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Robot::getBotId, botId);
        Robot robot = robotMapper.selectOne(wrapper);
        if (robot == null) {
            throw new MsgException(10301, "robot对应ID不存在！");
        }
        this.formatBotOnLine(robot);    // 格式化在线信息
        return robot;
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
        //分页参数
        Page<Robot> robotPage = robotMapper.selectPage(new Page<>(page + 1, size), null);
        List<Robot> records = robotPage.getRecords();
        for (Robot robot : records) {
            this.formatBotOnLine(robot);    // 格式化在线信息
        }
        return records;
    }

    /**
     * 分页查询所有机器人信息 [模糊查询]
     *
     * @param page     页数 从0开始
     * @param size     每页大小
     * @param botId
     * @param username
     * @param state
     * @param botType
     * @param sandBox
     * @return 机器人信息实体类列表
     */
    @Override
    public List<Robot> queryRobotAllByCondition(int page, int size, String botId, String username, Integer state, Integer botType, Boolean sandBox) {
        LambdaQueryWrapper<Robot> wrapper = new LambdaQueryWrapper<>();
        if (!ObjectUtils.isEmpty(botId)) {
            wrapper.and(e -> e.like(Robot::getBotId, botId));
        }
        if (!ObjectUtils.isEmpty(botType)) {
            wrapper.and(e -> e.eq(Robot::getBotType, botType));
        }
        if (!ObjectUtils.isEmpty(sandBox)) {
            wrapper.and(e -> e.eq(Robot::getSandBox, sandBox));
        }
        List<Robot> robots = robotMapper.selectList(wrapper);
        for (Robot robot : robots) {
            System.out.println(robot);
            this.formatBotOnLine(robot);    // 格式化在线信息
            System.out.println(robot);
        }
        // 搜索机器人昵称
        if (!ObjectUtils.isEmpty(username)) {
            robots.removeIf(robot -> robot.getUsername() == null || !robot.getUsername().contains(username));
        }
        // 搜索机器人登录状态
        if (!ObjectUtils.isEmpty(state)) {
            robots.removeIf(robot -> robot.getState() == null || !robot.getState().equals(state));
        }
        // 分页
        if (robots.size() == 0){
            return new ArrayList<>();
        }
        int pageSize = robots.size() / size;
        if (robots.size() % size != 0){
            pageSize += 1;
        }
        if (page >= pageSize){
            page = pageSize - 1;
        }
        int start = page * size;
        List<Robot> reply = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int index = start + i;
            if (index >= robots.size()){
                break;
            }
            reply.add(robots.get(index));
        }
        return reply;
    }

    @Override
    public int queryRobotAllCount() {
        return robotMapper.queryAllCount();
    }

    private void formatBotOnLine(Robot robot) {
        BotInfo botInfo = botPool.queryBotInfoByBotId(robot.getBotId());
        if (botInfo != null) {
            robot.setState(botInfo.getState());
            robot.setUsername(botInfo.getUsername());
            if (robot.getState() == Robot.STATE_ERROR) {
                robot.setErrorInfo(botInfo.getErrorInfo());
            }
        }
    }

    /**
     * 根据botId获取机器人客户端实例
     *
     * @param botId 机器人ID
     * @return 机器人客户端实例
     */
    @Override
    public BotClient queryBotClientByBotId(String botId) {
        return botPool.getBotById(botId);
    }

    /**
     * 获取当前在线的机器人客户端列表
     *
     * @return 机器人客户端列表
     */
    @Override
    public List<BotClient> queryBotClientAll() {
        return botPool.getAllClient();
    }

    /**
     * 自动登录机器人
     */
    @Override
    public void autoLoginBot() {
        List<Robot> robots = this.queryRobotAll(0, 10000);
        for (Robot robot : robots) {
            if (robot.getAutoLogin()) {
                try {
                    log.info(String.format("Bot[%s] 自动登录", robot.getBotId()));
                    loginBot(robot);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error(String.format("Bot[%s] 登录失败,失败原因:%s", robot.getBotId(), e.getCause()));
                }
            }
        }
    }
}

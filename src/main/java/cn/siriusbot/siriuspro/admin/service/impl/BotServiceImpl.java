package cn.siriusbot.siriuspro.admin.service.impl;

import cn.siriusbot.siriuspro.admin.dao.RobotMapper;
import cn.siriusbot.siriuspro.admin.entity.Robot;
import cn.siriusbot.siriuspro.admin.service.BotService;
import cn.siriusbot.siriuspro.bot.BotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.BotToken;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.config.aop.ServiceInterceptor;
import cn.siriusbot.siriuspro.error.MsgException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Override
    @ServiceInterceptor
    public void loginBot(Robot robot) {
        verificationInfo(robot);
        BotToken token = robotToBotToken(robot);
        SiriusBotClient siriusBotClient = new SiriusBotClient(token);
        botManager.addBot(siriusBotClient);
        if (!botManager.AuthBot(siriusBotClient)){
            throw new MsgException(10401, "验证机器人信息不通过，登录失败!");
        }
        botManager.loginBot(robot.getBotId());
    }

    /**
     * 根据机器人ID登录机器人
     *
     * @param botId 机器人ID
     */
    @Override
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
    private BotToken robotToBotToken(Robot robot) {
        BotToken bot = new BotToken();
        bot
                .setBotId(robot.getBotId())
                .setToken(robot.getToken())
                .setBotType(robot.getBotType() == 0 ? BotToken.botType.PUBLIC_TYPE : BotToken.botType.PRIVATE_TYPE)
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
        botManager.logoutBot(robot.getBotId());
    }

    /**
     * 根据机器人id登出机器人
     *
     * @param botId 机器人id
     */
    @Override
    public void logoutBotByBotId(String botId) {
        botManager.logoutBot(botId);
    }

    /**
     * 根据id查询机器人信息
     *
     * @param id 唯一id
     * @return 机器人信息实体类
     */
    @Override
    public Robot queryRobotById(int id) {
        return robotMapper.selectById(id);
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
        return robotPage.getRecords();
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

    /**
     * 获取当前在线的机器人客户端列表
     *
     * @return 机器人客户端列表
     */
    @Override
    public List<BotClient> queryBotClientAll() {
        return new ArrayList<>(botManager.getAllBot().values());
    }
}

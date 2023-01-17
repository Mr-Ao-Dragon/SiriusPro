package cn.siriusbot.siriuspro.admin.service.impl;

import cn.siriusbot.siriuspro.admin.dao.RobotMapper;
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
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BotServiceImpl implements BotService {

    @Autowired
    BotPool botPool;

    @Autowired
    RobotMapper robotMapper;

    @Autowired
    BotConfig botConfig;

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
    private void loginBot(Robot robot) {
        verificationInfo(robot);
        BotInfo token = this.robotToBotToken(robot);
        BotClient client = new SiriusBotClient(token, botConfig);
        if (token.getBotType() == BotType.PUBLIC_TYPE){
            client.setConfig(
                    IntentsEvent.class, new IntentsEventImpl()
                            .setIntents(IntentsType.PUBLIC_ALL)
            );
        } else {
            client.setConfig(
                    IntentsEvent.class, new IntentsEventImpl()
                            .setIntents(IntentsType.ALL)
            );
        }
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
        return botPool.getAllClient();
    }
}

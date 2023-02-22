package cn.siriusbot.siriuspro.admin.service.impl;

import cn.siriusbot.siriuspro.admin.dao.IntentMapper;
import cn.siriusbot.siriuspro.admin.entity.Intent;
import cn.siriusbot.siriuspro.admin.service.IntentService;
import cn.siriusbot.siriuspro.bot.pojo.e.IntentsType;
import cn.siriusbot.siriuspro.error.MsgException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Log4j2
public class IntentServiceImpl implements IntentService {

    @Autowired
    IntentMapper intentMapper;

    /**
     * 订阅事件
     *
     * @param robotId
     * @param intents
     */
    @Override
    @Transactional
    public void subscription(String robotId, IntentsType[] intents) {
        int[] ints = new int[intents.length];
        for (int i = 0; i < intents.length; i++) {
            ints[i] = intents[i].getVal();
        }
        this.subscription(robotId, ints);
    }

    /**
     * 订阅事件
     *
     * @param robotId
     * @param intents
     */
    @Override
    @Transactional
    public void subscription(String robotId, int[] intents) {
        LambdaQueryWrapper<Intent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Intent::getRobotId, robotId);
        List<Intent> intentList = intentMapper.selectList(wrapper);
        Set<Integer> integers = new HashSet<>();
        for (int i : intents){
            integers.add(i);
        }
        // 执行删除
        for (Intent intent : intentList){
            if (!integers.contains(intent.getIntentsType())){
                intentMapper.deleteById(intent);
            } else {
                integers.remove(intent.getIntentsType());
            }
        }
        // 执行添加
        for (Integer i : integers){
            IntentsType instance = IntentsType.getInstance(i);
            if (instance == null){
                throw new MsgException(11101, String.format("订阅事件(%d)有误", i));
            }
            intentMapper.insert(
                    new Intent()
                            .setRobotId(robotId)
                            .setIntentsType(i)
                            .setIntentsName(instance.getName())
            );
        }
    }

    /**
     * 查询所有订阅事件
     *
     * @param robotId
     * @return
     */
    @Override
    public List<Intent> queryAllByRobotId(String robotId) {
        LambdaQueryWrapper<Intent> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Intent::getRobotId, robotId);
        return intentMapper.selectList(wrapper);
    }
}

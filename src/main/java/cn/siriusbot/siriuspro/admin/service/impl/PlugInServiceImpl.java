package cn.siriusbot.siriuspro.admin.service.impl;

import cn.siriusbot.siriuspro.admin.service.PlugInService;
import cn.siriusbot.siriuspro.admin.vo.PlugInInfo;
import cn.siriusbot.siriuspro.bot.plugin.PlugInClient;
import cn.siriusbot.siriuspro.bot.plugin.PlugInFactory;
import cn.siriusbot.siriuspro.config.bean.StatisticsPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlugInServiceImpl implements PlugInService {

    @Autowired
    PlugInFactory plugInFactory;

    @Autowired
    StatisticsPool statisticsPool;

    /**
     * 获取所有插件信息
     */
    @Override
    public List<PlugInInfo> queryAllPlugInList() {
        List<PlugInClient> plugInClients = plugInFactory.queryAllClient();
        List<PlugInInfo> plugInInfos = new ArrayList<>();
        for (PlugInClient client : plugInClients){
            plugInInfos.add(
                    new PlugInInfo()
                            .setBasic(client.getInfo())
                            .setResponseNum(statisticsPool.queryResponseNumByPackageName(client.getPackageName()))
            );
        }
        plugInInfos.sort((o1, o2) -> o2.getResponseNum() - o1.getResponseNum());
        return plugInInfos;
    }
}

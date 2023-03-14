package cn.siriusbot.siriuspro.admin.service;

import cn.siriusbot.siriuspro.admin.vo.PlugInInfo;
import cn.siriusbot.siriuspro.bot.plugin.PlugInClient;

import java.util.List;

public interface PlugInService {

    /**
     * 获取所有插件信息
     */
    List<PlugInInfo> queryAllPlugInList();

    /**
     * 插件添加到数据库
     * @param client
     */
    void addDatabase(PlugInClient client);
}

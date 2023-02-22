package cn.siriusbot.siriuspro.admin.service;

import cn.siriusbot.siriuspro.admin.vo.PlugInInfo;

import java.util.List;

public interface PlugInService {

    /**
     * 获取所有插件信息
     */
    List<PlugInInfo> queryAllPlugInList();
}

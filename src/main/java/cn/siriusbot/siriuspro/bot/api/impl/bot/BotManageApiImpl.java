package cn.siriusbot.siriuspro.bot.api.impl.bot;

import cn.siriusbot.siriuspro.admin.service.ServerConfigService;
import cn.siriusbot.siriuspro.bot.api.BotManageApi;
import cn.siriusbot.siriuspro.bot.pojo.BotInfo;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public class BotManageApiImpl implements BotManageApi {

    String packageName; // 包名
    BotPool botPool;
    ServerConfigService serverConfigService;

    public BotManageApiImpl(String packageName, BotPool botPool, ServerConfigService serverConfigService) {
        this.packageName = packageName;
        this.botPool = botPool;
        this.serverConfigService = serverConfigService;
    }

    /**
     * 获取在线的机器人列表
     *
     * @return
     */
    @Override
    public List<BotInfo> queryOnLineBotList() {
        return this.botPool.queryOnLineBotList();
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
}
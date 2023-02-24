package cn.siriusbot.siriuspro.bot.api.impl.bot;

import cn.siriusbot.siriuspro.admin.service.ServerConfigService;
import cn.siriusbot.siriuspro.bot.api.BotManageApi;
import cn.siriusbot.siriuspro.bot.pojo.BotInfo;
import cn.siriusbot.siriuspro.bot.pojo.BotInfoJson;
import cn.siriusbot.siriuspro.config.bean.BotPool;

import java.util.ArrayList;
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
    public List<BotInfoJson> queryOnLineBotList() {
        List<BotInfo> botInfos = this.botPool.queryOnLineBotList();
        List<BotInfoJson> botInfoJsons = new ArrayList<>();
        for (BotInfo botInfo : botInfos){
            botInfoJsons.add(new BotInfoJson(botInfo));
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
        System.exit(0);
    }
}

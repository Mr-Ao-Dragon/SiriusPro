package cn.siriusbot.siriuspro.admin.service.impl;

import cn.siriusbot.siriuspro.admin.dao.ServerConfigMapper;
import cn.siriusbot.siriuspro.admin.entity.ServerConfig;
import cn.siriusbot.siriuspro.admin.service.ServerConfigService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerConfigServiceImpl implements ServerConfigService {

    @Autowired
    ServerConfigMapper serverConfigMapper;

    @Override
    public void addConfig(String key, String val) {
        LambdaQueryWrapper<ServerConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServerConfig::getKey, key);
        ServerConfig serverConfig = serverConfigMapper.selectOne(wrapper);
        if (serverConfig != null){
            serverConfig.setVal(val);
            serverConfigMapper.updateById(serverConfig);
            return;
        }
        serverConfigMapper.insert(
                new ServerConfig()
                        .setKey(key)
                        .setVal(val)
        );
    }

    @Override
    public String getString(String key) {
        LambdaQueryWrapper<ServerConfig> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ServerConfig::getKey, key);
        ServerConfig serverConfig = serverConfigMapper.selectOne(wrapper);
        if (serverConfig == null){
            return "";
        }
        return serverConfig.getVal();
    }
}

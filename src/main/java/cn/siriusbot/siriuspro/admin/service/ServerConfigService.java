package cn.siriusbot.siriuspro.admin.service;


public interface ServerConfigService {

    void addConfig(String key, String val);

    String getString(String key);
}

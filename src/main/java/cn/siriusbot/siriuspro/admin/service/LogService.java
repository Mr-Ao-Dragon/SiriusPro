package cn.siriusbot.siriuspro.admin.service;

import java.util.List;

/**
 * 日志处理类
 */
public interface LogService {

    List<String> getLogLately();

    /**
     * 推入日志
     */
    void pushLog(String log);

    /**
     * 注册监听
     */
    void registerListener(String sessionId);

    /**
     * 删除监听
     */
    void deleteListener(String sessionId);

    /**
     * 根据会话获取日志信息
     */
    List<String> getLogBySession(String sessionId);
}

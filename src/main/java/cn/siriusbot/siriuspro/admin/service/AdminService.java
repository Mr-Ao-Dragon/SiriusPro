package cn.siriusbot.siriuspro.admin.service;

import cn.siriusbot.siriuspro.admin.entity.Admin;

public interface AdminService {
    /**
     * 登录管理员
     * @param account 账号
     * @param passwd 密码
     * @return 用户对象
     */
    Admin login(String account, String passwd);

    /**
     * 修改密码
     */
    void changePassword(String oldPasswd, String passwd);
}

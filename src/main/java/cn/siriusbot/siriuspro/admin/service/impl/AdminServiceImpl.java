package cn.siriusbot.siriuspro.admin.service.impl;

import cn.siriusbot.siriuspro.admin.dao.AdminMapper;
import cn.siriusbot.siriuspro.admin.entity.Admin;
import cn.siriusbot.siriuspro.admin.service.AdminService;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.uitls.HTTPUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    /**
     * 登录管理员
     *
     * @param account 账号
     * @param passwd  密码
     * @return 用户对象
     */
    @Override
    public Admin login(String account, String passwd) {
        if (account == null){
            throw new MsgException(20101, "用户不能为空！");
        }
        if (passwd == null){
            throw new MsgException(20102, "密码不能为空！");
        }
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getAccount, account);
        Admin admin = adminMapper.selectOne(wrapper);
        if (admin == null) {
            throw new MsgException(20103, "管理员账户不存在！");
        }
        if (!admin.getPasswd().equals(passwd)){
            throw new MsgException(20104, "密码错误！");
        }
        return admin;
    }

    /**
     * 修改密码
     *
     * @param oldPasswd
     * @param passwd
     */
    @Override
    public void changePassword(String oldPasswd, String passwd) {
        Admin admin = HTTPUtil.getAdmin();
        admin = adminMapper.selectById(admin.getId());
        if (admin == null){
            throw new MsgException(500, "用户不存在!");
        }
        if (!admin.getPasswd().equals(oldPasswd)){
            throw new MsgException(500, "旧密码错误!");
        }
        if (ObjectUtils.isEmpty(passwd)){
            throw new MsgException(500, "新密码不能为空!");
        }
        admin.setPasswd(passwd);
        adminMapper.updateById(admin);
    }
}

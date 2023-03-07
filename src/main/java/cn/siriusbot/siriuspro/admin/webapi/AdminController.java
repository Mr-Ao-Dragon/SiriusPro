package cn.siriusbot.siriuspro.admin.webapi;

import cn.siriusbot.siriuspro.admin.entity.Admin;
import cn.siriusbot.siriuspro.admin.service.AdminService;
import cn.siriusbot.siriuspro.config.Constant;
import cn.siriusbot.siriuspro.config.aop.PowerInterceptor;
import cn.siriusbot.siriuspro.web.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    public R login(
            @RequestParam(value = "account", required = false) String account,
            @RequestParam(value = "passwd", required = false) String passwd,
            HttpSession session
    ) {
        Admin login = adminService.login(account, passwd);
        session.setAttribute(Constant.SESSION_ADMIN, login);
        return new R().setMsg("登录成功!");
    }

    @PowerInterceptor(power = 0)
    @RequestMapping("/change-password")
    public R changePassword(
            @RequestParam(value = "old_passwd", required = false) String oldPasswd,
            @RequestParam(value = "passwd", required = false) String passwd
    ) {
        adminService.changePassword(oldPasswd, passwd);
        return new R().setMsg("修改密码成功!");
    }
}

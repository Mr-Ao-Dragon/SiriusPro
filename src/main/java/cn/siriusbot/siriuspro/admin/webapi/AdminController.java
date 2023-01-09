package cn.siriusbot.siriuspro.admin.webapi;

import cn.siriusbot.siriuspro.admin.entity.Admin;
import cn.siriusbot.siriuspro.admin.service.AdminService;
import cn.siriusbot.siriuspro.config.Constant;
import cn.siriusbot.siriuspro.webapi.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
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
}

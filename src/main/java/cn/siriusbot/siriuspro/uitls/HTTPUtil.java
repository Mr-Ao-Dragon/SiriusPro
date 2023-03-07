package cn.siriusbot.siriuspro.uitls;

import cn.siriusbot.siriuspro.admin.entity.Admin;
import cn.siriusbot.siriuspro.config.Constant;
import cn.siriusbot.siriuspro.error.MsgException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class HTTPUtil {


    public static HttpSession getSession(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null){
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest().getSession();
    }



    public static Admin getAdmin(){
        HttpSession session = getSession();
        if (session != null){
            Object attribute = session.getAttribute(Constant.SESSION_ADMIN);
            if (attribute instanceof Admin data){
                return data;
            }
        }
        throw new MsgException(500, "获取当前用户权限失败!");
    }
}

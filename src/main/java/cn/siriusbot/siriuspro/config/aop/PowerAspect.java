package cn.siriusbot.siriuspro.config.aop;


import cn.siriusbot.siriuspro.admin.entity.Admin;
import cn.siriusbot.siriuspro.config.Constant;
import cn.siriusbot.siriuspro.error.MsgException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

@Aspect
@Component
public class PowerAspect {

    @Pointcut("@annotation(cn.siriusbot.siriuspro.config.aop.PowerInterceptor)")
    public void fun(){}

    @Around("fun()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Class<?> targetClass = joinPoint.getTarget().getClass();

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        PowerInterceptor annotation = method.getAnnotation(PowerInterceptor.class);
        if (annotation == null){
            // 如果方法没有注解，则获取类注解
            annotation = targetClass.getAnnotation(PowerInterceptor.class);
        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null && annotation != null){
            HttpSession session = ((ServletRequestAttributes) requestAttributes).getRequest().getSession();
            Admin admin = (Admin) session.getAttribute(Constant.SESSION_ADMIN);
            if (admin == null){
                throw new MsgException(90002, "未登录，无权限使用!");
            }
            if (admin.getPower() > annotation.power()){
                throw new MsgException(90002, "当前用户无权限使用!");
            }
            return joinPoint.proceed();
        }
        throw new MsgException(90003, "权限授权异常，请联系管理员!");
    }

    @Pointcut("@within(cn.siriusbot.siriuspro.config.aop.PowerInterceptor)")
    public void clazz(){}


    @Around("clazz()")
    public Object clazzAround(ProceedingJoinPoint joinPoint) throws Throwable{
        return around(joinPoint);
    }
}

package cn.siriusbot.siriuspro.config.aop;


import lombok.extern.log4j.Log4j2;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

@Log4j2
public class Interceptor implements MethodInterceptor {

    @Override
    public Object invoke(@NotNull MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        log.info("代理调用："  + method.getName());
        return methodInvocation.proceed();
    }
}

package cn.siriusbot.siriuspro.config.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class PowerAspect {

    @Pointcut("@annotation(cn.siriusbot.siriuspro.config.aop.ServiceInterceptor)")
    public void fun(){}

    @Around("fun()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println(joinPoint);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        Object result = null;
        long startTime = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
        }catch (Throwable e){
            e.fillInStackTrace();
            throw e;
        }
        finally {
            long spendTime = (System.currentTimeMillis() - startTime);
            System.out.println(method.getDeclaringClass().getName()+"."+method.getName()+"执行耗时："+spendTime+"ms");
        }

        return result;
    }

}

package cn.siriusbot.siriuspro.config.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface PowerInterceptor {
    /**
     * 权限 0最大 依次往下 ，必须小于等于该值才可以继续执行
     * @return
     */
    int power() default 0;
}

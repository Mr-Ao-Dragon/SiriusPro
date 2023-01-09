package cn.siriusbot.siriuspro.config.aop;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ServiceInterceptor {
}

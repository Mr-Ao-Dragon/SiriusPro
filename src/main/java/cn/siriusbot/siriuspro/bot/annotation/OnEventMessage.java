package cn.siriusbot.siriuspro.bot.annotation;

import cn.siriusbot.siriuspro.bot.pojo.e.MessageType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 插件监听事件
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OnEventMessage {
    MessageType type();
}

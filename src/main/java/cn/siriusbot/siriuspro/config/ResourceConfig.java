package cn.siriusbot.siriuspro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class ResourceConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")    // 虚拟路径
                // file: 表示以本地的路径方式去访问绝对路径。
                .addResourceLocations("classpath:/static/");    // 相对路径
        // .addResourceLocations("file:");    // 绝对路径
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry
                .addViewController( "/" )
                .setViewName( "forward:/index.html" );


    }
}
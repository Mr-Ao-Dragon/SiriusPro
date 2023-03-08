package cn.siriusbot.siriuspro.config;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import cn.siriusbot.siriuspro.admin.service.LogService;
import cn.siriusbot.siriuspro.uitls.AppContextUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@EqualsAndHashCode(callSuper = true)
@Component
@Data
public class LogFilter extends UnsynchronizedAppenderBase<ILoggingEvent> {

    PatternLayout layout;

    LogService logService;



    @Override
    protected void append(ILoggingEvent event) {
        if (logService == null){
            logService = AppContextUtil.getBean(LogService.class);
        }
        logService.pushLog(layout.doLayout(event));
    }
}
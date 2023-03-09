package cn.siriusbot.siriuspro.config;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import cn.siriusbot.siriuspro.admin.service.LogService;
import cn.siriusbot.siriuspro.uitls.AppContextUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

@EqualsAndHashCode(callSuper = true)
@Component
@Data
public class LogFilter extends UnsynchronizedAppenderBase<ILoggingEvent> {

    PatternLayout layout;

    LogService logService;


    @Override
    protected void append(ILoggingEvent event) {
        if (logService == null) {
            logService = AppContextUtil.getBean(LogService.class);
        }
        String s = layout.doLayout(event);
        String[] split = s.split("\\{----分割线----}");
        split[3] = split[3].substring(1, split[3].length() - 1);
        split[1] = split[1].replaceAll(" ", "");
        String sb = "<span class=\"colour-time\">" +
                split[0] +
                "</span>" +
                ' ' +
                "<span class=\"" +
                (split[1].equals("INFO") ? "colour-level" : (split[1].equals("ERROR") ? "colour-level-error" : "colour-level-warning")) +
                "\">" +
                split[1] +
                "</span>" +
                ' ' +
                "<span class=\"colour-pid\">" +
                split[2] +
                "</span>" +
                ' ' +
                '[' +
                "<span class=\"thread-name\">" +
                split[3] +
                "</span>" +
                ']' +
                ' ' +
                "<span class=\"colour-class\">" +
                split[4] +
                "</span>" +
                " : " +
                split[5];
        logService.pushLog(sb);
    }
}
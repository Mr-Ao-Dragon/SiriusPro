package cn.siriusbot.siriuspro.error;

import cn.siriusbot.siriuspro.webapi.R.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(MsgException.class)
    public R msgException(MsgException ex){
        return ex.getR();
    }

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public R globalException(HttpServletRequest request, Throwable ex){
        log.error(request.getRequestURI() +  "请求异常错误："  + ex);
        return new R()
                .setData(50000)
                .setMsg("请求失败，未知错误!")
                .setError(ex.getMessage());
    }
}
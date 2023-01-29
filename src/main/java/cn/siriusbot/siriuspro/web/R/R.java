package cn.siriusbot.siriuspro.web.R;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用于webApi一致性返回
 */
@Data
@Accessors(chain = true)
public class R {

    /**
     * 返回数据
     */
    Object data;

    /**
     * 请求状态码
     */
    int code = 0;

    /**
     * 请求结果
     */
    String msg = "success";

    /**
     * 错误信息
     */
    String error;
}

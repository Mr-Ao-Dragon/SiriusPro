package cn.siriusbot.siriuspro.webapi.R;

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
    int code = 200;

    /**
     * 请求结果
     */
    String msg = "success";
}

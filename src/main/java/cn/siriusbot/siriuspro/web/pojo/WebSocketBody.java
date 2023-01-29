package cn.siriusbot.siriuspro.web.pojo;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WebSocketBody {
    int code;
    JSONObject body;
}

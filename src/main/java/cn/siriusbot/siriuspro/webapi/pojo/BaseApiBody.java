package cn.siriusbot.siriuspro.webapi.pojo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseApiBody {
    String api;
    String method;
    JSONObject param;
}

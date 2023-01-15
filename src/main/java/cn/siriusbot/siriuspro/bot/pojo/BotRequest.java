package cn.siriusbot.siriuspro.bot.pojo;

import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import com.alibaba.fastjson2.JSON;

import java.util.HashMap;
import java.util.Map;

public class BotRequest {
    StringBuilder url;
    String mediaType;
    String requestBody;
    Map<String, String> header = new HashMap<>();
    Map<String, Object> map = new HashMap<>();
    RequestMethod method;

    public BotRequest() {
        this.addHeader("Content-Type", "application/json;text/plain"); // 默认为json请求
    }
    public String getUrl() {
        return url.toString();
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getRequestBody() {
        if (requestBody == null){
            return JSON.toJSONString(this.map);
        }
        return requestBody;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public BotRequest setMethod(RequestMethod method) {
        this.method = method;
        return this;
    }

    public BotRequest setRequestBody(String requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public BotRequest setRequestBodyObject(Object requestBody) {
        this.requestBody = JSON.toJSONString(requestBody);
        return this;
    }

    public BotRequest putRequestBody(String key, Object val){
        this.map.put(key, val);
        return this;
    }

    public BotRequest setMediaType(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public BotRequest appendUrl(String url) {
        this.url.append(url);
        return this;
    }

    public BotRequest setUrl(String url) {
        this.url = new StringBuilder(url);
        return this;
    }

    public BotRequest addHeader(String key, String val){
        this.header.put(key, val);
        return this;
    }



}

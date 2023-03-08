package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestBodyType;
import cn.siriusbot.siriuspro.error.MsgException;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class BotHttpEventImpl implements BotHttpEvent {

    /**
     * HttpClient单例
     */
    public static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .callTimeout(Duration.ofSeconds(10))     // 设置超时时间为10秒
            .build();

    BotClient client;

    Map<Integer, String> errorMsg = new ConcurrentHashMap<>();

    /**
     * 初始化错误信息
     */
    private void initializeTheErrorMessage(){
        errorMsg.put(10001, "UnknownAccount 账号异常");
    }

    /**
     * 注入客户端对象，并初始化
     *
     * @param client
     */
    @Override
    public void init(BotClient client) {
        this.client = client;
        initializeTheErrorMessage();
    }

    /**
     * 机器人登录事件
     */
    @Override
    public void start() {

    }

    /**
     * http请求
     *
     * @param request 请求对象
     * @return 响应内容
     */
    @Override
    public String request(BotRequest request) {
        request
                .addHeader("Authorization", "Bot " + client.getInfo().getBotId() + "." + client.getInfo().getToken())
                .addHeader("User-Agent", "SiriusBot");
        // 构建外部请求对象
        Request toRequest = botRequestToRequest(request);
        try {
            Response response = httpClient.newCall(toRequest).execute();
            switch (response.code()) {
                case 200 -> {
                    return Objects.requireNonNull(response.body()).string();
                }
                default -> {
                    throw new MsgException(500, String.format("httpClient请求错误代码:%d，body:%s", response.code(), response.body().string()));
                }
            }
        } catch (MsgException e) {
            log.error(e);
            throw e;
        } catch (Exception e) {
            log.error(e);
            throw new MsgException(500, "httpClient请求错误!");
        }
    }

    private void disposeResponse(Response response) throws IOException {
        String body = Objects.requireNonNull(response.body()).string();
        JSONObject json = JSONObject.parseObject(body);
        if (json.getInteger("code") != null){
            String msg = errorMsg.get(json.getInteger("code"));
            if (msg != null){
                throw new MsgException(json.getInteger("code"), msg);
            }
        }
    }

    /**
     * http请求
     *
     * @param request 请求对象
     * @return Bot专属响应体
     */
    @Override
    public BotResponse req(BotRequest request) {
        request
                .addHeader("Authorization", "Bot " + client.getInfo().getBotId() + "." + client.getInfo().getToken())
                .addHeader("User-Agent", "SiriusBot");
        // 构建外部请求对象
        Request toRequest = botRequestToRequest(request);
        try {
            Response response = httpClient.newCall(toRequest).execute();
            switch (response.code()) {
                case 202, 200, 204 -> {
                    return new BotResponse()
                            .setCode(response.code())
                            .setBody(Objects.requireNonNull(response.body()).string())
                            .setTraceId(response.header("X-Tps-trace-ID"));
                }
                case 405 -> throw new MsgException(405, "http method 不允许");
                case 429 -> throw new MsgException(405, "频率限制");
                case 404 -> throw new MsgException(405, "未找到 API");
                case 500 -> {
                    String body = Objects.requireNonNull(response.body()).string();
                    this.disposeResponse(response);
                    return new BotResponse()
                            .setCode(500)
                            .setBody(body)
                            .setTraceId(response.header("X-Tps-trace-ID"));
                }
                default -> {
                    this.disposeResponse(response);
                    throw new MsgException(response.code(), String.format("httpClient请求错误代码:%d，body:%s,X-Tps-trace-ID：%s", response.code(), response.body().string(), response.header("X-Tps-trace-ID")));
                }
            }
        } catch (MsgException e) {
            log.error(e);
            throw e;
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
            throw new MsgException(500, "httpClient请求错误!");
        }
    }

    private Request botRequestToRequest(BotRequest request) {
        Request.Builder builder = new Request.Builder()
                .url(request.getUrl());
        for (String key : request.getHeader().keySet()) {
            builder.addHeader(key, request.getHeader().get(key));
        }
        switch (request.getMethod()) {
            case POST -> {
                RequestBody requestBody = botRequestToRequestBody(request);
                builder.post(requestBody);
            }
            case PUT -> {
                RequestBody requestBody = botRequestToRequestBody(request);
                builder.put(requestBody);
            }
            case PATCH -> {
                RequestBody requestBody = botRequestToRequestBody(request);
                builder.patch(requestBody);
            }
            case DELETE -> {
                RequestBody requestBody = botRequestToRequestBody(request);
                builder.delete(requestBody);
            }
        }
        return builder.build();
    }

    private RequestBody botRequestToRequestBody(BotRequest request) {
        if (request.getBodyType() == RequestBodyType.FORM) {
            // 发送图文
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            for (String key : request.getMap().keySet()) {
                Object o = request.getMap().get(key);
                if (o instanceof String s) {
                    builder.addFormDataPart(key, s);
                }
                if (o instanceof File file) {
                    builder.addFormDataPart(key, file.getAbsolutePath(), RequestBody.create(file, MediaType.parse(request.getMediaType())));
                }
            }
            return builder.build();
        }
        return RequestBody.create(request.getRequestBody(), MediaType.parse(request.getMediaType()));
    }
}

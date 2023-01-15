package cn.siriusbot.siriuspro.bot.event.impl;

import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.error.MsgException;
import lombok.extern.log4j.Log4j2;
import okhttp3.*;

import java.time.Duration;
import java.util.Objects;

@Log4j2
public class BotHttpEventImpl implements BotHttpEvent {

    /**
     * HttpClient单例
     */
    public static final OkHttpClient httpClient = new OkHttpClient.Builder()
            .callTimeout(Duration.ofSeconds(10))     // 设置超时时间为10秒
            .build();

    BotClient client;

    /**
     * 注入客户端对象，并初始化
     *
     * @param client
     */
    @Override
    public void init(BotClient client) {
        this.client = client;
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
            switch (response.code()){
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
        return RequestBody.create(request.getRequestBody(), MediaType.parse(request.getMediaType()));
    }
}
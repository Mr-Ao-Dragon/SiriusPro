package cn.siriusbot.siriuspro.http;


import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.BotManager;
import okhttp3.*;


public class SiriusHttpUtils extends OkHttpClient {



    /**
     * get请求
     *
     * @param siriusBotClient     机器人对象
     * @param request 请求对象
     * @return 响应内容
     */
    public static Response getRequest(SiriusBotClient siriusBotClient, Request request) {
        request = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bot " + siriusBotClient.getInfo().getBotId() + "." + siriusBotClient.getInfo().getToken())
                .addHeader("User-Agent", "SiriusBot").build();
        Request build = request.newBuilder().get().build();
        Response response = null;
        try {
            response = siriusBotClient.getHttpClient().newCall(build).execute();
        } catch (Exception e) {
            System.out.println(e);
            return response;
        }
        return response;
    }


    /**
     * post请求
     *
     * @param siriusBotClient     机器人对象
     * @param request 请求对象
     * @param data    请求数据
     * @return 返回响应对象
     */
    public static Response postRequest(SiriusBotClient siriusBotClient, Request request, RequestBody data) {
            request = request.newBuilder()
                    .addHeader("Authorization", "Bot " + siriusBotClient.getInfo().getBotId() + "." + siriusBotClient.getInfo().getToken())
                    .addHeader("User-Agent", "SiriusBot")
                    .addHeader("Content-Type", "application/json")
                    .post(data).build();
        Request build = request.newBuilder().post(data).build();
        Response response = null;
        try {
            response = siriusBotClient.getHttpClient().newCall(build).execute();
        } catch (Exception e) {
            System.out.println(e);
            return response;
        }
        return response;
    }

    /**
     * post请求
     *
     * @param siriusBotClient     机器人对象
     * @param request 请求对象
     * @param data    请求数据
     * @param header Content-Type请求头
     * @return 返回响应对象
     */
    public static Response postRequest(SiriusBotClient siriusBotClient, Request request, RequestBody data, String header) {
        request = request.newBuilder()
                .addHeader("Authorization", "Bot " + siriusBotClient.getInfo().getBotId() + "." + siriusBotClient.getInfo().getToken())
                .addHeader("User-Agent", "SiriusBot")
                .addHeader("Content-Type", header)
                .post(data).build();
        Request build = request.newBuilder().post(data).build();
        Response response = null;
        try {
            response = siriusBotClient.getHttpClient().newCall(build).execute();
        } catch (Exception e) {
            System.out.println(e);
            return response;
        }
        return response;
    }

    /**
     * patch请求
     *
     * @param siriusBotClient     传入机器人对象
     * @param request 传入请求对象
     * @param data    请求数据
     * @return 返回响应对象
     */
    public static Response patchRequest(SiriusBotClient siriusBotClient, Request request, RequestBody data) {
        request = request.newBuilder()
                .addHeader("Authorization", "Bot " + siriusBotClient.getInfo().getBotId() + "." + siriusBotClient.getInfo().getToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "SiriusBot")
                .patch(data).build();
        Response response = null;
        try {
            response = siriusBotClient.getHttpClient().newCall(request).execute();
        } catch (Exception e) {
            return response;
        }
        return response;
    }

    /**
     * delete请求
     *
     * @param siriusBotClient     传入机器人对象
     * @param request 传入请求对象
     * @param data    无数据时请传null
     * @return 返回响应对象
     */
    public static Response deleteRequest(SiriusBotClient siriusBotClient, Request request, RequestBody data) {
        siriusBotClient = BotManager.getBotByBotId(siriusBotClient.getInfo().getBotId());
        if (data == null) {
            request = request.newBuilder()
                    .addHeader("Authorization", "Bot " + siriusBotClient.getInfo().getBotId() + "." + siriusBotClient.getInfo().getToken())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("User-Agent", "SiriusBot")
                    .delete().build();
        } else {
            request = request.newBuilder()
                    .addHeader("Authorization", "Bot " + siriusBotClient.getInfo().getBotId() + "." + siriusBotClient.getInfo().getToken())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("User-Agent", "SiriusBot")
                    .delete(data).build();
        }
        Response response = null;
        try {
            response = siriusBotClient.getHttpClient().newCall(request).execute();
        } catch (Exception e) {
            return response;
        }
        return response;
    }

    /**
     * put请求
     *
     * @param siriusBotClient     传入机器人对象
     * @param request 传入请求对象
     * @param data    要提交的数据
     * @return 返回响应对象
     */
    public static Response putRequest(SiriusBotClient siriusBotClient, Request request, RequestBody data) {
        siriusBotClient = BotManager.getBotByBotId(siriusBotClient.getInfo().getBotId());
        request = request.newBuilder()
                .addHeader("Authorization", "Bot " + siriusBotClient.getInfo().getBotId() + "." + siriusBotClient.getInfo().getToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "SiriusBot")
                .put(data).build();
        Response response = null;
        try {
            response = siriusBotClient.getHttpClient().newCall(request).execute();
        } catch (Exception e) {
            return response;
        }
        return response;
    }
}

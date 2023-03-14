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

    private static final Map<Integer, String> errorMsg = new ConcurrentHashMap<>();
    static {
        errorMsg.put(10001, "UnknownAccount 账号异常");
        errorMsg.put(10003, "UnknownChannel 子频道异常");
        errorMsg.put(10004, "UnknownGuild 频道异常");
        errorMsg.put(11281, "ErrorCheckAdminFailed 检查是否是管理员失败，系统错误，一般重试一次会好，最多只能重试一次");
        errorMsg.put(11282, "ErrorCheckAdminNotPass 检查是否是管理员未通过，该接口需要管理员权限，但是用户在添加机器人的时候并未授予该权限，属于逻辑错误，可以提示用户进行授权");
        errorMsg.put(11251, "ErrorWrongAppid 参数中的 appid 错误，开发者填的 token 错误，appid 无法识别");
        errorMsg.put(11252, "ErrorCheckAppPrivilegeFailed 检查应用权限失败，系统错误，一般重试一次会好，最多只能重试一次");
        errorMsg.put(11253, "ErrorCheckAppPrivilegeNotPass 检查应用权限不通过，该机器人应用未获得调用该接口的权限，需要向平台申请");
        errorMsg.put(11254, "ErrorInterfaceForbidden 应用接口被封禁，该机器人虽然获得了该接口权限，但是被封禁了。");
        errorMsg.put(11261, "ErrorWrongAppid 参数中缺少 appid，同 11251");
        errorMsg.put(11262, "ErrorCheckRobot 当前接口不支持使用机器人 Bot Token 调用");
        errorMsg.put(11263, "ErrorCheckGuildAuth 检查频道权限失败，系统错误，一般重试一次会好，最多只能重试一次");
        errorMsg.put(11264, "ErrorGuildAuthNotPass 检查小站权限未通过，管理员添加机器人的时候未授予该接口权限，属于逻辑错误，可提示用户进行授权，如果已经给予授权，请检查传递的 guild id 是否正确");
        errorMsg.put(11265, "ErrorRobotHasBaned 机器人已经被封禁");
        errorMsg.put(11241, "ErrorWrongToken 参数中缺少 token");
        errorMsg.put(11242, "ErrorCheckTokenFailed 校验 token 失败，系统错误，一般重试一次会好，最多只能重试一次");
        errorMsg.put(11243, "ErrorCheckTokenNotPass 校验 token 未通过，用户填充的 token 错误，需要开发者进行检查");
        errorMsg.put(11273, "ErrorCheckUserAuth 检查用户权限失败，当前接口不支持使用 Bearer Token 调用");
        errorMsg.put(11274, "ErrorUserAuthNotPass 检查用户权限未通过，用户 OAuth 授权时未给与该接口权限，可提示用户重新进行授权");
        errorMsg.put(11275, "ErrorWrongAppid 无 appid ，同 11251");
        errorMsg.put(11301, "ErrorGetHTTPHeader HTTP Header 无效");
        errorMsg.put(11302, "ErrorGetHeaderUIN HTTP Header 无效");
        errorMsg.put(11303, "ErrorGetNick 获取昵称失败");
        errorMsg.put(11304, "ErrorGetAvatar 获取头像失败");
        errorMsg.put(11305, "ErrorGetGuildID 获取频道 ID 失败");
        errorMsg.put(11306, "ErrorGetGuildInfo 获取频道信息失败");
        errorMsg.put(12001, "ReplaceIDFailed 替换 id 失败");
        errorMsg.put(12002, "RequestInvalid 请求体错误");
        errorMsg.put(12003, "ResponseInvalid 回包错误");
        errorMsg.put(20028, "ChannelHitWriteRateLimit 子频道消息触发限频");
        errorMsg.put(50006, "CannotSendEmptyMessage 消息为空");
        errorMsg.put(50035, "InvalidFormBody form-data 内容异常");
        errorMsg.put(50037, "带有markdown消息只支持 markdown 或者 keyboard 组合");
        errorMsg.put(50038, "非同频道同子频道");
        errorMsg.put(50039, "获取消息失败");
        errorMsg.put(50040, "消息模版类型错误");
        errorMsg.put(50041, "markdown 有空值");
        errorMsg.put(50042, "markdown 列表长达最大值");
        errorMsg.put(50043, "guild_id 转换失败");
        errorMsg.put(50045, "不能回复机器人自己产生的消息");
        errorMsg.put(50046, "非 at 机器人消息");
        errorMsg.put(50047, "非机器人产生的消息 或者 at 机器人消息");
        errorMsg.put(50048, "message id 不能为空");
        errorMsg.put(50049, "只能修改含有 keyboard 元素的消息");
        errorMsg.put(50050, "修改消息时，keyboard 元素不能为空");
        errorMsg.put(50051, "只能修改机器人自己发送的消息");
        errorMsg.put(50053, "修改消息错误");
        errorMsg.put(50054, "markdown 模版参数错误");
        errorMsg.put(50055, "无效的 markdown content");
        errorMsg.put(50056, "不允许发送 markdown content");
        errorMsg.put(50057, "markdown 参数只支持原生语法或者模版二选一");



        errorMsg.put(301000, "参数错误");
        errorMsg.put(301001, "查询频道信息错误");
        errorMsg.put(301002, "查询子频道权限错误");
        errorMsg.put(301003, "修改子频道权限错误");
        errorMsg.put(301004, "私密子频道关联的人数到达上限");
        errorMsg.put(301005, "调用 Rpc 服务失败");
        errorMsg.put(301006, "非群成员没有查询权限");
        errorMsg.put(301007, "参数超过数量限制");
        errorMsg.put(302000, "参数错误");
        errorMsg.put(302001, "查询频道信息错误");
        errorMsg.put(302002, "查询日程列表失败");
        errorMsg.put(302003, "查询日程失败");
        errorMsg.put(302004, "修改日程失败");
        errorMsg.put(302005, "删除日程失败");
        errorMsg.put(302006, "创建日程失败");
        errorMsg.put(302007, "获取创建者信息失败");
        errorMsg.put(302008, "子频道 ID 不能为空");
        errorMsg.put(302009, "频道系统错误，请联系客服");
        errorMsg.put(302010, "暂无修改日程权限");
        errorMsg.put(302011, "日程活动已被删除");
        errorMsg.put(302012, "每天只能创建 10 个日程，明天再来吧！");
        errorMsg.put(302013, "创建日程触发安全打击");
        errorMsg.put(302014, "日程持续时间超过 7 天，请重新选择");
        errorMsg.put(302015, "开始时间不能早于当前时间");
        errorMsg.put(302016, "结束时间不能早于开始时间");
        errorMsg.put(302017, "Schedule 对象为空");
        errorMsg.put(302018, "参数类型转换失败");
        errorMsg.put(302019, "调用下游失败，请联系客服");
        errorMsg.put(302020, "日程内容违规、账号违规");
        errorMsg.put(302021, "频道内当日新增活动达上限");
        errorMsg.put(302022, "不能绑定非当前频道的子频道");
        errorMsg.put(302023, "开始时跳转不可绑定日程子频道");
        errorMsg.put(302024, "绑定的子频道不存在");
        errorMsg.put(304003, "URL_NOT_ALLOWED url 未报备");
        errorMsg.put(304004, "ARK_NOT_ALLOWED 没有发 ark 消息权限");
        errorMsg.put(304005, "EMBED_LIMIT embed 长度超限");
        errorMsg.put(304006, "SERVER_CONFIG 后台配置错误");
        errorMsg.put(304007, "GET_GUILD 查询频道异常");
        errorMsg.put(304008, "GET_BOT 查询机器人异常");
        errorMsg.put(304009, "GET_CHENNAL 查询子频道异常");
        errorMsg.put(304010, "CHANGE_IMAGE_URL 图片转存错误");
        errorMsg.put(304011, "NO_TEMPLATE 模板不存在");
        errorMsg.put(304012, "GET_TEMPLATE 取模板错误");
        errorMsg.put(304014, "TEMPLATE_PRIVILEGE 没有模板权限");
        errorMsg.put(304016, "SEND_ERROR 发消息错误");
        errorMsg.put(304017, "UPLOAD_IMAGE 图片上传错误");
        errorMsg.put(304018, "SESSION_NOT_EXIST 机器人没连上 gateway");
        errorMsg.put(304019, "AT_EVERYONE_TIMES @全体成员 次数超限");
        errorMsg.put(304020, "FILE_SIZE 文件大小超限");
        errorMsg.put(304021, "GET_FILE 下载文件错误");
        errorMsg.put(304022, "PUSH_TIME 推送消息时间限制");
        errorMsg.put(304023, "PUSH_MSG_ASYNC_OK 推送消息异步调用成功, 等待人工审核");
        errorMsg.put(304024, "REPLY_MSG_ASYNC_OK 回复消息异步调用成功, 等待人工审核");
        errorMsg.put(304025, "BEAT 消息被打击");
        errorMsg.put(304026, "MSG_ID 回复的消息 id 错误");
        errorMsg.put(304027, "MSG_EXPIRE 回复的消息过期");
        errorMsg.put(304028, "MSG_PROTECT 非 At 当前用户的消息不允许回复");
        errorMsg.put(304029, "CORPUS_ERROR 调语料服务错误");
        errorMsg.put(304030, "CORPUS_NOT_MATCH 语料不匹配");
        errorMsg.put(304031, "私信已关闭");
        errorMsg.put(304032, "私信不存在");
        errorMsg.put(304033, "拉私信错误");
        errorMsg.put(304034, "不是私信成员");
        errorMsg.put(304035, "推送消息超过子频道数量限制");
        errorMsg.put(304036, "没有 markdown 模板的权限");
        errorMsg.put(304037, "没有发消息按钮组件的权限");
        errorMsg.put(304038, "消息按钮组件不存在");
        errorMsg.put(304039, "消息按钮组件解析错误");
        errorMsg.put(304040, "消息按钮组件消息内容错误");
        errorMsg.put(304044, "取消息设置错误");
        errorMsg.put(304045, "子频道主动消息数限频");
        errorMsg.put(304046, "不允许在此子频道发主动消息");
        errorMsg.put(304047, "主动消息推送超过限制的子频道数");
        errorMsg.put(304048, "不允许在此频道发主动消息");
        errorMsg.put(304049, "私信主动消息数限频");
        errorMsg.put(304050, "私信主动消息总量限频");
        errorMsg.put(304051, "消息设置引导请求构造错误");
        errorMsg.put(304052, "发消息设置引导超频");
        errorMsg.put(306001, "param invalid 撤回消息参数错误");
        errorMsg.put(306002, "msgid error 消息 id 错误");
        errorMsg.put(306003, "fail to get message 获取消息错误(可重试)");
        errorMsg.put(306004, "no permission to delete message 没有撤回此消息的权限");
        errorMsg.put(306005, "retract message error 消息撤回失败(可重试)");
        errorMsg.put(306006, "fail to get channel 获取子频道失败(可重试)");


        errorMsg.put(501001, "参数校验失败");
        errorMsg.put(501002, "创建子频道公告失败(可重试)");
        errorMsg.put(501003, "删除子频道公告失败(可重试)");
        errorMsg.put(501004, "获取频道信息失败(可重试)");
        errorMsg.put(501005, "MessageID 错误");
        errorMsg.put(501006, "创建频道全局公告失败(可重试)");
        errorMsg.put(501007, "删除频道全局公告失败(可重试)");
        errorMsg.put(501008, "MessageID 不存在");
        errorMsg.put(501009, "MessageID 解析失败");
        errorMsg.put(501010, "此条消息非子频道内消息");
        errorMsg.put(501011, "创建精华消息失败(可重试)");
        errorMsg.put(501012, "删除精华消息失败(可重试)");
        errorMsg.put(501013, "精华消息超过最大数量");
        errorMsg.put(501014, "安全打击");
        errorMsg.put(501015, "此消息不允许设置");
        errorMsg.put(501016, "频道公告子频道推荐超过最大数量");
        errorMsg.put(501017, "非频道主或管理员");
        errorMsg.put(501018, "推荐子频道 ID 无效");
        errorMsg.put(501019, "公告类型错误");
        errorMsg.put(501020, "创建推荐子频道类型频道公告失败");


        errorMsg.put(502001, "频道 id 无效");
        errorMsg.put(502002, "频道 id 为空");
        errorMsg.put(502003, "用户 id 无效");
        errorMsg.put(502004, "用户 id 为空");
        errorMsg.put(502005, "timestamp 不合法");
        errorMsg.put(502006, "timestamp 无效");
        errorMsg.put(502007, "参数转换错误");
        errorMsg.put(502008, "rpc 调用失败");
        errorMsg.put(502009, "安全打击");
        errorMsg.put(502010, "请求头错误");
        errorMsg.put(503001, "频道 id 无效");
        errorMsg.put(503002, "频道 id 为空");
        errorMsg.put(503003, "获取子频道信息失败");
        errorMsg.put(503004, "超出发布帖子的频次限制");
        errorMsg.put(503005, "帖子标题为空");
        errorMsg.put(503006, "帖子内容为空");
        errorMsg.put(503007, "帖子ID为空");
        errorMsg.put(503008, "获取X-Uin失败");
        errorMsg.put(503009, "帖子ID无效或不合法");
        errorMsg.put(503010, "通过Uin获取TinyID失败");
        errorMsg.put(503011, "帖子ID里面的时间戳无效或不合法");
        errorMsg.put(503012, "帖子不存在或已删除");
        errorMsg.put(503013, "服务器内部错误");
        errorMsg.put(503014, "帖子JSON内容解析失败");
        errorMsg.put(503015, "帖子内容转换失败");
        errorMsg.put(503016, "链接数量超过限制");
        errorMsg.put(503017, "字数超过限制");
        errorMsg.put(503018, "图片数量超过限制");
        errorMsg.put(503019, "视频数量超过限制");
        errorMsg.put(503020, "标题长度超过限制");


        errorMsg.put(504001, "请求参数无效错误");
        errorMsg.put(504002, "获取 HTTP 头失败");
        errorMsg.put(504003, "获取 BOT UIN 错误");
        errorMsg.put(504004, "获取消息频率设置信息错误");
        errorMsg.put(505000, "获取子频道信息失败");
        errorMsg.put(505001, "获取机器人信息失败");
        errorMsg.put(505002, "非语音频道");
        errorMsg.put(505003, "非音频机器人");
        errorMsg.put(505004, "上麦失败");
        errorMsg.put(505005, "下麦失败");



        errorMsg.put(610001, "获取频道 ID 失败");
        errorMsg.put(610002, "获取 HTTP 头失败");
        errorMsg.put(610003, "获取机器人号码失败");
        errorMsg.put(610004, "获取机器人角色失败");
        errorMsg.put(610005, "获取机器人角色内部错误");
        errorMsg.put(610006, "拉取机器人权限列表失败");
        errorMsg.put(610007, "机器人不在频道内");
        errorMsg.put(610008, "无效参数");
        errorMsg.put(610009, "获取 API 接口详情失败");
        errorMsg.put(610010, "API 接口已授权");
        errorMsg.put(610011, "获取机器人信息失败");
        errorMsg.put(610012, "限频失败");
        errorMsg.put(610013, "已限频");
        errorMsg.put(610014, "api 授权链接发送失败");



        errorMsg.put(620001, "表情表态无效参数");
        errorMsg.put(620002, "已经达到表情反应的类型数量上限");
        errorMsg.put(620003, "已经设置过该表情表态");
        errorMsg.put(620004, "没有设置过该表情表态");
        errorMsg.put(620005, "没有权限设置表情表态");
        errorMsg.put(620006, "操作限频");
        errorMsg.put(620007, "表情表态操作失败，请重试");
        errorMsg.put(620008, "无效表情");


        errorMsg.put(630001, "互动回调数据更新无效参数");
        errorMsg.put(630002, "互动回调数据更新获取AppID失败");
        errorMsg.put(630003, "互动回调数据AppID不匹配");
        errorMsg.put(630004, "互动回调数据更新内部存储错误");
        errorMsg.put(630005, "互动回调数据更新内部存储读取错误");
        errorMsg.put(630006, "互动回调数据更新读取请求AppID失败");
        errorMsg.put(630007, "互动回调数据太大");


        errorMsg.put(1100100, "安全打击：消息被限频");
        errorMsg.put(1100101, "安全打击：内容涉及敏感，请返回修改");
        errorMsg.put(1100102, "安全打击：抱歉，暂未获得新功能体验资格");
        errorMsg.put(1100103, "安全打击");
        errorMsg.put(1100104, "安全打击：该群已失效或当前群已不存在");
        errorMsg.put(1100300, "系统内部错误");
        errorMsg.put(1100301, "调用方不是群成员");
        errorMsg.put(1100302, "获取指定频道名称失败");
        errorMsg.put(1100303, "主页频道非管理员不允许发消息");
        errorMsg.put(1100304, "@次数鉴权失败");
        errorMsg.put(1100305, "TinyId 转换 Uin 失败");
        errorMsg.put(1100306, "非私有频道成员");
        errorMsg.put(1100307, "非白名单应用子频道");
        errorMsg.put(1100308, "触发频道内限频");
        errorMsg.put(1100499, "其他错误");


        errorMsg.put(3300006, "安全打击");

    }

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
            switch (response.code()) {
                case 200 -> {
                    return Objects.requireNonNull(response.body()).string();
                }
                default -> {
                    throw new MsgException(500, String.format("httpClient请求错误代码:%d，body:%s", response.code(), response.body().string()));
                }
            }
        } catch (MsgException e) {
            log.error("httpClient请求错误", e);
            throw e;
        } catch (Exception e) {
            log.error("httpClient请求错误", e);
            throw new MsgException(500, "httpClient请求错误!");
        }
    }

    private void disposeResponse(String response, String header) throws IOException {
        JSONObject json = JSONObject.parseObject(response);
        if (json.getInteger("code") != null){
            String msg = errorMsg.get(json.getInteger("code"));
            if (msg != null){
                throw new MsgException(json.getInteger("code"), msg, header);
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
        log.info("HTTP请求 -> " + request);
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
                    this.disposeResponse(body, response.header("X-Tps-trace-ID"));
                    return new BotResponse()
                            .setCode(500)
                            .setBody(body)
                            .setTraceId(response.header("X-Tps-trace-ID"));
                }
                default -> {
                    String body = Objects.requireNonNull(response.body()).string();
                    this.disposeResponse(body, response.header("X-Tps-trace-ID"));
                    throw new MsgException(response.code(), String.format("httpClient请求错误代码:%d，body:%s,X-Tps-trace-ID：%s", response.code(), body, response.header("X-Tps-trace-ID")));
                }
            }
        } catch (MsgException e) {
            log.error("httpClient请求错误", e);
            throw e;
        } catch (Exception e) {
            log.error("httpClient请求错误", e);
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

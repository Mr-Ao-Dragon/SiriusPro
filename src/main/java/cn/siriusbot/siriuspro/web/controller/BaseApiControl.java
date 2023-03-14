package cn.siriusbot.siriuspro.web.controller;

import cn.siriusbot.siriuspro.admin.entity.Admin;
import cn.siriusbot.siriuspro.admin.service.BotService;
import cn.siriusbot.siriuspro.admin.service.IntentService;
import cn.siriusbot.siriuspro.admin.service.ServerConfigService;
import cn.siriusbot.siriuspro.bot.BotApi;
import cn.siriusbot.siriuspro.bot.SiriusBotApiExternal;
import cn.siriusbot.siriuspro.bot.api.*;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.application.SiriusApplicationInfo;
import cn.siriusbot.siriuspro.bot.plugin.PlugInClient;
import cn.siriusbot.siriuspro.bot.plugin.PlugInFactory;
import cn.siriusbot.siriuspro.config.Constant;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import cn.siriusbot.siriuspro.config.bean.StatisticsPool;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.web.R.R;
import cn.siriusbot.siriuspro.web.pojo.BaseApiBody;
import cn.siriusbot.siriuspro.web.pojo.BotHttpRequest;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
@Log4j2
public class BaseApiControl {

    @Autowired
    BotApi botApi;

    @Data
    @Accessors(chain = true)
    private static class MethodInfo {
        Method method;  // 方法对象
        String name;  // 方法名
        List<ParamInfo> params; // 参数列表
    }

    @Data
    @Accessors(chain = true)
    private static class ParamInfo {
        String name;    // 参数名
        Type type;
        boolean nonNull = false;    // 不能为空
    }

    private final Map<String, Map<String, MethodInfo>> apiMethodInfo = new ConcurrentHashMap<>();
    private final Map<String, Object> apiObject = new ConcurrentHashMap<>();

    private String getClassName(String s) {
        return s.substring(s.lastIndexOf(".") + 1);
    }

    private <T> void putApi(Class<T> clazz, T object) {
        Method[] methods = clazz.getMethods();
        Map<String, MethodInfo> methodInfos = new HashMap<>();
        for (Method method : methods) {
            MethodInfo methodInfo = new MethodInfo();
            methodInfo
                    .setName(method.getName())
                    .setMethod(method)
                    .setParams(new ArrayList<>());
            // 构建参数类型
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                Type genericParameterType = parameter.getParameterizedType();
                ParamInfo info = new ParamInfo()
                        .setType(genericParameterType)
                        .setName(parameter.getName());
                methodInfo.getParams().add(info);
            }
            methodInfos.put(method.getName(), methodInfo);
        }
        apiMethodInfo.put(getClassName(clazz.getName()), methodInfos);
        apiObject.put(getClassName(clazz.getName()), object);
    }

    // ============= 代理
    // <包名, api>
    @Autowired
    BotPool botPool;
    @Autowired
    ServerConfigService serverConfigService;
    @Autowired
    StatisticsPool statisticsPool;
    @Autowired
    BotService botService;
    @Autowired
    IntentService intentService;
    private final Map<String, BotApi> proxyApi = new ConcurrentHashMap<>();

    private Object getProxyByName(SiriusApplicationInfo info, String clazzName) {
        if (!proxyApi.containsKey(info.getPackageName())) {
            // 创建代理对象
            BotApi siriusBotApiExternal = new SiriusBotApiExternal(info, botApi, botPool, serverConfigService, statisticsPool, botService, intentService);
            proxyApi.put(info.getPackageName(), siriusBotApiExternal);
        }
        BotApi botApi = proxyApi.get(info.getPackageName());
        Method[] methods = botApi.getClass().getMethods();
        for (Method method : methods) {
            if (getClassName(method.getReturnType().getName()).equals(clazzName)) {
                try {
                    return method.invoke(botApi);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new MsgException(500, "请求错误，调用代理对象失败!");
                }
            }
        }
        throw new MsgException(500, "请求错误，找不到对应调用类!");
    }
    // ==================

    /**
     * 初始化配置
     */
    @PostConstruct
    private void initConfig() {
        // 配置API类
        putApi(AnnouncesApi.class, botApi.announcesApi());
        putApi(ApiPermissionApi.class, botApi.apiPermissionApi());
        putApi(AudioApi.class, botApi.audioApi());
        putApi(ChannelApi.class, botApi.channelApi());
        putApi(ChannelPermissionsApi.class, botApi.channelPermissionsApi());
        putApi(DMS_Api.class, botApi.dmsApi());
        putApi(ForumApi.class, botApi.forumApi());
        putApi(GuildApi.class, botApi.guildApi());
        putApi(MemberApi.class, botApi.memberApi());
        putApi(MessageApi.class, botApi.messageApi());
        putApi(MessageReactionApi.class, botApi.messageReactionApi());
        putApi(MessageSettingApi.class, botApi.messageSettingApi());
        putApi(NoSpeakApi.class, botApi.noSpeakApi());
        putApi(PinsMessageApi.class, botApi.pinsMessageApi());
        putApi(RoleApi.class, botApi.roleApi());
        putApi(ScheduleApi.class, botApi.scheduleApi());
        putApi(UserApi.class, botApi.userApi());

    }


    @Autowired
    PlugInFactory plugInFactory;

    @SneakyThrows
    @PostMapping("control/{api}/{method}")
    public R controlUrl(@RequestBody String bodyStr) {
        return control(bodyStr);
    }

    @SneakyThrows
    @PostMapping("control")
    public R control(@RequestBody String bodyStr) {
        bodyStr = bodyStr.replaceAll("\\\\r\\\\n", "\\\\n");
        BaseApiBody body = JSONObject.parseObject(bodyStr, BaseApiBody.class);
        if (ObjectUtils.isEmpty(body.getSession())) {
            throw new MsgException(500, "构建请求错误，session为空!");
        }
        if (ObjectUtils.isEmpty(body.getApi())) {
            throw new MsgException(500, "构建请求错误，api为空!");
        }
        if (ObjectUtils.isEmpty(body.getMethod())) {
            throw new MsgException(500, "构建请求错误，method为空!");
        }
        if (!apiMethodInfo.containsKey(body.getApi())) {
            throw new MsgException(500, "构建请求错误，api信息错误!");
        }
        if (!apiMethodInfo.get(body.getApi()).containsKey(body.getMethod())) {
            throw new MsgException(500, "构建请求错误，method信息错误!");
        }
        PlugInClient client = plugInFactory.getPlugInClientBySessionId(body.getSession());
        if (client == null) {
            throw new MsgException(500, "构建请求错误，session会话过期或不存在!");
        }
        String logMsg = "";
        //Object o = apiObject.get(body.getApi());
        Object o = this.getProxyByName(client.getInfo(), body.getApi());
        MethodInfo methodInfo = apiMethodInfo.get(body.getApi()).get(body.getMethod());
        // 构建调用参数
        Method method = methodInfo.getMethod();
        Object[] objects = new Object[methodInfo.getParams().size()];
        for (int i = 0; i < methodInfo.getParams().size(); i++) {
            ParamInfo param = methodInfo.getParams().get(i);
            objects[i] = body.getParam().getObject(param.getName(), param.getType());
        }
        logMsg += String.format("(%s)调用API(%s)方法(%s)\n参数 -> %s\n",
                client.getPackageName(),
                body.getApi(),
                method.getName(),
                Arrays.toString(objects)
        );
        try {
            R r = new R();
            r.setCode(0);
            Object invoke = method.invoke(o, objects);
            if (invoke instanceof Tuple tuple) {
                r.setData(tuple.getFirst());
                r.setExtra(tuple.getSecond());
            } else {
                r.setData(invoke);
            }
            logMsg += "成功返回 -> " + r;
            log.info(logMsg);
            return r;
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            Throwable temp = e;
            while (temp.getCause() != null) {
                temp = temp.getCause();
            }
            if (temp instanceof MsgException msg) {
                logMsg += "异常 -> " + msg.getR();
            } else {
                String pattern = "Argument for @NotNull parameter '(\\w+)'";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(temp.getMessage());
                if (m.find()) {
                    logMsg += "异常 -> " + String.format("参数%s不能为空!", m.group(1));
                    log.error(logMsg, e);
                    throw new MsgException(500, String.format("参数%s不能为空!", m.group(1)));
                }
                logMsg += "异常 -> " + temp;
            }
            log.error(logMsg, e);
            throw temp;
        }
    }


    @SneakyThrows
    @PostMapping("plugin/{package}/{name}")
    public R plugin(
            @PathVariable("package") String packageName,
            @PathVariable("name") String name,
            @RequestBody JSONObject body,
            HttpServletRequest request,
            HttpSession session
    ) {
        Admin admin = (Admin) session.getAttribute(Constant.SESSION_ADMIN);
        String ipAddr = getIpAddr(request);
        InetAddress localHost = InetAddress.getLocalHost();
        String hostAddress = localHost.getHostAddress();
        BotHttpRequest botHttpRequest = new BotHttpRequest()
                .setName(name)
                .setSourceIp(ipAddr)
                .setLocalIp(hostAddress)
                .setBody(body.toJSONString());
        if (admin == null) {
            botHttpRequest.setPower(-1);
        } else {
            botHttpRequest.setPower(admin.getPower());
        }
        R r = plugInFactory.putWebEvent(packageName, botHttpRequest);
        if (r == null) {
            throw new MsgException(404, "找不到web映射入口!");
        }
        return r;
    }

    private String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet;
                    try {
                        inet = InetAddress.getLocalHost();
                        ipAddress = inet.getHostAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        // ipAddress = this.getRequest().getRemoteAddr();
        return ipAddress;
    }


}

package cn.siriusbot.siriuspro.webapi.controller;

import cn.siriusbot.siriuspro.bot.BotApi;
import cn.siriusbot.siriuspro.bot.api.*;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.error.MsgException;
import cn.siriusbot.siriuspro.webapi.R.R;
import cn.siriusbot.siriuspro.webapi.pojo.BaseApiBody;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api")
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

    @SneakyThrows
    @PostMapping("control")
    public R control(@RequestBody BaseApiBody body){
        System.out.println(body);
        if (ObjectUtils.isEmpty(body.getApi())){
            throw new MsgException(500, "构建请求错误，api为空!");
        }
        if (ObjectUtils.isEmpty(body.getMethod())){
            throw new MsgException(500, "构建请求错误，method为空!");
        }
        if (!apiMethodInfo.containsKey(body.getApi())){
            throw new MsgException(500, "构建请求错误，api信息错误!");
        }
        if (!apiMethodInfo.get(body.getApi()).containsKey(body.getMethod())){
            throw new MsgException(500, "构建请求错误，method信息错误!");
        }
        Object o = apiObject.get(body.getApi());
        MethodInfo methodInfo = apiMethodInfo.get(body.getApi()).get(body.getMethod());
        // 构建调用参数
        Method method = methodInfo.getMethod();
        Object[] objects = new Object[methodInfo.getParams().size()];
        for (int i = 0; i < methodInfo.getParams().size(); i++) {
            ParamInfo param = methodInfo.getParams().get(i);
            objects[i] = body.getParam().getObject(param.getName(), param.getType());
        }
        try {
            R r = new R();
            r.setCode(0);
            Object invoke = method.invoke(o, objects);
            if (invoke instanceof Tuple tuple){
                r.setData(tuple.getFirst());
            } else {
                r.setData(invoke);
            }
            return r;
        } catch (InvocationTargetException | IllegalAccessException e) {
             throw e.getCause();
        }
    }


}

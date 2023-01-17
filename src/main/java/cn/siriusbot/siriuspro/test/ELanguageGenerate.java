package cn.siriusbot.siriuspro.test;

import cn.siriusbot.siriuspro.bot.annotation.EName;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.*;
import cn.siriusbot.siriuspro.bot.api.pojo.*;
import cn.siriusbot.siriuspro.bot.api.pojo.announces.Announces;
import cn.siriusbot.siriuspro.bot.api.pojo.announces.RecommendChannel;
import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.APIPermission;
import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.ApiPermissionDemand;
import cn.siriusbot.siriuspro.bot.api.pojo.apipermission.ApiPermissionDemandIdentify;
import cn.siriusbot.siriuspro.bot.api.pojo.audio.AudioAction;
import cn.siriusbot.siriuspro.bot.api.pojo.audio.AudioControl;
import cn.siriusbot.siriuspro.bot.api.pojo.emoji.Emoji;
import cn.siriusbot.siriuspro.bot.api.pojo.emoji.MessageReaction;
import cn.siriusbot.siriuspro.bot.api.pojo.emoji.ReactionReply;
import cn.siriusbot.siriuspro.bot.api.pojo.emoji.ReactionTarget;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.post.Post;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.post.PostInfo;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.reply.Reply;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.reply.ReplyInfo;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.responseObj.CreateThread;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.responseObj.ThreadList;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.richObject.*;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.thread.ForumThread;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.thread.ForumThreadInfo;
import cn.siriusbot.siriuspro.bot.api.pojo.member.Member;
import cn.siriusbot.siriuspro.bot.api.pojo.member.MemberQueryLimit;
import cn.siriusbot.siriuspro.bot.api.pojo.message.*;
import cn.siriusbot.siriuspro.bot.api.pojo.message.ark.MessageArk;
import cn.siriusbot.siriuspro.bot.api.pojo.message.ark.MessageArkKv;
import cn.siriusbot.siriuspro.bot.api.pojo.message.ark.MessageArkObj;
import cn.siriusbot.siriuspro.bot.api.pojo.message.ark.MessageArkObjKv;
import cn.siriusbot.siriuspro.bot.api.pojo.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.bot.api.pojo.message.embed.MessageEmbedField;
import cn.siriusbot.siriuspro.bot.api.pojo.message.embed.MessageEmbedThumbnail;
import cn.siriusbot.siriuspro.bot.api.pojo.message.keyboard.*;
import cn.siriusbot.siriuspro.bot.api.pojo.message.requestPack.RequestCustomKeyboard;
import cn.siriusbot.siriuspro.bot.api.pojo.role.GuildRoleList;
import cn.siriusbot.siriuspro.bot.api.pojo.role.NewRole;
import cn.siriusbot.siriuspro.bot.api.pojo.role.Role;
import cn.siriusbot.siriuspro.bot.pojo.message.AudioLiveChannelEvent.AudioLiveChannelMemberEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.AudioLiveChannelEvent.AudioLiveChannelMemberEventDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.AudioMessageEvent.AudioMessageEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.AudioMessageEvent.AudioMessageEventDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.ChannelEvent.ChannelDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.ChannelEvent.ChannelEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.DirectMessageEvent.DirectMessageDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.DirectMessageEvent.DirectMessageEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.ForumEvent.ForumEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.ForumEvent.ForumEventDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildEvent.GuildDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildEvent.GuildEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildMemberEvent.GuildMemberDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildMemberEvent.GuildMemberEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildMemberEvent.create.GuildMemberCreateDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.GuildMemberEvent.create.GuildMemberCreateEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.InterActionMessageEvent.InterActionEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.InterActionMessageEvent.InterActionEventDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.InterActionMessageEvent.InterActionType;
import cn.siriusbot.siriuspro.bot.pojo.message.InterActionMessageEvent.Resolved;
import cn.siriusbot.siriuspro.bot.pojo.message.MessageReactionEvent.ReactionEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.Op_User;
import cn.siriusbot.siriuspro.bot.pojo.message.OpenForumEvent.OpenForumEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.PrivateDomainEvent.PrivateDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.PrivateDomainEvent.PrivateDomainMessageInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.PrivateDomainEvent.PrivateMessageDeleteDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.PrivateDomainEvent.PrivateMessageDeleteEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent.PublicMessageDeleteDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent.PublicMessageDeleteEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent.PublicMessageEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent.PublicMessageEventDObject;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.lang.reflect.*;
import java.util.*;

/**
 * 易语言代码通用解析器生成
 */
@Component
@Log4j2
public class ELanguageGenerate {

    private final Map<String, List<Info>> map = new HashMap<>();
    private final Map<String, List<MethodInfo>> apiMap = new HashMap<>();

    private String getName(String s) {
        return s.substring(s.lastIndexOf(".") + 1);
    }

    private String getEType(String s) {

        switch (s) {
            case "String" -> {
                return "文本型";
            }
            case "Integer", "int" -> {
                return "整数型";
            }
            case "Boolean", "boolean" -> {
                return "逻辑型";
            }
            case "double", "Double" -> {
                return "双精度小数型";
            }
            case "long", "Long" -> {
                return "长整数型";
            }
            default -> {
                return s;
            }
        }
    }

    private void put(Class<?> clazz) throws Exception {
        List<Info> list = new ArrayList<>();
        // 首先得到pojo所定义的字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field curField : fields) {
            // 设置字段可访问（必须，否则报错）
            boolean isList = false;
            curField.setAccessible(true);
            Class<?> curFieldType = curField.getType();
            String name = getName(curField.getName());
            String type = getName(curFieldType.getName());
            // 集合List元素
            if (curFieldType.equals(List.class)) {
                // 当前集合的泛型类型
                Type genericType = curField.getGenericType();
                if (genericType instanceof ParameterizedType pt) {
                    // 得到泛型里的class类型对象
                    Class<?> actualTypeArgument = (Class<?>) pt.getActualTypeArguments()[0];
                    isList = true;
                    type = getName(actualTypeArgument.getName());
                }
            }
            Info info = new Info()
                    .setName(name)
                    .setSrcType(type)
                    .setType(getEType(type))
                    .setList(isList);
            list.add(info);
        }
        exec(clazz, list);
        map.put(getName(clazz.getName()), list);
    }

    /**
     * 生成易语言数据类型代码
     */
    private String generateTypeInfos() {
        StringBuilder sb = new StringBuilder();
        sb.append(".版本 2").append('\n').append('\n');
        for (String key : map.keySet()) {
            sb.append(".数据类型 ").append(key).append('\n');
            for (Info info : map.get(key)) {
                sb.append("    .成员 ").append(info.getName()).append(',').append(info.getType());
                if (info.list) {
                    sb.append(", , \"1\" , ").append(info.getJavaDoc()).append('\n');
                } else {
                    sb.append(", , , ").append(info.getJavaDoc()).append('\n');
                }

            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 生成解析方法
     */
    private String generateAnalyticMethod() {
        StringBuilder sb = new StringBuilder();
        String methodName = "通用Json解析";
        String transition = "强制转换";
        String paramMethod = "method";
        String paramSource = "source";
        // 添加代码头
        sb.append("""
                .版本 2
                .支持库 spec

                """);
        // 添加所有待解析局部变量
        for (String key : map.keySet()) {
            sb
                    .append(".局部变量 ")
                    .append(key.substring(0, 1).toLowerCase(Locale.ROOT))
                    .append(key.substring(1))
                    .append(',')
                    .append(key)
                    .append('\n');
        }
        // 定义需要用到的局部变量
        sb.append(".局部变量 json, zyJsonDocument").append('\n');
        sb.append(".局部变量 i, 整数型").append('\n');
        sb.append(".局部变量 length, 整数型").append('\n');
        sb.append(".局部变量 item, zyJsonValue").append('\n');
        sb.append('\n');
        // 共有方法
        sb.append(String.format("json.解析 (%s, , , )", paramSource)).append('\n');
        for (String key : map.keySet()) {
            sb.append(String.format(".如果真 (%s ＝ “%s”)", paramMethod, key)).append('\n');
            // ==== 如果事件 ====
            String o = key.substring(0, 1).toLowerCase(Locale.ROOT) + key.substring(1);
            List<Info> infos = map.get(key);
            for (Info info : infos) {
                if (info.isList()) {
                    // 数组事件
                    sb.append("    ").append(String.format("item ＝ json.取属性 (, “%s”)", info.getName())).append('\n');
                    sb.append("    ").append("length ＝ item.取成员数 ()").append('\n');
                    sb.append("    ").append(String.format("重定义数组 (%s.%s, 假, length)", o, info.getName())).append('\n');
                    String eType = info.getType();
                    if (eType.equals(info.getSrcType())) {
                        // 对象类型成员
                        sb.append("    ").append("i ＝ 0").append('\n');
                        sb.append("    ").append(".判断循环首 (i < length)").append('\n');
                        sb.append("    ").append("    ").append("i ＝ i + 1").append('\n');
                        sb.append("    ").append("    ").append(String.format(
                                "%s(%s.%s[i], %s(“%s“, item.取成员 (, i - 1).到文本()))",
                                transition,
                                o,
                                info.getName(),
                                methodName,
                                info.getType()
                        )).append('\n');
                        sb.append("    ").append(".判断循环尾 ()").append('\n');
                    } else {
                        // 普通类型成员
                        sb.append("    ").append(".计次循环首 (length, i)").append('\n');
                        String jsonType;
                        switch (eType) {
                            case "文本型" -> jsonType = "取文本";
                            case "整数型" -> jsonType = "取整数";
                            case "逻辑型" -> jsonType = "取逻辑";
                            case "双精度小数型" -> jsonType = "取双精度";
                            case "长整数型" -> jsonType = "取长整数";
                            default -> jsonType = "取文本";
                        }
                        sb.append("    ").append(String.format("    %s.%s[i] ＝ item.取成员 (, i - 1).%s ()", o, info.getName(), jsonType)).append('\n');
                        sb.append("    ").append(".计次循环尾 ()").append('\n');
                    }

                } else {
                    // 单属性事件
                    String eType = info.getType();
                    if (eType.equals(info.getSrcType())) {
                        // 解析对象类型
                        sb.append("    ").append(String.format(
                                "%s(%s.%s, %s(“%s“,json.取属性 (, “%s”).到文本()))",
                                transition,
                                o,
                                info.getName(),
                                methodName,
                                info.getType(),
                                info.getName()
                        )).append('\n');

                    } else {
                        // 解析基础类型
                        String jsonType;
                        switch (eType) {
                            case "文本型" -> jsonType = "取文本";
                            case "整数型" -> jsonType = "取整数";
                            case "逻辑型" -> jsonType = "取逻辑";
                            case "双精度小数型" -> jsonType = "取双精度";
                            case "长整数型" -> jsonType = "取长整数";
                            default -> jsonType = "取文本";
                        }
                        sb.append("    ").append(String.format(
                                "%s.%s ＝ json.%s (“%s”)",
                                o,
                                info.getName(),
                                jsonType,
                                info.getName()
                        )).append('\n');
                    }
                }
            }
            sb.append("    ").append(String.format("返回 (%s)", o)).append('\n');
            // ==== 如果事件 ====
            sb.append(".如果真结束").append('\n');
        }
        sb.append("返回 (“”)").append('\n');
        return sb.toString();
    }

    private String eTypeToJsonName(String eType) {
        String jsonType;
        switch (eType) {
            case "整数型" -> jsonType = "取整数";
            case "逻辑型" -> jsonType = "取逻辑";
            case "双精度小数型" -> jsonType = "取双精度";
            case "长整数型" -> jsonType = "取长整数";
            default -> jsonType = "取文本";
        }
        return jsonType;
    }

    private String jsonNameToeType(String eType) {
        String jsonType;
        switch (eType) {
            case "整数型" -> jsonType = "置整数";
            case "逻辑型" -> jsonType = "置逻辑";
            case "双精度小数型" -> jsonType = "置双精度";
            case "长整数型" -> jsonType = "置长整数";
            default -> jsonType = "置文本";
        }
        return jsonType;
    }

    /**
     * 生成解析对象
     *
     * @return
     */
    private String generateAnalyticClass() {
        StringBuilder sb = new StringBuilder();
        String paramMethodHead = "parse";
        String paramSource = "source";
        // 添加代码头
        sb.append("""
                .版本 2

                """);
        for (String key : map.keySet()) {
            String obj = key.substring(0, 1).toLowerCase(Locale.ROOT) + key.substring(1);
            // 生成方法
            sb.append(String.format(".子程序 %s%s, %s, 公开", paramMethodHead, key, key)).append('\n');
            sb.append(String.format(".参数 %s, 文本型", paramSource)).append('\n');
            // 生成局部变量
            sb.append(".局部变量 i, 整数型").append('\n');
            sb.append(".局部变量 length, 整数型").append('\n');
            sb.append(".局部变量 json, zyJsonDocument").append('\n');
            sb.append(".局部变量 item, zyJsonValue").append('\n');
            sb.append(String.format(".局部变量 %s, %s", obj, key)).append('\n');
            sb.append('\n');
            // 生成方法
            sb.append(String.format("json.解析 (%s, , , )", paramSource)).append('\n');
            List<Info> infos = map.get(key);
            for (Info info : infos) {
                if (info.isList()) {
                    // 数组解析
                    sb.append(String.format("item ＝ json.取属性 (, “%s”)", info.getName())).append('\n');
                    sb.append("length ＝ item.取成员数 ()").append('\n');
                    sb.append(String.format("重定义数组 (%s.%s, 假, length)", obj, info.getName())).append('\n');
                    sb.append(".计次循环首 (length, i)").append('\n');
                    if (info.getType().equals(info.getSrcType())) {
                        // 对象类型
                        sb.append("    ").append(String.format("%s.%s[i] ＝ %s%s(item.取成员 (, i - 1).到文本 ())",
                                        obj,
                                        info.getName(),
                                        paramMethodHead,
                                        info.getType()
                                )
                        ).append('\n');
                    } else {
                        // 基础类型
                        sb.append("    ").append(String.format("%s.%s[i] ＝ item.取成员 (, i - 1).%s ()", obj, info.getName(), eTypeToJsonName(info.getType()))).append('\n');
                    }
                    sb.append(".计次循环尾 ()").append('\n');
                } else {
                    // 单独对象解析
                    if (info.getType().equals(info.getSrcType())) {
                        // 对象类型
                        sb.append(String.format(
                                        "%s.%s ＝ %s%s(json.取属性 (, “%s”).到文本())",
                                        obj,
                                        info.getName(),
                                        paramMethodHead,
                                        info.getType(),
                                        info.getName()
                                )
                        ).append('\n');
                    } else {
                        // 基础类型
                        sb.append(String.format(
                                        "%s.%s ＝ json.%s (“%s”)",
                                        obj,
                                        info.getName(),
                                        eTypeToJsonName(info.getType()),
                                        info.getName()
                                )
                        ).append('\n');
                    }
                }
            }
            // 生成返回
            sb.append(String.format("返回 (%s)", obj)).append('\n');
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * 生成构建对象
     *
     * @return
     */
    private String generateBuildObject() {
        StringBuilder sb = new StringBuilder();
        String paramMethodHead = "build";
        String paramSource = "source";
        // 添加代码头
        sb.append("""
                .版本 2

                """);
        for (String key : map.keySet()) {
            // 生成方法
            sb.append(String.format(".子程序 %s%s, 文本型, 公开", paramMethodHead, key)).append('\n');
            sb.append(String.format(".参数 %s, %s", paramSource, key)).append('\n');
            // 生成局部变量
            sb.append(".局部变量 i, 整数型").append('\n');
            sb.append(".局部变量 length, 整数型").append('\n');
            sb.append(".局部变量 json, zyJsonDocument").append('\n');
            sb.append('\n');
            // 生成方法
            sb.append("json.创建 ()").append('\n');
            List<Info> infos = map.get(key);
            for (Info info : infos) {
                if (info.isList()) {
                    // 数组构建
                    sb.append(String.format("length ＝ 取数组成员数 (%s.%s)", paramSource, info.getName())).append('\n');
                    sb.append(".计次循环首 (length, i)").append('\n');
                    if (info.getType().equals(info.getSrcType())) {
                        // 对象类型
                        // json.置JSON (“id [i - 1]”, build (source.id [i]))
                        sb.append(String.format("json.置JSON (“%s[” ＋ 到文本 (i － 1) ＋ “]”, %s%s (%s.%s [i]))",
                                        info.getName(),
                                        paramMethodHead,
                                        info.getType(),
                                        paramSource,
                                        info.getName()
                                )
                        ).append('\n');
                    } else {
                        // 普通类型
                        // json.置文本 (“id[0]”, source.id[1])
                        sb.append("    ").append(String.format("json.%s (“%s[” ＋ 到文本 (i － 1) ＋ “]”, %s.%s [i])",
                                        jsonNameToeType(info.getType()),
                                        info.getName(),
                                        paramSource,
                                        info.getName()
                                )
                        ).append('\n');
                    }
                    sb.append(".计次循环尾 ()").append('\n');
                } else {
                    // 普通构建
                    if (info.getType().equals(info.getSrcType())) {
                        // 对象类型
                        // json.置JSON (“id”, build (source.id))
                        sb.append(String.format("json.置JSON (“%s”, %s%s (%s.%s))",
                                        info.getName(),
                                        paramMethodHead,
                                        info.getType(),
                                        paramSource,
                                        info.getName()
                                )
                        ).append('\n');
                    } else {
                        // 基础类型
                        // json.置文本 (“id”, source.id)
                        sb.append(String.format("json.%s (“%s”, %s.%s)",
                                        jsonNameToeType(info.getType()),
                                        info.getName(),
                                        paramSource,
                                        info.getName()
                                )
                        ).append('\n');
                    }
                }
            }

            sb.append("返回 (json.到文本 ())").append('\n');
            sb.append('\n');
        }
        return sb.toString();
    }


    /**
     * 生成API方法
     *
     * @return
     */
    private String generateAPIMethod() {
        StringBuilder sb = new StringBuilder();
        String paramSource = "source";
        String url = "global_url_head";
        String build = "build_util.build";
        String jsonApi = "api";
        String jsonMethod = "method";
        String jsonParam = "param";
        for (String apiName : apiMap.keySet()) {
            // 构建代码头
            sb.append(String.format("""
                    .版本 2
                                        
                    .程序集 %s
                    .程序集变量 %s, 文本型
                                        
                    .子程序 _初始化, , , 当基于本类的对象被创建后，此方法会被自动调用
                                        
                    .子程序 _销毁, , , 当基于本类的对象被销毁前，此方法会被自动调用
                                        
                    """, apiName, url));

            List<MethodInfo> methodInfos = apiMap.get(apiName);
            for (MethodInfo methodInfo : methodInfos) {
                // 构建方法
                sb.append(String.format(".子程序 %s, 整数型, 公开", methodInfo.getFormatName())).append('\n');
                sb.append(String.format(".参数 %s, %s, 参考%s, 返回结果",
                        methodInfo.getType().getSrcType(),
                        methodInfo.getType().getType(),
                        methodInfo.getType().isList() ? " 数组" : ""
                )).append('\n');
                sb.append(String.format(".参数 %s, 文本型, 参考, http源消息，包括错误消息", paramSource)).append('\n');
                // 构造参数
                for (Info param : methodInfo.getParams()) {
                    sb.append(String.format(".参数 %s, %s,%s%s",
                                    param.getName(),
                                    param.getType(),
                                    !param.isNonNull() ? " 可空" : "",
                                    param.isList() ? " 数组" : ""
                            )
                    ).append('\n');
                }
                // 构造局部变量
                sb.append(".局部变量 i, 整数型").append('\n');
                sb.append(".局部变量 code, 整数型").append('\n');
                sb.append(".局部变量 length, 整数型").append('\n');
                sb.append(".局部变量 json, zyJsonDocument").append('\n');
                sb.append(".局部变量 data, zyJsonDocument").append('\n');
                sb.append('\n');
                // 构造方法代码
                sb.append("json.创建 ()").append('\n');
                sb.append(String.format("json.置文本 (“%s”, “%s”)", jsonApi, apiName)).append('\n');
                sb.append(String.format("json.置文本 (“%s”, “%s”)", jsonMethod, methodInfo.getName())).append('\n');
                // 构建参数json
                for (Info param : methodInfo.getParams()) {
                    if (!param.isNonNull()) {
                        // 可空
                        sb.append(String.format(".如果真 (是否为空 (%s) ＝ 假)", param.getName())).append('\n');
                    }
                    // =====
                    if (param.isList()) {
                        // 处理数组
                        sb.append(String.format("length ＝ 取数组成员数 (%s)", param.getName())).append('\n');
                        sb.append(".计次循环首 (length, i)").append('\n');
                        if (param.getType().equals(param.getSrcType())) {
                            // 对象类型
                            sb.append(String.format("json.置JSON (“%s.%s[” ＋ 到文本 (i － 1) ＋ “]”, %s%s(%s [i]))",
                                    jsonParam,
                                    param.getName(),
                                    build,
                                    param.getType(),
                                    param.getName()
                            )).append('\n');
                        } else {
                            // 基础类型
                            sb.append(String.format("json.%s (“%s.%s[” ＋ 到文本 (i － 1) ＋ “]”, %s [i])",
                                    jsonNameToeType(param.getType()),
                                    jsonParam,
                                    param.getName(),
                                    param.getName()
                            )).append('\n');
                        }
                        sb.append(".计次循环尾 ()").append('\n');
                    } else {
                        // 处理单个
                        if (param.getType().equals(param.getSrcType())) {
                            // 对象类型
                            sb.append(String.format("json.置JSON (“%s.%s”, %s%s(%s))",
                                    jsonParam,
                                    param.getName(),
                                    build,
                                    param.getType(),
                                    param.getName()
                            )).append('\n');
                        } else {
                            // 基础类型
                            sb.append(String.format("json.%s (“%s.%s”, %s)",
                                    jsonNameToeType(param.getType()),
                                    jsonParam,
                                    param.getName(),
                                    param.getName()
                            )).append('\n');
                        }
                    }
                    // =====
                    if (!param.isNonNull()) {
                        // 可空
                        sb.append(".如果真结束").append('\n');
                    }
                }
                // 构建http请求代码
                StringBuilder build_return = new StringBuilder();
                if (methodInfo.getType().isList()) {
                    // 处理数组
                    build_return.append("length ＝ data.取成员数 (“data”)").append('\n');
                    build_return.append(String.format("重定义数组 (%s, 假, length)", methodInfo.getType().getSrcType())).append('\n');
                    build_return.append(".计次循环首 (length, i)").append('\n');
                    if (methodInfo.getType().getType().equals(methodInfo.getType().getSrcType())) {
                        // 对象类型
                        build_return.append(String.format("%s [i] ＝ %s%s (data.取属性 (, “data[” ＋ 到文本 (i － 1) ＋ “]”).到文本 ())",
                                methodInfo.getType().getSrcType(),
                                build,
                                methodInfo.getType().getType()
                        )).append('\n');
                    } else {
                        // 基础类型
                        build_return.append(String.format("%s [i] ＝ data.%s (“data[” ＋ 到文本 (i － 1) ＋ “]”)",
                                methodInfo.getType().getSrcType(),
                                eTypeToJsonName(methodInfo.getType().getType())
                        )).append('\n');
                    }
                    build_return.append(".计次循环尾 ()");
                } else {
                    // 处理对象
                    if (methodInfo.getType().getType().equals(methodInfo.getType().getSrcType())) {
                        // 对象类型
                        build_return.append(String.format("%s ＝ %s%s (data.取属性 (, “data”).到文本 ())",
                                methodInfo.getType().getSrcType(),
                                build,
                                methodInfo.getType().getType()
                        ));
                    } else {
                        // 基础类型
                        build_return.append(String.format("%s ＝ data.%s (“data”)",
                                methodInfo.getType().getSrcType(),
                                eTypeToJsonName(methodInfo.getType().getType())
                        ));
                    }
                }

                sb.append(String.format("""              
                        source ＝ 网页_访问S (%s, 1, json.到文本 ())
                        data.解析 (source)
                        code ＝ data.取整数 (“code”)
                        .如果真 (code ＝ 0)
                        %s
                        .如果真结束
                        返回 (code)
                        """, url, build_return));
            }

            // 类分隔符
            sb.append('\n');
            sb.append("======================================================").append('\n');
            sb.append("======================================================").append('\n');
            sb.append('\n');
        }
        return sb.toString();
    }


    @SneakyThrows
    public void exec(Class<?> clazz, List<Info> infos) {
        String url = System.getProperty("user.dir") + "\\src\\main\\java\\";
        url = url + clazz.getName().replace('.', '\\') + ".java";
        String str = FileUtil.readAsString(new File(url));
        int index = str.indexOf("class");
        ;
        for (Info info : infos) {
            try {
                int a = str.indexOf("/**", index);
                int b = str.indexOf(" " + info.getName() + ";");
                index = b;
                String substring = str.substring(a, b);
                int c = substring.indexOf("\n");
                int d = substring.indexOf("*/");
                substring = substring.substring(c, d);
                String[] split = substring.split("\n");
                StringBuilder doc = new StringBuilder();
                for (String s : split) {
                    String replace = s.replace(" ", "");
                    if (!replace.isEmpty()) {
                        if (replace.charAt(0) == '*') {
                            replace = replace.substring(1);
                        }
                        doc.append(replace);
                    }
                }
                info.setJavaDoc(doc.toString());
            } catch (Exception e) {
                info.setJavaDoc("");
            }

        }
    }

    @SneakyThrows
    private void config() {
        // 配置实体类
        put(Channel.class);
        put(ChannelPermissions.class);
        put(DMS.class);
        put(Guild.class);
        put(MessageSetting.class);
        put(NoSpeak.class);
        put(PinsMessage.class);
        put(Schedule.class);
        put(User.class);
        put(Announces.class);
        put(RecommendChannel.class);
        put(APIPermission.class);
        put(ApiPermissionDemand.class);
        put(ApiPermissionDemandIdentify.class);
        put(AudioAction.class);
        put(AudioControl.class);
        put(Emoji.class);
        put(MessageReaction.class);
        put(ReactionReply.class);
        put(ReactionReply.class);
        put(ReactionTarget.class);
        put(Post.class);
        put(PostInfo.class);
        put(Reply.class);
        put(ReplyInfo.class);
        put(CreateThread.class);
        put(ThreadList.class);
        put(AtGuildInfo.class);
        put(AtInfo.class);
        put(AtRoleInfo.class);
        put(AtUserInfo.class);
        put(AuditResult.class);
        put(ChannelInfo.class);
        put(Elem.class);
        put(EmojiInfo.class);
        put(ImageElem.class);
        put(Paragraph.class);
        put(ParagraphProps.class);
        put(PlatImage.class);
        put(PlatVideo.class);
        put(RichObject.class);
        put(RichText.class);
        put(TextElem.class);
        put(TextInfo.class);
        put(TextProps.class);
        put(UrlElem.class);
        put(UrlInfo.class);
        put(VideoElem.class);
        put(ForumThread.class);
        put(ForumThreadInfo.class);
        put(Member.class);
        put(MemberQueryLimit.class);
        put(MessageArk.class);
        put(MessageArkKv.class);
        put(MessageArkObj.class);
        put(MessageArkObjKv.class);
        put(MessageEmbed.class);
        put(MessageEmbedField.class);
        put(MessageEmbedThumbnail.class);
        put(Action.class);
        put(Button.class);
        put(InlineKeyboard.class);
        put(InlineKeyboardRow.class);
        put(Permission.class);
        put(RenderData.class);
        put(RequestCustomKeyboard.class);
        put(Message.class);
        put(MessageAttachment.class);
        put(MessageAudited.class);
        put(MessageDelete.class);
        put(MessageKeyboard.class);
        put(MessageMarkdown.class);
        put(MessageMarkdownParams.class);
        put(MessageReference.class);
        put(GuildRoleList.class);
        put(NewRole.class);
        put(Role.class);
        put(AudioLiveChannelMemberEvent.class);
        put(AudioLiveChannelMemberEventDObject.class);
        put(AudioMessageEvent.class);
        put(AudioMessageEventDObject.class);
        put(ChannelDObject.class);
        put(ChannelEventInfo.class);
        put(DirectMessageDObject.class);
        put(DirectMessageEventInfo.class);
        put(ForumEvent.class);
        put(ForumEventDObject.class);
        put(GuildDObject.class);
        put(GuildEventInfo.class);
        put(GuildMemberCreateEventInfo.class);
        put(GuildMemberCreateDObject.class);
        put(GuildMemberEventInfo.class);
        put(GuildMemberDObject.class);
        put(InterActionEvent.class);
        put(InterActionEventDObject.class);
        put(InterActionType.class);
        put(Resolved.class);
        put(ReactionEventInfo.class);
        put(OpenForumEventInfo.class);
        put(PrivateDObject.class);
        put(PrivateDomainMessageInfo.class);
        put(PublicMessageEvent.class);
        put(PublicMessageEventDObject.class);
        put(Op_User.class);
        put(PrivateMessageDeleteDObject.class);
        put(PrivateMessageDeleteEvent.class);
        put(PublicMessageDeleteDObject.class);
        put(PublicMessageDeleteEvent.class);
    }

    private void configApi() {
        putApi(AnnouncesApi.class);
        putApi(ApiPermissionApi.class);
        putApi(AudioApi.class);
        putApi(ChannelApi.class);
        putApi(ChannelPermissionsApi.class);
        putApi(DMS_Api.class);
        putApi(ForumApi.class);
        putApi(GuildApi.class);
        putApi(MemberApi.class);
        putApi(MessageApi.class);
        putApi(MessageReactionApi.class);
        putApi(MessageSettingApi.class);
        putApi(NoSpeakApi.class);
        putApi(PinsMessageApi.class);
        putApi(RoleApi.class);
        putApi(ScheduleApi.class);
        putApi(UserApi.class);
    }

    private void putApi(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        List<MethodInfo> methodInfos = new ArrayList<>();
        for (Method method : methods) {
            Type genericReturnType = method.getGenericReturnType();
            MethodInfo methodInfo = new MethodInfo();
            methodInfo.setName(method.getName());
            methodInfo.setFormatName(method.getName());     // 可以获取注解自定义名称
            methodInfo.setParams(new ArrayList<>());

            EName annotation = method.getAnnotation(EName.class);
            if (annotation != null){
                methodInfo.setFormatName(annotation.name());
            }

            // 构建返回值
            if (genericReturnType instanceof ParameterizedType type) {
                // 泛型返回
                Type[] actualTypeArguments = type.getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    if (actualTypeArgument instanceof ParameterizedType list) {
                        // list泛型
                        Class<?> typeArgClass = (Class<?>) list.getActualTypeArguments()[0];
                        String name = getName(typeArgClass.getName());
                        Info info = new Info();
                        info
                                .setName("")
                                .setSrcType(name)
                                .setType(getEType(name))
                                .setList(true)
                                .setJavaDoc("");
                        methodInfo.setType(info);
                    } else {
                        // 普通类型
                        Class<?> typeArgClass = (Class<?>) actualTypeArgument;
                        if (typeArgClass != String.class) {
                            String name = getName(typeArgClass.getName());
                            Info info = new Info();
                            info
                                    .setName("")
                                    .setSrcType(name)
                                    .setType(getEType(name))
                                    .setList(false)
                                    .setJavaDoc("");
                            ;
                            methodInfo.setType(info);
                        }
                    }

                }
            } else {
                // 普通返回
                Class<?> returnType = method.getReturnType();
                String name = getName(returnType.getName());
                Info info = new Info();
                info
                        .setName("")
                        .setSrcType(name)
                        .setType(getEType(name))
                        .setList(false)
                        .setJavaDoc("");
                methodInfo.setType(info);
            }
            // 构建参数类型
            Parameter[] parameters = method.getParameters();

            for (Parameter parameter : parameters) {
                Type genericParameterType = parameter.getParameterizedType();
                Info info = new Info()
                        .setName(parameter.getName())
                        .setJavaDoc("");
                ENonNull eNonNull = parameter.getAnnotation(ENonNull.class);
                if (eNonNull != null){
                    info.setNonNull(true);
                }

                if (genericParameterType instanceof ParameterizedType type) {
                    // 泛型类型
                    info.setList(true);
                    Class<?> paramClass = (Class<?>) type.getActualTypeArguments()[0];
                    info
                            .setSrcType(getName(paramClass.getName()))
                            .setType(getEType(info.getSrcType()));
                } else {
                    // 普通类型
                    info.setList(false);
                    Class<?> paramClass = (Class<?>) genericParameterType;
                    info
                            .setSrcType(getName(paramClass.getName()))
                            .setType(getEType(info.getSrcType()));
                }
                methodInfo.getParams().add(info);
            }
/*            System.out.println("方法名：" + methodInfo.getName() + " -> " + methodInfo.getType());
            for (Info info : methodInfo.getParams()){
                System.out.println("参数 -> " + info.getName() + " - " + info.getType());
            }
            System.out.println("====");*/
            methodInfos.add(methodInfo);
        }
        apiMap.put(getName(clazz.getName()), methodInfos);
    }

    @PostConstruct
    public void start() {
        config();
        configApi();
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println("======易语言代码生成=====");
        System.out.println("=======================");
        System.out.println("=======================");

        log.info("\n" + generateAPIMethod() + "\n");


    }

    @Data
    @Accessors(chain = true)
    private static class MethodInfo {
        String name;    // 方法名
        String formatName;  // 格式化名
        Info type;    // 返回类型
        List<Info> params;  // 参数列表
    }

    @Data
    @Accessors(chain = true)
    private static class Info {
        String name;
        String type;
        String srcType; // 源类型
        String javaDoc;
        boolean nonNull = false;    // 不能为空
        boolean list;
    }
}

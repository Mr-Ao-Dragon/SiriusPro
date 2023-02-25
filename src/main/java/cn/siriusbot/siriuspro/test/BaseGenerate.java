package cn.siriusbot.siriuspro.test;

import cn.siriusbot.siriuspro.bot.annotation.EDoc;
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
import cn.siriusbot.siriuspro.bot.api.pojo.message.ark.*;
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
import cn.siriusbot.siriuspro.bot.pojo.message.AuditMessageEvent.AuditMessageEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.AuditMessageEvent.AuditMessageEventDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.ChannelEvent.ChannelDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.ChannelEvent.ChannelEventInfo;
import cn.siriusbot.siriuspro.bot.pojo.message.DirectMessageEvent.DirectMessageDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.DirectMessageEvent.DirectMessageDeleteDObject;
import cn.siriusbot.siriuspro.bot.pojo.message.DirectMessageEvent.DirectMessageDeleteEvent;
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
import org.aspectj.util.FileUtil;

import java.io.File;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseGenerate {

    FormatGenerateType generateType;

    public BaseGenerate(FormatGenerateType generateType) {
        this.generateType = generateType;
        config();
        configApi();
    }

    protected final Map<String, List<Info>> map = new HashMap<>();
    protected final Map<String, List<MethodInfo>> apiMap = new HashMap<>();

    @Data
    @Accessors(chain = true)
    protected static class MethodInfo {
        String name;    // 方法名
        String formatName;  // 格式化名
        Info type;    // 返回类型
        List<Info> params;  // 参数列表
    }

    @Data
    @Accessors(chain = true)
    protected static class Info {
        String name;
        String type;
        String srcType; // 源类型
        String javaDoc;
        boolean obj = false;  // 是否为对象
        boolean nonNull = false;    // 不能为空
        boolean list;
    }

    private String getName(String s) {
        return s.substring(s.lastIndexOf(".") + 1);
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
            String eType = this.generateType.getEType(type);
            Info info = new Info()
                    .setName(name)
                    .setSrcType(type)
                    .setType(this.generateType.getEType(type))
                    .setList(isList);
            if (info.getType() == null){
                info.setType(type);
                info.setObj(true);
            }
            list.add(info);
        }
        exec(clazz, list);
        map.put(getName(clazz.getName()), list);
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
        put(Message.class);
        put(MessageAttachment.class);
        put(MessageAudited.class);
        put(MessageDelete.class);
        put(MessageKeyboard.class);
        put(MessageMarkdown.class);
        put(MessageMarkdownParams.class);
        put(MessageReference.class);
        put(MessageArk.class);
        put(MessageArkKv.class);
        put(MessageArkObj.class);
        put(MessageArkObjKv.class);
        put(TextAndLinkObject.class);
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
        put(GuildRoleList.class);
        put(NewRole.class);
        put(Role.class);
        put(Op_User.class);
        put(AudioLiveChannelMemberEvent.class);
        put(AudioLiveChannelMemberEventDObject.class);
        put(AudioMessageEvent.class);
        put(AudioMessageEventDObject.class);
        put(AuditMessageEvent.class);
        put(AuditMessageEventDObject.class);
        put(ChannelDObject.class);
        put(ChannelEventInfo.class);
        put(DirectMessageDeleteDObject.class);
        put(DirectMessageDeleteEvent.class);
        put(DirectMessageDObject.class);
        put(DirectMessageEventInfo.class);
        put(ForumEvent.class);
        put(ForumEventDObject.class);
        put(GuildDObject.class);
        put(GuildEventInfo.class);
        put(GuildMemberDObject.class);
        put(GuildMemberEventInfo.class);
        put(GuildMemberCreateDObject.class);
        put(GuildMemberCreateEventInfo.class);
        put(InterActionEvent.class);
        put(InterActionEventDObject.class);
        put(InterActionType.class);
        put(Resolved.class);
        put(ReactionEventInfo.class);
        put(OpenForumEventInfo.class);
        put(PrivateDObject.class);
        put(PrivateDomainMessageInfo.class);
        put(PrivateMessageDeleteDObject.class);
        put(PrivateMessageDeleteEvent.class);
        put(PublicMessageDeleteDObject.class);
        put(PublicMessageDeleteEvent.class);
        put(PublicMessageEvent.class);
        put(PublicMessageEventDObject.class);
        put(Op_User.class);
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
                                .setType(this.generateType.getEType(name))
                                .setList(true)
                                .setJavaDoc("");
                        if (info.getType() == null){
                            info.setType(name);
                            info.setObj(true);
                        }
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
                                    .setType(this.generateType.getEType(name))
                                    .setList(false)
                                    .setJavaDoc("");
                            if (info.getType() == null){
                                info.setType(name);
                                info.setObj(true);
                            }
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
                        .setType(this.generateType.getEType(name))
                        .setList(false)
                        .setJavaDoc("");
                if (info.getType() == null){
                    info.setType(name);
                    info.setObj(true);
                }
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
                EDoc eDoc = parameter.getAnnotation(EDoc.class);
                if (eDoc != null){
                    info.setJavaDoc(eDoc.doc());
                }

                if (genericParameterType instanceof ParameterizedType type) {
                    // 泛型类型
                    info.setList(true);
                    Class<?> paramClass = (Class<?>) type.getActualTypeArguments()[0];
                    info
                            .setSrcType(getName(paramClass.getName()))
                            .setType(this.generateType.getEType(info.getSrcType()));
                } else {
                    // 普通类型
                    info.setList(false);
                    Class<?> paramClass = (Class<?>) genericParameterType;
                    info
                            .setSrcType(getName(paramClass.getName()))
                            .setType(this.generateType.getEType(info.getSrcType()));
                }
                if (info.getType() == null){
                    info.setType(info.getSrcType());
                    info.setObj(true);
                }
                methodInfo.getParams().add(info);
            }
            methodInfos.add(methodInfo);
        }
        apiMap.put(getName(clazz.getName()), methodInfos);
    }
}

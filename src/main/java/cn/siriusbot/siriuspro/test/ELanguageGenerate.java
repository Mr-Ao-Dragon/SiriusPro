package cn.siriusbot.siriuspro.test;

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
import cn.siriusbot.siriuspro.bot.api.pojo.forum.responseObj.ThreadList;
import cn.siriusbot.siriuspro.bot.api.pojo.forum.responseObj.CreateThread;
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
import cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent.PublicMessageEvent;
import cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent.PublicMessageEventDObject;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 易语言代码通用解析器生成
 */
@Component
@Log4j2
public class ELanguageGenerate {

    private Map<String, List<Info>> map = new HashMap<>();

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
                    sb.append(", , \"1\"");
                }
                sb.append('\n');
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
                    sb.append("    ").append(String.format("清除数组 (%s.%s)", o, info.getName())).append('\n');
                    sb.append("    ").append(".计次循环首 (length, i)").append('\n');
                    String eType = info.getType();
                    if (eType.equals(info.getSrcType())) {
                        // 对象类型成员
                        sb.append("    ").append("    ").append(String.format(
                                "%s(%s.%s, %s(“%s“,json.取属性 (, “%s”).到文本()))",
                                transition,
                                o,
                                info.getName(),
                                methodName,
                                info.getType(),
                                info.getName()
                        )).append('\n');
                    } else {
                        // 普通类型成员
                        String jsonType;
                        switch (eType) {
                            case "文本型" -> jsonType = "取文本";
                            case "整数型" -> jsonType = "取整数";
                            case "逻辑型" -> jsonType = "取逻辑";
                            case "双精度小数型" -> jsonType = "取双精度";
                            case "长整数型" -> jsonType = "取长整数";
                            default -> jsonType = "取文本";
                        }
                        sb.append("    ").append(String.format("    加入成员 (%s.%s, item.取成员 (, 0).%s ())", o, info.getName(), jsonType)).append('\n');
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
    }


    @PostConstruct
    public void start() {
        config();
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println("======易语言代码生成=====");
        System.out.println("=======================");
        System.out.println("=======================");
        System.out.println(map);
        //log.info("\n" + generateTypeInfos() + "\n");
        log.info("\n" + generateAnalyticMethod() + "\n");
    }


    @Data
    @Accessors(chain = true)
    private static class Info {
        String name;
        String type;
        String srcType; // 源类型
        boolean list;
    }
}

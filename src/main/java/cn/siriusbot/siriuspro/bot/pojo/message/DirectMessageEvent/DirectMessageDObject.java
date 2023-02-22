package cn.siriusbot.siriuspro.bot.pojo.message.DirectMessageEvent;

import cn.siriusbot.siriuspro.bot.api.pojo.User;
import cn.siriusbot.siriuspro.bot.api.pojo.member.Member;
import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 私信消息事件详情对象
 */
@Data
@Accessors(chain = true)
public class DirectMessageDObject implements MessageBody {


    /**
     * 私信来源频道ID
     */
    private String src_guild_id;

    /**
     *  在子频道的消息排序
     */
     private String seq_in_channel;

    /**
     * 消息创建者
     */
    private User author;

    /**
     * 私信会话ID
     */
    private String guild_id;

    /**
     * 成员对象
     */
    private Member member;

    /**
     * 是否为私信消息
     */
    private Boolean direct_message;

    /**
     * 消息ID
     */
    private String id;

    /**
     * 私信会话关联的子频道ID
     */
    private String channel_id;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息排序
     */
    private Integer seq;

    /**
     * 消息创建时间戳
     */
    private String timestamp;
}

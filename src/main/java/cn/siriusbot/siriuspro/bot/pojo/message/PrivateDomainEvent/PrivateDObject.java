package cn.siriusbot.siriuspro.bot.pojo.message.PrivateDomainEvent;


import cn.siriusbot.siriuspro.bot.api.pojo.User;
import cn.siriusbot.siriuspro.bot.api.pojo.message.Message;
import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import cn.siriusbot.siriuspro.entity.Op_User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Member;

/**
 * 私域D字段消息对象
 */
@Data
@Accessors(chain = true)
public class PrivateDObject implements MessageBody {
    /**
     * 子频道下消息排序
     */
    private String seq_in_channel;

    /**
     * 操作人用户信息
     */
    private User author;

    /**
     * 频道ID
     */
    private String guild_id;

    /**
     * 操作人成员信息
     */
    private Member member;

    /**
     * 消息id
     */
    private String id;

    /**
     * 子频道ID
     */
    private String channel_id;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 子频道下,消息排序
     */
    private Integer seq;

    /**
     * 事件时间戳
     */
    private String timestamp;

    /**
     * 被操作的消息对象
     */
    private Message message;

    /**
     * 操作人对象
     */
    private Op_User op_user;
}

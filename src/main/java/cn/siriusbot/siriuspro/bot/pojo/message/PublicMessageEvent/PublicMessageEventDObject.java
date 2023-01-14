package cn.siriusbot.siriuspro.bot.pojo.message.PublicMessageEvent;


import cn.siriusbot.siriuspro.bot.api.pojo.User;
import cn.siriusbot.siriuspro.bot.api.pojo.member.Member;
import cn.siriusbot.siriuspro.bot.api.pojo.message.Message;
import cn.siriusbot.siriuspro.bot.pojo.message.MessageBody;
import cn.siriusbot.siriuspro.entity.Op_User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 事件详情对象
 */
@Data
@Accessors(chain = true)
public class PublicMessageEventDObject implements MessageBody {

    /**
     * 被撤回的消息对象
     */
    private Message message;

    /**
     * 操作人对象
     */
    private Op_User op_user;

    /**
     * 子频道消息排序
     */
    private String seq_in_channel;

    /**
     * 发送人用户信息
     */
    private User author;

    /**
     * 消息中被@的人
     */
    private List<User> mentions;

    /**
     * 发送人成员信息
     */
    private Member member;

    /**
     * 消息ID
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
     * 消息排序
     */
    private Integer seq;

    /**
     * 消息创建时间
     */
    private String timestamp;
}

package cn.siriusbot.siriuspro.entity.pojo.message;


import cn.siriusbot.siriuspro.entity.pojo.User;
import cn.siriusbot.siriuspro.entity.pojo.message.ark.MessageArk;
import cn.siriusbot.siriuspro.entity.pojo.message.embed.MessageEmbed;
import cn.siriusbot.siriuspro.entity.pojo.member.Member;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Message {
    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", channel_id='" + channel_id + '\'' +
                ", guild_id='" + guild_id + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", edited_timestamp='" + edited_timestamp + '\'' +
                ", mention_everyone=" + mention_everyone +
                ", author=" + author +
                ", attachments=" + attachments +
                ", embeds=" + embeds +
                ", mentions=" + mentions +
                ", member=" + member +
                ", ark=" + ark +
                ", seq=" + seq +
                ", seq_in_channel=" + seq_in_channel +
                ", message_reference=" + message_reference +
                ", src_guild_id='" + src_guild_id + '\'' +
                '}';
    }

    /**
     * 消息id
     */
    private String id;
    /**
     * 子频道ID
     */
    private String channel_id;
    /**
     * 频道ID
     */
    private String guild_id;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息创建时间
     */
    private String timestamp;
    /**
     * 消息编辑时间
     */
    private String edited_timestamp;
    /**
     * 是否是@全员消息
     */
    private boolean mention_everyone;
    /**
     * 消息创建者
     */
    private User author;
    /**
     * 附件对象
     */
    private List<MessageAttachment> attachments;
    /**
     * embed消息
     */
    private List<MessageEmbed> embeds;
    /**
     * 消息中@的人
     */
    private List<User> mentions;
    /**
     * 消息创建者的member信息
     */
    private Member member;
    /**
     * ark消息对象
     */
    private MessageArk ark;
    /**
     * 消息之间的排序，2022年8月1日已废弃
     */
    private Integer seq;
    /**
     * 子频道消息的排序,不同子频道之间消息,无法排序
     */
    private Integer seq_in_channel;
    /**
     * 引用消息对象
     */
    private MessageReference message_reference;

    /**
     * 私信场景下识别真实的来源频道id
     */
    private String src_guild_id;

    /**
     * 消息撤回时间类型
     */
    public enum DELETE_MESSAGE {
        /**
         * 近三天消息
         */
        THREE_DAY(3),
        /**
         * 近七天消息
         */
        SEVEN_DAY(7),
        /**
         * 近半个月消息
         */
        FIFTEEN_DAY(15),
        /**
         * 近一个月消息
         */
        ONE_MONTH(30),
        /**
         * 全部消息
         */
        ALL_MESSAGE(-1);
        private int value;

        DELETE_MESSAGE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}

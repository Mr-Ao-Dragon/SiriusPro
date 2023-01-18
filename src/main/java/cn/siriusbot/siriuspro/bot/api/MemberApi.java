package cn.siriusbot.siriuspro.bot.api;


import cn.siriusbot.siriuspro.bot.annotation.EDoc;
import cn.siriusbot.siriuspro.bot.annotation.ENonNull;
import cn.siriusbot.siriuspro.bot.api.pojo.member.Member;
import cn.siriusbot.siriuspro.bot.api.pojo.member.MemberQueryLimit;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import lombok.NonNull;

import java.util.List;

/**
 * 成员Api
 */
public interface MemberApi {

    /**
     * 获取频道成员列表
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param after    上一次回包中最后一个member的user id， 如果是第一次请求填 0，默认为 0
     * @param limit    查询条数
     * @return 返回成员列表 分页大小，1-400，默认是 1。成员较多的频道尽量使用较大的limit值，以减少请求数
     */
    @EDoc(doc = "获取频道成员列表")
    Tuple<List<Member>, String> getMemberList
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "上一次查询的数据中最后一个user_id，首次请求请写0，默认位0")
            String after,

            @EDoc(doc = "单次分页大小，默认1，最大400，为减少请求数，请使用较大的limit值")
            int limit);

    /**
     * 获取成员详情
     *
     * @param bot_id   传入机器人ID
     * @param guild_id 频道ID
     * @param user_id  用户ID
     * @return 返回成员对象
     */
    @EDoc(doc = "获取成员详情")
    Tuple<Member, String> getMemberInfo
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "用户ID")
            @NonNull @ENonNull
            String user_id
    );

    /**
     * 获取拥有此身份组的成员列表
     *
     * @param bot_id      传入机器人ID
     * @param guild_id    频道ID
     * @param role_id     身份组ID
     * @param start_index 上一次返回包中的next，第一次请求填0，默认0
     * @param limit       分页大小 1-400，请尽量使用较大的limit值，减少请求次数
     * @return 返回持有指定身份组ID的成员列表
     */

    @EDoc(doc = "获取身份组成员列表")
    Tuple<MemberQueryLimit, String> getMemberListByRoleId
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "身份组ID")
            @NonNull @ENonNull String role_id,

            @EDoc(doc = "上一次数据包中的next，第一次填写0，默认为0")
            String start_index,

            @EDoc(doc = "分页大小，默认1，最大400")
            int limit
    );

    /**
     * 将指定成员从频道内移除
     *
     * @param bot_id                  传入机器人ID
     * @param user_id                 用户ID
     * @param guild_id                频道ID
     * @param add_black               添加到黑名单
     * @param delete_history_msg_days 撤回消息的天数
     * @return 移除结果
     */
    @EDoc(doc = "移除成员")
    boolean deleteMemberByUserId
    (
            @EDoc(doc = "机器人ID")
            @NonNull @ENonNull String bot_id,

            @EDoc(doc = "频道ID")
            @NonNull @ENonNull String guild_id,

            @EDoc(doc = "用户ID")
            @NonNull @ENonNull String user_id,

            @EDoc(doc = "是否拉黑")
            boolean add_black,

            @EDoc(doc = "撤回消息的天数范围")
            int delete_history_msg_days);
}

package cn.siriusbot.siriuspro.entity.api;


import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.entity.impl.message.requestPack.member.Member;
import cn.siriusbot.siriuspro.entity.impl.message.requestPack.member.MemberQueryLimit;

import java.util.List;
import java.util.Map;

/**
 * 成员Api
 */
public interface MemberApi {

    /**
     * 获取频道成员列表
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @param after 上一次回包中最后一个member的user id， 如果是第一次请求填 0，默认为 0
     * @return 返回成员列表 分页大小，1-400，默认是 1。成员较多的频道尽量使用较大的limit值，以减少请求数
     *
     */
    public abstract Map<List<Member>,Object> getMemberList(Bot bot, String guild_id, String after, int limit);

    /**
     * 获取成员详情
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @param user_id 用户ID
     * @return 返回成员对象
     */
    public abstract Map<Member,Object> getMemberInfo(Bot bot, String guild_id,String user_id);

    /**
     * 获取拥有此身份组的成员列表
     * @param bot 传入机器人对象
     * @param guild_id 频道ID
     * @param role_id 身份组ID
     * @param start_index 上一次返回包中的next，第一次请求填0，默认0
     * @return 返回持有指定身份组ID的成员列表
     */
    public abstract Map<MemberQueryLimit,Object> getMemberListByRoleId(Bot bot, String guild_id, String role_id, String start_index, int limit);

    /**
     * 将指定成员从频道内移除
     * @param bot 传入机器人对象
     * @param user_id 用户ID
     * @param guild_id 频道ID
     * @param add_black 添加到黑名单
     * @param delete_history_msg_days 撤回消息的天数
     * @return 移除结果
     */
    public abstract boolean deleteMemberByUserId(Bot bot,String guild_id,String user_id,boolean add_black,int delete_history_msg_days);
}

package cn.siriusbot.siriuspro.entity.api.impl;

import cn.siriusbot.siriuspro.bot.Bot;
import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.entity.api.MemberApi;
import cn.siriusbot.siriuspro.entity.pojo.member.Member;
import cn.siriusbot.siriuspro.entity.pojo.member.MemberQueryLimit;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberImpl implements MemberApi {
    /**
     * 获取频道成员列表
     *
     * @param bot_id      传入机器人对象ID
     * @param guild_id 频道ID
     * @param after    上一次回包中最后一个member的user id， 如果是第一次请求填 0，默认为 0
     * @return 返回成员列表 分页大小，1-400，默认是 1。成员较多的频道尽量使用较大的limit值，以减少请求数
     */
    @SneakyThrows
    @Override
    public Map<List<Member>,Object> getMemberList(String bot_id, String guild_id, String after, int limit) {
        Bot bot = BotManager.getBotByBotId(bot_id);
        if (after == null)
            after = "0";
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/members?after=" + after + "&limit=" + limit).build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();
        List<Member> memberList = JSONObject.parseObject(data, List.class);
        Map<List<Member>,Object> map = new HashMap<>();
        map.put(memberList,data);
        return map;
    }


    /**
     * 获取成员详情
     *
     * @param bot_id      传入机器人对象ID
     * @param guild_id 频道ID
     * @param user_id  用户ID
     * @return 返回成员对象
     */
    @SneakyThrows
    @Override
    public Map<Member,Object> getMemberInfo(String bot_id, String guild_id, String user_id) {
        Bot bot = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id).build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();
        Map<Member,Object> map = new HashMap<>();
        map.put(JSONObject.parseObject(data, Member.class),data);
        return map;
    }

    /**
     * 获取拥有此身份组的成员列表
     *
     * @param bot_id         传入机器人对象ID
     * @param guild_id    频道ID
     * @param role_id     身份组ID
     * @param start_index 上一次返回包中的next，第一次请求填0，默认0
     * @param limit       分页大小，1-400，默认是 1。成员较多的频道尽量使用较大的limit值，以减少请求数
     * @return 返回持有指定身份组ID的成员列表
     */
    @SneakyThrows
    @Override
    public Map<MemberQueryLimit,Object> getMemberListByRoleId(String bot_id, String guild_id, String role_id, String start_index, int limit) {
        Bot bot = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/roles/" + role_id + "/members?start_index=" + start_index + "&limit=" + limit).build();
        Response response = SiriusHttpUtils.getRequest(bot, request);
        String data = response.body().string();
        MemberQueryLimit memberList = JSONObject.parseObject(data,MemberQueryLimit.class);
        Map<MemberQueryLimit,Object> map = new HashMap<>();
        map.put(memberList,data);
        return map;
    }

    /**
     * 将指定成员从频道内移除
     *
     * @param bot_id                     传入机器人对象ID
     * @param user_id                 用户ID
     * @param guild_id                频道ID
     * @param add_black               添加到黑名单
     * @param delete_history_msg_days 撤回消息的天数
     * @return 移除结果
     */
    @SneakyThrows
    @Override
    public boolean deleteMemberByUserId(String bot_id, String guild_id, String user_id, boolean add_black, int delete_history_msg_days) {
        Bot bot = BotManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(bot.getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id).build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("add_blacklist", add_black);
        json.put("delete_history_msg_days", delete_history_msg_days);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.deleteRequest(bot, request, body);
        return response.code() == 204;
    }
}

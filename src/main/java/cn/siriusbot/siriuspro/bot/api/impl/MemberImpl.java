package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.BotManager;
import cn.siriusbot.siriuspro.bot.SiriusBotClient;
import cn.siriusbot.siriuspro.bot.api.MemberApi;
import cn.siriusbot.siriuspro.bot.api.pojo.member.Member;
import cn.siriusbot.siriuspro.bot.api.pojo.member.MemberQueryLimit;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.http.SiriusHttpUtils;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class  MemberImpl implements MemberApi {

    @Autowired
    BotManager botManager;

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
    public Tuple<List<Member>,String> getMemberList(String bot_id, String guild_id, String after, int limit) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        if (after == null)
            after = "0";
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "guilds/" + guild_id + "/members?after=" + after + "&limit=" + limit).build();
        Response response = SiriusHttpUtils.getRequest(siriusBotClient, request);
        String data = response.body().string();
        data = EmojiParser.parseToUnicode(data);
        List<Member> memberList = JSONObject.parseObject(data, List.class);
        Tuple<List<Member>,String> tuple = new Tuple<>();
        tuple.setFirst(memberList).setSecond(data);
        return tuple;
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
    public Tuple<Member,String> getMemberInfo(String bot_id, String guild_id, String user_id) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id).build();
        Response response = SiriusHttpUtils.getRequest(siriusBotClient, request);
        String data = response.body().string();
        data = EmojiParser.parseToUnicode(data);
        Tuple<Member,String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Member.class)).setSecond(data);
        return tuple;
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
    public Tuple<MemberQueryLimit,String> getMemberListByRoleId(String bot_id, String guild_id, String role_id, String start_index, int limit) {
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "guilds/" + guild_id + "/roles/" + role_id + "/members?start_index=" + start_index + "&limit=" + limit).build();
        Response response = SiriusHttpUtils.getRequest(siriusBotClient, request);
        String data = response.body().string();
        data = EmojiParser.parseToUnicode(data);
        MemberQueryLimit memberList = JSONObject.parseObject(data,MemberQueryLimit.class);
        Tuple<MemberQueryLimit,String> tuple = new Tuple<>();
        tuple.setFirst(memberList).setSecond(data);
        return tuple;
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
        SiriusBotClient siriusBotClient = botManager.getBotByBotId(bot_id);
        Request request = new Request.Builder().url(siriusBotClient.getSocket().getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id).build();
        MediaType mediaType = MediaType.parse("text/plain;application/json");
        JSONObject json = new JSONObject();
        json.put("add_blacklist", add_black);
        json.put("delete_history_msg_days", delete_history_msg_days);
        RequestBody body = RequestBody.create(mediaType, json.toJSONString());
        Response response = SiriusHttpUtils.deleteRequest(siriusBotClient, request, body);
        return response.code() == 204;
    }
}
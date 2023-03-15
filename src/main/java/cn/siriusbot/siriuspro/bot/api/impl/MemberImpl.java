package cn.siriusbot.siriuspro.bot.api.impl;

import cn.siriusbot.siriuspro.bot.api.MemberApi;
import cn.siriusbot.siriuspro.bot.api.pojo.member.Member;
import cn.siriusbot.siriuspro.bot.api.pojo.member.MemberQueryLimit;
import cn.siriusbot.siriuspro.bot.api.tuple.Tuple;
import cn.siriusbot.siriuspro.bot.client.BotClient;
import cn.siriusbot.siriuspro.bot.event.BotHttpEvent;
import cn.siriusbot.siriuspro.bot.pojo.BotRequest;
import cn.siriusbot.siriuspro.bot.pojo.BotResponse;
import cn.siriusbot.siriuspro.bot.pojo.e.RequestMethod;
import cn.siriusbot.siriuspro.config.bean.BotPool;
import com.alibaba.fastjson.JSONObject;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemberImpl implements MemberApi {

    @Autowired
    BotPool botPool;

    /**
     * 获取频道成员列表
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @param after    上一次回包中最后一个member的user id， 如果是第一次请求填 0，默认为 0
     * @return 返回成员列表 分页大小，1-400，默认是 1。成员较多的频道尽量使用较大的limit值，以减少请求数
     */
    @SneakyThrows
    @Override
    public Tuple<List<Member>, String> getMemberList(@NotNull String bot_id, @NotNull String guild_id, String after, int limit) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/members?after=" + after + "&limit=" + limit)
                .setMethod(RequestMethod.GET);
        if (after == null)
            botRequest.setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/members?limit="+limit);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        List<Member> memberList = JSONObject.parseObject(data, List.class);
        Tuple<List<Member>, String> tuple = new Tuple<>();
        tuple.setFirst(memberList).setSecond(data);
        return tuple;
    }


    /**
     * 获取成员详情
     *
     * @param bot_id   传入机器人对象ID
     * @param guild_id 频道ID
     * @param user_id  用户ID
     * @return 返回成员对象
     */
    @SneakyThrows
    @Override
    public Tuple<Member, String> getMemberInfo(@NotNull String bot_id, @NotNull String guild_id, @NotNull String user_id) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.GET)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        Tuple<Member, String> tuple = new Tuple<>();
        tuple.setFirst(JSONObject.parseObject(data, Member.class)).setSecond(data);
        return tuple;
    }

    /**
     * 获取拥有此身份组的成员列表
     *
     * @param bot_id      传入机器人对象ID
     * @param guild_id    频道ID
     * @param role_id     身份组ID
     * @param start_index 上一次返回包中的next，第一次请求填0，默认0
     * @param limit       分页大小，1-400，默认是 1。成员较多的频道尽量使用较大的limit值，以减少请求数
     * @return 返回持有指定身份组ID的成员列表
     */
    @SneakyThrows
    @Override
    public Tuple<MemberQueryLimit, String> getMemberListByRoleId(@NotNull String bot_id, @NotNull String guild_id, @NotNull String role_id, String start_index, int limit) {
        BotClient client = botPool.getBotById(bot_id);
        if(start_index==null||start_index.isEmpty())
            start_index="0";
        BotRequest botRequest = new BotRequest()
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/roles/" + role_id + "/members?start_index=" + start_index + "&limit=" + limit)
                .setMethod(RequestMethod.GET);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        String data = EmojiParser.parseToUnicode(response.getBody());
        MemberQueryLimit memberList = JSONObject.parseObject(data, MemberQueryLimit.class);
        Tuple<MemberQueryLimit, String> tuple = new Tuple<>();
        tuple.setFirst(memberList).setSecond(data);
        return tuple;
    }

    /**
     * 将指定成员从频道内移除
     *
     * @param bot_id                  传入机器人对象ID
     * @param user_id                 用户ID
     * @param guild_id                频道ID
     * @param add_black               添加到黑名单
     * @param delete_history_msg_days 撤回消息的天数
     * @return 移除结果
     */
    @SneakyThrows
    @Override
    public boolean deleteMemberByUserId(@NotNull String bot_id, @NotNull String guild_id, @NotNull String user_id, boolean add_black, int delete_history_msg_days) {
        BotClient client = botPool.getBotById(bot_id);
        BotRequest botRequest = new BotRequest()
                .setMethod(RequestMethod.DELETE)
                .setUrl(client.getSession().getOpenUrl() + "guilds/" + guild_id + "/members/" + user_id)
                .putRequestBody("add_blacklist", add_black)
                .putRequestBody("delete_history_msg_days", delete_history_msg_days);
        BotHttpEvent http = client.getBean(BotHttpEvent.class);
        BotResponse response = http.req(botRequest);
        return response.getCode() == 204;
    }
}

package cn.siriusbot.siriuspro.bot.pojo.e;

public enum IntentsType {
    ALL(2081166851),
    PUBLIC_ALL(1812730883),
    GUILDS(1),
    GUILD_MEMBERS(1 << 1),
    GUILD_MESSAGES(1 << 9),
    GUILD_MESSAGE_REACTIONS(1 << 10),
    DIRECT_MESSAGE(1 << 12),
    OPEN_FORUMS_EVENT(1 << 18),
    AUDIO_OR_LIVE_CHANNEL_MEMBER(1 << 19),
    INTERACTION(1 << 26),
    MESSAGE_AUDIT(1 << 27),
    FORUMS_EVENT(1 << 28),
    AUDIO_ACTION(1 << 29),
    PUBLIC_GUILD_MESSAGES(1 << 30);

    int val;

    IntentsType(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}

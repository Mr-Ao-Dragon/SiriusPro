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


    static public IntentsType getInstance(int i){
        switch (i){
            case 1 -> {
                return GUILDS;
            }
            case 1 << 1 -> {
                return GUILD_MEMBERS;
            }
            case 1 << 9 -> {
                return GUILD_MESSAGES;
            }
            case 1 << 10 -> {
                return GUILD_MESSAGE_REACTIONS;
            }
            case 1 << 12 -> {
                return DIRECT_MESSAGE;
            }
            case 1 << 18 -> {
                return OPEN_FORUMS_EVENT;
            }
            case 1 << 19 -> {
                return AUDIO_OR_LIVE_CHANNEL_MEMBER;
            }
            case 1 << 26 -> {
                return INTERACTION;
            }
            case 1 << 27 -> {
                return MESSAGE_AUDIT;
            }
            case 1 << 28 -> {
                return FORUMS_EVENT;
            }
            case 1 << 29 -> {
                return AUDIO_ACTION;
            }
            case 1 << 30 -> {
                return PUBLIC_GUILD_MESSAGES;
            }
            case 1812730883 -> {
                return PUBLIC_ALL;
            }
            case 2081166851 -> {
                return ALL;
            }
            default -> {
                return null;
            }
        }
    }
}

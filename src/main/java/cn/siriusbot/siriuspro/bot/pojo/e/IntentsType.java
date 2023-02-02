package cn.siriusbot.siriuspro.bot.pojo.e;

public enum IntentsType {
    ALL(2081166851, "ALL"),
    PUBLIC_ALL(1812730883, "PUBLIC_ALL"),
    GUILDS(1, "GUILDS"),
    GUILD_MEMBERS(1 << 1, "GUILD_MEMBERS"),
    GUILD_MESSAGES(1 << 9, "GUILD_MESSAGES"),
    GUILD_MESSAGE_REACTIONS(1 << 10, "GUILD_MESSAGE_REACTIONS"),
    DIRECT_MESSAGE(1 << 12, "DIRECT_MESSAGE"),
    OPEN_FORUMS_EVENT(1 << 18, "OPEN_FORUMS_EVENT"),
    AUDIO_OR_LIVE_CHANNEL_MEMBER(1 << 19, "AUDIO_OR_LIVE_CHANNEL_MEMBER"),
    INTERACTION(1 << 26, "INTERACTION"),
    MESSAGE_AUDIT(1 << 27, "MESSAGE_AUDIT"),
    FORUMS_EVENT(1 << 28, "FORUMS_EVENT"),
    AUDIO_ACTION(1 << 29, "AUDIO_ACTION"),
    PUBLIC_GUILD_MESSAGES(1 << 30, "PUBLIC_GUILD_MESSAGES");

    int val;
    String name;

    IntentsType(int val, String name) {
        this.val = val;
        this.name = name;
    }

    public int getVal() {
        return val;
    }

    public String getName() {
        return name;
    }


    static public IntentsType getInstance(int i) {
        switch (i) {
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

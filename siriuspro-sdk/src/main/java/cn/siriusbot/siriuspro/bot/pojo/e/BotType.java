package cn.siriusbot.siriuspro.bot.pojo.e;

public enum BotType {
    PUBLIC_TYPE(0),
    PRIVATE_TYPE(1);

    int value;

    BotType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

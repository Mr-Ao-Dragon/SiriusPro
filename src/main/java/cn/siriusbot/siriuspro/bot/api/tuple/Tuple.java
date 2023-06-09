package cn.siriusbot.siriuspro.bot.api.tuple;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Tuple<K, V>{

    private K first;
    private V second;

    public Tuple(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public Tuple() {
    }
}

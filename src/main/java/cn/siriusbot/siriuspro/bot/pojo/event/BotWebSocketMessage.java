package cn.siriusbot.siriuspro.bot.pojo.event;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BotWebSocketMessage implements BotEventBody {
    @NonNull
    Integer op;

    @NonNull
    String message;

    @NonNull
    JSONObject body;
}

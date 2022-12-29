package cn.siriusbot.siriuspro.bot;

import cn.siriusbot.siriuspro.entity.api.AnnouncesApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SiriusBotApi implements BotApi{

    @Autowired
    AnnouncesApi announcesApi;

    /**
     * @return
     */
    @Override
    public AnnouncesApi announcesApi() {
        return announcesApi;
    }
}

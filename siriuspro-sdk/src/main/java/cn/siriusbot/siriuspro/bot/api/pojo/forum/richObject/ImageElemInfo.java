package cn.siriusbot.siriuspro.bot.api.pojo.forum.richObject;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ImageElemInfo {
    String url;

    Integer width;

    Integer height;

    String image_id;
}

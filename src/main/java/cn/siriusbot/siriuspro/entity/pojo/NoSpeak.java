package cn.siriusbot.siriuspro.entity.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Accessors(chain = true)
public class NoSpeak{

    /**
     * 禁言到期时间戳
     */
    private String mute_end_timestamp;

    /**
     * 禁言秒数
     */
    private String seconds;

    /**
     * 禁言人员列表
     */
    private List<String> user_ids;



}

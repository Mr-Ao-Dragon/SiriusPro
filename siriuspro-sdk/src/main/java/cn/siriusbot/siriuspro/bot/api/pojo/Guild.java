package cn.siriusbot.siriuspro.bot.api.pojo;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Guild  {

    /**
     * 频道ID
     */

    private String id;

    /**
     * 频道名称
     */
    private String name;

    /**
     * 频道图标地址
     */
    private String icon;

    /**
     * 创建人ID
     */
    private String owner_id;

    /**
     * 是否为创建人
     */
    private boolean owner;

    /**
     * 频道人数
     */
    private int member_count;

    /**
     * 频道人数上限
     */
    private int max_member;

    /**
     * 频道介绍
     */
    private String description;

    /**
     * 加入时间
     */
    private String joined_at;



}

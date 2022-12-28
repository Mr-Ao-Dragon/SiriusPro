package cn.siriusbot.siriuspro.entity.pojo.member;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页查询成员对象
 */
@Data
@Accessors(chain = true)
public class MemberQueryLimit{
    /**
     * 当前页成员列表
     */
    private List<Member> data;
    /**
     * 下一次请求的翻页标识
     */
    private String next;
}
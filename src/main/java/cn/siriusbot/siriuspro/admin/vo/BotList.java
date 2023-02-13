package cn.siriusbot.siriuspro.admin.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class BotList {

    @Data
    @Accessors(chain = true)
    public static class BotData{
        String avatar;  // 头像
        int callNo;
        String createdAt;   // 创建时间
        String desc;    // 昵称
        boolean disabled;
        String href;
        int key;    // 排序
        String name;    // botId
        String owner;   // 机器人昵称
        int progress;
        int server; // 0 正式 1 沙箱
        int status; // 0 未登录 1 登录中 2 运行中 3 异常
        int type;   // 0 公域 1 私域
        String updatedAt;
    }

    int current;
    String pageSize;
    boolean success;
    int total;
    List<BotData> data;
}

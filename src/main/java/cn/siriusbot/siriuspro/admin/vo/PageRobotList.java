package cn.siriusbot.siriuspro.admin.vo;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageRobotList {
    List<Robot> robots;
    int count;
}

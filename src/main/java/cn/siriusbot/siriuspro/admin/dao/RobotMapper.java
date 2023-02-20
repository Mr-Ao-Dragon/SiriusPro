package cn.siriusbot.siriuspro.admin.dao;

import cn.siriusbot.siriuspro.admin.entity.Robot;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotMapper extends BaseMapper<Robot> {

    @Select("select count(*) from `robot`")
    int queryAllCount();
}

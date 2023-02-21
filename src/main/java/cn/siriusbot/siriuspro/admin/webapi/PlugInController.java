package cn.siriusbot.siriuspro.admin.webapi;

import cn.siriusbot.siriuspro.config.aop.PowerInterceptor;
import cn.siriusbot.siriuspro.config.bean.StatisticsPool;
import cn.siriusbot.siriuspro.web.R.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PowerInterceptor(power = 0)
@RequestMapping("/api/plugin-manage")
public class PlugInController {

    @Autowired
    StatisticsPool statisticsPool;

    /**
     * 获取统计信息
     */
    @RequestMapping("/statistics")
    public R getStatistics(){
        return new R()
                .setCode(0)
                .setData(statisticsPool.getStatisticsData());
    }
}

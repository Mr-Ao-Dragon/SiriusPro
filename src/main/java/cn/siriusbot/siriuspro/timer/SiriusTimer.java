package cn.siriusbot.siriuspro.timer;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class SiriusTimer extends Timer {
    private RUN_STATE state = RUN_STATE.DEFAULT;
    private TimerTask task;
    private long runInterval;

    public SiriusTimer(TimerTask task){
        this.task = task;
    }

    /**
     * 执行定时任务
     * @param runTime 运行时间
     * @param interval 运行间隔
     */
    public void start(Date runTime, long interval){
        this.runInterval = interval;
        schedule(task,runTime,this.runInterval);
    }

    /**
     * 取消当前所有计划任务
     */
    public void pause(){
        task.cancel();
        setState(RUN_STATE.STOP);
    }

    /**
     * 恢复任务
     */
    public void resume(Date runTime, TimerTask task){
        this.task = task;
        schedule(task,runTime,this.runInterval);
    }

    public static enum RUN_STATE{
        RUNNING,STOP,DEFAULT
    }
}

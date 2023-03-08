package cn.siriusbot.siriuspro.web.websocket.messagequeue;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

@Component
@Log4j2
public class MsgQueue {
    Queue<ClientTask> tasks = new ConcurrentLinkedQueue<>();

    int resourceNumber = 0;

    Executor executor;

    public MsgQueue() {
    }

    public MsgQueue(Executor executor) {
        this.executor = executor;
    }

    /**
     * 推入任务
     */
    public void push(ClientTask task) {
        tasks.add(task);
        synchronized (MsgQueue.class) {
            if (resourceNumber == 0) {
                resourceNumber++;
                if (this.executor == null){
                    // 注解启动
                    this.startTask();
                } else {
                    // 手动启动
                    this.executor.execute(MsgQueue.this::startTask);
                }

            }
        }
    }

    /**
     * 启动任务，如果任务线程存在，则不启动
     */
    @Async
    void startTask() {
        // 消费直到队列为空
        while (!tasks.isEmpty()) {
            ClientTask poll = tasks.poll();
            try {
                poll.getObserver().sendMsg(poll.getMsg());  // 发送数据
            } catch (Exception e) {
                log.error(e);
                poll.setRetry(poll.getRetry() + 1);
                if (poll.getRetry() < 6){
                    // 最多重发5次
                    this.push(poll);
                }

            }
        }
        synchronized (MsgQueue.class) {
            resourceNumber--;
        }
    }

}

package cn.siriusbot.siriuspro.websocket.messagequeue;

import cn.siriusbot.siriuspro.entity.temp.Tuple;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class ClientSubject {
    List<ClientObserver> list = new ArrayList<>();

    public void add(ClientObserver clientObserver){
        list.add(clientObserver);
    }

    public void remove(ClientObserver clientObserver){
        list.remove(clientObserver);
    }

    /**
     * 通知所有订阅者
     * @param s 发送内容
     */
    public void sendAllMsg(String s){
        for (ClientObserver observer : list){
            try {
                observer.sendMsg(s);
            } catch (Exception e) {
                log.error(e);
            }
        }
    }

    public ClientTask[] getTaskList(String s){
        ClientTask[] temp = new ClientTask[list.size()];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = new ClientTask()
                    .setObserver(list.get(i))
                    .setMsg(s);
        }
        return temp;
    }
}

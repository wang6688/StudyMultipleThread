package _6ConcurrentCollection._6_5useThreadSafeListWithDelayElement;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

public class Task implements Runnable {
    private int id;
    private DelayQueue<Event> queue;

    public Task(int id, DelayQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    /**
     * 计算要创建的event对象的激活日期，用实际日期加上对应的当前Task编号id对应的秒数。
     */
    @Override
    public void run() {
        Date now = new Date();
        Date delay = new Date();
        delay.setTime(now.getTime()+(id*1000));
        System.out.printf("Thread %s: %s\n",id,delay);

        for ( int i=0; i<100;i++){
            Event event = new Event(delay);
            queue.add(event);
        }

    }
}

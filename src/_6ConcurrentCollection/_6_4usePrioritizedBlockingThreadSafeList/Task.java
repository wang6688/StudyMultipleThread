package _6ConcurrentCollection._6_4usePrioritizedBlockingThreadSafeList;

import java.util.concurrent.PriorityBlockingQueue;

public class Task implements Runnable {
    private int id;
    private PriorityBlockingQueue queue;

    public Task(int id, PriorityBlockingQueue queue) {
        this.id = id;
        this.queue = queue;
    }

    /**
     * 向队列中添加1000个event对象。使用task对象的id属性和自增数字作为传入参数创建每一个event对象，
     * 其中 自增数字是用来设定优先级的。调用add()方法将每个event 加入到队列中。
     */
    @Override
    public void run() {
        for (int i = 0; i<1000;i++){
            Event event = new Event(id,i);
            queue.add(event);
        }
    }
}

package _6ConcurrentCollection._6_5useThreadSafeListWithDelayElement;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Event> queue = new DelayQueue<>();
        Thread threads[] = new Thread[5];

        for (int i= 0; i<threads.length;i++){
            Task task = new Task(i+1,queue);
            threads[i] = new Thread(task);

        }
        for (int i = 0;i<threads.length;i++){
            threads[i].start();
        }
        //使用join方法等待所有线程结束
        for (int i = 0; i<threads.length;i++){
            threads[i].join();
        }

        //把存放在队列中的event对象输出到控制台。当队列长度大于0时，使用poll方法获得
        //一个Event类。如果返回null，则使当前线程休眠500毫秒以等待更多event对象被激活。
        do {
            int counter = 0;
            Event event;
            do {
                event = queue.poll();
                if(event!=null) {
                    counter++;
                }
            }while (event!=null);
            System.out.printf("At %s you have read %d events \n",new Date(),counter);
            TimeUnit.MILLISECONDS.sleep(500);
        }while (queue.size()>0);
    }
}

package _6ConcurrentCollection._6_4usePrioritizedBlockingThreadSafeList;

import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    public static void main(String[] args) {
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();

        Thread taskThreads[] = new Thread[5]; //存放将执行的5个任务的线程。

        for (int i =0; i<taskThreads.length;i++){
            Task task = new Task(i,queue);
            taskThreads[i] = new Thread(task);
        }

        for (int i = 0;i<taskThreads.length;i++){
            taskThreads[i].start();
        }

        //使用join()等待5个线程结束
        for (int i = 0 ;i<taskThreads.length;i++){
            try {
                taskThreads[i].join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        //在控制台输出队列的实际大小和里面存放的events。使用poll()方法从队列中取出events。
        System.out.printf("Main: Queue Size: %d\n",queue.size());
        for (int i =0; i<taskThreads.length*1000;i++){
            Event event = queue.poll();
            System.out.printf("Thread %s : Priority %d \n", event.getThread(),event.getPriority());
        }

        System.out.printf("Main: Queue Size: %d\n",queue.size());
        System.out.printf("Main: End of the program\n");
    }
}

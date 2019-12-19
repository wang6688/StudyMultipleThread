package _1ThreadManagement._1_8createAndRunGuardThread;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

public class WriterTask implements Runnable {
    private Deque<Event> deque;

    public WriterTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i =1; i<100; i++){
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent(String.format("The thread %s has generated an event",Thread.currentThread().getId()));
            deque.addFirst(event);
           // System.out.println("线程"+Thread.currentThread().getId()+"于"+System.currentTimeMillis()+"向队列中加入一个事件");
            try{
                TimeUnit.SECONDS.sleep(1);
             //   System.out.println("休息了一秒");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

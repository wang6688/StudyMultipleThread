package _1ThreadManagement._1_10useThreadLocalVariables;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *  每个线程有一个不同的开始时间，但是当他们结束时，三个线程都有相同的stratDate属性值。
 */
public class UnsafeTask implements  Runnable {
    private Date startDate;

    @Override
    public void run() {
        startDate = new Date();
        System.out.printf("Starting Thread : %s : %s\n",Thread.currentThread().getId(),startDate);
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s\n",Thread.currentThread().getId(),startDate);
    }
}

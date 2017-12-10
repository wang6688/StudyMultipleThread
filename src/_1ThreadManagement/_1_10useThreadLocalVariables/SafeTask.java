package _1ThreadManagement._1_10useThreadLocalVariables;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *  线程局部变量分别为每个线程存储了各自的属性值，并提供给每个线程使用。你可以
 *  使用get()方法读取这个值，并用set()方法存储这个值。如果线程是第一次访问线程局部变量，
 *  线程局部变量可能还没有为它存储值，这个时候initialValue()方法就会被调用，并且返回当前的时间值。
 */
public class SafeTask implements  Runnable {
    private static ThreadLocal<Date> startDate = new ThreadLocal<Date>(){
        @Override
        protected Date initialValue() {
            return new Date();
        }
    };

    @Override
    public void run() {
        System.out.printf("Starting Thread: %s :%s\n",Thread.currentThread().getId(),startDate.get());
        try {
            TimeUnit.SECONDS.sleep((int)Math.rint(Math.random()*10));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Thread Finished: %s : %s\n",Thread.currentThread().getId(),startDate.get());
    }




}

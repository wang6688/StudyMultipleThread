package _2ThreadSynchronizedBasis._2_7modifyFairnessOfLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 非公平模式时，将传入锁构造器的参数设置为false。执行情况是:
 *  所有线程都是按顺序创建的，每个线程都执行两个被锁保护的代码块。
 *  然而访问时线程并没有按照创建的先后顺序。如同前面解释的，锁将选择任一个线程并让它访问锁保护的代码。
 *  JVM没有对线程的执行顺序提供保障。
 */
public class PrintQueue {
    //修改锁对象的构造方法。
    private final Lock queueLock = new ReentrantLock(false);

    //打印方法
    public  void printJob(Object document){
        queueLock.lock();
        try {
            Long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+": " +
                    "first PrintQueue: Printing a job during "+(duration/1000)+"seconds");
            Thread.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
        queueLock.lock();
        try {
            Long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+":"
            +"second PrintQueue : Printing a Job during "+(duration/1000)+"seconds");
            Thread.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
    }
}

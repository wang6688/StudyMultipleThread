package _2ThreadSynchronizedBasis._2_4useConditionInSynchronizedCode;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据存储类
 */
public class EventStorage {
    private int maxSize;
    private List<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<Date>();
    }

    /**
     * 实现同步方法set(),它保存数据到存储列表storage中。 首先，它将检查列表是不是满的，
     * 如果已满，就调用wait()方法挂起线程并等待空余时间的出现。在这个方法的最后，我们调用
     * notifyAll()方法唤醒所有因调用wait()方法进入休眠的线程。
     */
    public synchronized void set(){
        while (storage.size()==maxSize){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        storage.add(new Date());
        System.out.printf("Set: %d\n",storage.size());
        notifyAll();
    }

    /**
     * 实现同步方法get(),它从存储列表中获取数据。首先，它将检查列表中是不是有数据，如果没有，
     * 就调用wait()方法 挂起线程并等待列表中数据的出现。
     * 在这个方法的最后，我们调用notifyAll()方法唤醒所有因调用wait()方法进入休眠的线程。
     */
    public synchronized void get(){
        while (storage.size()==0){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("Get : %d : %s\n",storage.size(),((LinkedList<?>)storage).poll());
        notifyAll();
    }
}

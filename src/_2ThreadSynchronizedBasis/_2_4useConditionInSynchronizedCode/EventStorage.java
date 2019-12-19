package _2ThreadSynchronizedBasis._2_4useConditionInSynchronizedCode;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 数据存储类
 * 这个范例 的主要部分是数据存储EventStorage类的set()和get()方法。 首先，
 */
public class EventStorage {
    private int maxSize;
    private List<Date> storage;

    public EventStorage() {
        maxSize = 10;
        storage = new LinkedList<Date>();
    }

    /**
     * *  set()方法检查存储列表storage是否还有空间，如果已经满了，就调用wait()方法挂起线程并等待空余空间的出现。
     *  *  其次，当其他线程调用notifyAll()方法时，挂起的线程将被唤醒并且再次检查这个条件。notifyAll()并不保证哪个线程会被唤醒。
     *  *  这个过程持续进行直到存储列表有空余空间出现，然后生一个生产者将生成一个新的数据并且存入存储列表storage。
     *
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
     *  *  get()方法的行为与之类似。首先，get()方法检查存储列表storage是否还有数据，如果没有，
     *  *  就调用wait()方法挂起线程并等待数据的出现。其次，当其他线程调用notifyAll()方法时，挂起的线程将被唤醒并且再次检查这个条件。
     *  *  这个过程持续进行直到存储列表有数据出现。
     *  *  备注: 必须在while循环中调用wait(),并且不断查询while的条件，直到条件为真的时候才能继续。
     *
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

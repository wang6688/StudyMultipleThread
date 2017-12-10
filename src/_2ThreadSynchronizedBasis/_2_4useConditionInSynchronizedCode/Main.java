package _2ThreadSynchronizedBasis._2_4useConditionInSynchronizedCode;

/**
 *  这个范例 的主要部分是数据存储EventStorage类的set()和get()方法。 首先，
 *  set()方法检查存储列表storage是否还有空间，如果已经满了，就调用wait()方法挂起线程并等待空余空间的出现。
 *  其次，当其他线程调用notifyAll()方法时，挂起的线程将被唤醒并且再次检查这个条件。notifyAll()并不保证哪个线程会被唤醒。
 *  这个过程持续进行直到存储列表有空余空间出现，然后生一个生产者将生成一个新的数据并且存入存储列表storage。
 *
 *  get()方法的行为与之类似。首先，get()方法检查存储列表storage是否还有数据，如果没有，
 *  就调用wait()方法挂起线程并等待数据的出现。其次，当其他线程调用notifyAll()方法时，挂起的线程将被唤醒并且再次检查这个条件。
 *  这个过程持续进行直到存储列表有数据出现。
 *  备注: 必须在while循环中调用wait(),并且不断查询while的条件，直到条件为真的时候才能继续。
 */
public class Main {
    public static void main(String[] args) {
        // 创建 一个数据存储EventStorage对象。
        EventStorage storage = new EventStorage();
        //创建一个 生产者 Producer 对象，并用它作为传入参数创建一个线程。
        Producer producer = new Producer(storage);
        Thread thread1 = new Thread(producer);
        //创建 一个消费者 Consumer对象，并用它作为传入参数创建一个线程。
        Consumer consumer = new Consumer(storage);
        Thread thread2 = new Thread(consumer);

        thread2.start();
        thread1.start();
    }
}

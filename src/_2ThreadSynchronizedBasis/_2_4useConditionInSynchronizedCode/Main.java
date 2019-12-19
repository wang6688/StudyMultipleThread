package _2ThreadSynchronizedBasis._2_4useConditionInSynchronizedCode;

/**
 *  Java 在Object类中提供了wait()/notify()和notifyAll()方法。线程可以在同步代码块中调用wait()方法。
 *  如果在同步代码块之外调用wait()方法，JVM将抛出IllegalMonitorStateException异常。
 *  当一个线程调用wait()方法时，JVM将这个线程置入休眠，并且释放控制这个同步代码块的对象，同时允许其他线程执行这个对象控制的其他同步代码块。
 *  为了唤醒这个线程，必须在这个对象控制的某个同步代码块中调用notify()或者notifyAll()方法。

 *

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

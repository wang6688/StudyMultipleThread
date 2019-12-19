package _2ThreadSynchronizedBasis._2_8useMultipleConditionInLock;


/**
 * 一个锁可能关联一个或者多个条件，这些条件通过Condition接口声明。
 * 目的是允许线程获取锁并且哈看等待的某一个条件是否满足，如果不满足就挂起直到某个线程唤醒它们。
 * Condtion接口提供了挂起线程和唤起线程的机制。
 */
public class Main {
    public static void main(String[] args) {
        FileMock mock = new FileMock(100,10);
        Buffer buffer = new Buffer(20);

        Producer producer = new Producer(mock,buffer);
        Thread threadProducer = new Thread(producer,"Producer");

        Consumer consumers[] = new Consumer[3];
        Thread threadConsumers[] = new Thread[3];
        for (int i =0; i<3;i++){
            consumers[i] = new Consumer(buffer);
            threadConsumers[i] = new Thread(consumers[i],"Consumer "+i);
        }

        threadProducer.start();
        for (int i =0; i<3;i++){
            threadConsumers[i].start();
        }
    }
}

package _3ResourceSynchronizedAuxiliaryClass._3_8concurrentDataExchangeBetweenTasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Main {
    public static void main(String[] args) {
        //创建两个buffer列表，一个用于生产者，另一个用于消费者
        List<String > buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();
        //创建Exchanger对象，它用来同步生产者和消费者。
        Exchanger<List<String>> exchanger = new Exchanger<>();
        //创建生产者Producer对象和消费者对象。
        Producer producer = new Producer(buffer1,exchanger);
        Consumer consumer = new Consumer(buffer2,exchanger);
        //分别将生产者消费者作为传入参数创建线程，并且启动线程。
        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
        threadProducer.start();
        threadConsumer.start();
    }
}

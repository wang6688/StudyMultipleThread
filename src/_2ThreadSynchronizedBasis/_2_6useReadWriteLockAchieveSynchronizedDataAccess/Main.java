package _2ThreadSynchronizedBasis._2_6useReadWriteLockAchieveSynchronizedDataAccess;

/**
 * 锁机制最大的改进之一就是ReadWriteLock接口和它的唯一实现类ReentrantReadWriteLock。这个类有两个锁，一个是读操作锁，另一个是写操作锁。
 * 使用读操作锁时可以允许多个线程同时访问，但是使用写操作锁时只允许一个线程进行。在一个线程执行写操作时，其他线程不能够执行读操作。
 * 当你获取Lock接口的读锁时，不可以进行修改操作，否则将引起数据不一致的错误。
 */
public class Main {
    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();

        //创建5个读取类Reader对象，并把每一个对象作为传入参数创建线程。
        Reader readers[] = new Reader[5];
        Thread threadsReader[] = new Thread[5];
        for (int i =0; i<5; i++){
            readers[i] = new Reader(pricesInfo);
            threadsReader[i] = new Thread(readers[i]);
        }

        //创建一个写入类Writer对象，并把它作为传入参数创建线程。
        Writer writer = new Writer(pricesInfo);
        Thread threadWriter = new Thread(writer);

        //启动这6个线程。
        for (int i =0; i<5; i++){
            threadsReader[i].start();
        }
        threadWriter.start();
    }
}

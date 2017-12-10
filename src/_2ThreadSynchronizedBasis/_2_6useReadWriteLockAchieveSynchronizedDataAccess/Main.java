package _2ThreadSynchronizedBasis._2_6useReadWriteLockAchieveSynchronizedDataAccess;

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

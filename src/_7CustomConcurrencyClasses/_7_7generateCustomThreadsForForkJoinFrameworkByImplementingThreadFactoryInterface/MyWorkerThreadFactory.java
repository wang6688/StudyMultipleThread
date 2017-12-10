package _7CustomConcurrencyClasses._7_7generateCustomThreadsForForkJoinFrameworkByImplementingThreadFactoryInterface;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;

public class MyWorkerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {
    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        return new MyWorkerTHread(pool);
    }


}

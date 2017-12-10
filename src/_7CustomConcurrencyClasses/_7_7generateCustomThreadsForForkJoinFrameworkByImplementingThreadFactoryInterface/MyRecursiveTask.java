package _7CustomConcurrencyClasses._7_7generateCustomThreadsForForkJoinFrameworkByImplementingThreadFactoryInterface;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class MyRecursiveTask extends RecursiveTask<Integer>  {
    private int array[];

    private int start,end;

    public MyRecursiveTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
       Integer ret=0;
       MyWorkerTHread thread = (MyWorkerTHread) Thread.currentThread();
       thread.addTask();
       return ret;
    }

    private Integer addResult(MyRecursiveTask task1,MyRecursiveTask task2){
        int value;
        try {
            value = task1.get().intValue()+task2.get().intValue();
        }catch (InterruptedException e){
            e.printStackTrace();
            value = 0;
        }catch ( ExecutionException e){
            e.printStackTrace();
            value = 0;
        }

        try {
            TimeUnit.MILLISECONDS.sleep(10);

        }catch ( InterruptedException e){
            e.printStackTrace();
        }
        return value;
    }
}

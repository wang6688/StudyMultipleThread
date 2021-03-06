package _5ForkAndJoinFramework._5_3mergeResultOfTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        //创建Document对象，包含100行，每行1000个词。
        DocumentMock mock = new DocumentMock();
        String [][] document = mock.generateDocument(100,1000,"the");
        //创建一个DocumentTask对象，用来更新整个文档。传递数字0给参数start，以及数字100给参数end。
        DocumentTask task = new DocumentTask(document,0,100,"the");

        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);

        do {
            System.out.printf("******************************************************\n");
            System.out.printf("Main: Parallelism: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Active Threads: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n",pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n",pool.getStealCount());
            System.out.printf("****************************************************\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }while (!task.isDone());

        pool.shutdown();

        try {
            pool.awaitTermination(1,TimeUnit.DAYS);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        try {
            System.out.printf("Main: The word appears %d in the document", task.get());
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
    }
}

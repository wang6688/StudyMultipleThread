package _1ThreadManagement._1_11threadGroup;

import java.util.concurrent.TimeUnit;

/**
 * 线程组类存储了线程对象和关联的线程组对象，并可以访问他们的信息(例如状态),将执行的操作应用到所有成员上(例如中断).
 */
public class Main {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        Result result = new Result();
        SearchTask searchTask = new SearchTask(result);
        for (int i =0; i<5; i++){
            Thread thread = new Thread(threadGroup,searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //通过activeCount()方法获取线程组包含的线程数目，通过enumerate()方法获取线程组包含的线程列表。
        //这两个方法可以帮助我们获取每个线程的信息，如线程的状态。
        System.out.printf("Number of Threads: %d\n",threadGroup.activeCount());
        System.out.printf("Information about the Thread Group\n");
        threadGroup.list();

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i =0; i<threadGroup.activeCount();i++){
            System.out.printf("Thread %s: %s\n",threads[i].getName(),threads[i].getState());
        }

        //调用waitFinish()方法，我们将在下面实现这个方法。它将等到线程组的第一个线程运行结束。
        waitFinish(threadGroup);
        //使用interrupt()方法中断这个组中的其余线程。
        threadGroup.interrupt();

    }

    //实现waitFinish()方法。 activeCount()方法被用来检测是否有线程运行结束。
    private static  void waitFinish(ThreadGroup threadGroup){
        while (threadGroup.activeCount()>9){
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

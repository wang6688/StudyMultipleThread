package _4ThreadExecutor._4_7delayPerformTasksInActuator;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // 通过Executors工厂类的newScheduledThreadPool()方法创建一个ScheduledThreadPoolExecutor执行器，并传递1作为参数。
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        //初始化一些任务，在这里 是5个，然后通过ScheduledThreadPoolExecutor实例的schedule()方法来启动这些任务。
        System.out.printf("Main: Starting at: %s\n",new Date());
        for (int i =0;i <5;i++){
            Task task = new Task("Task "+i);
            executor.schedule(task,i+1, TimeUnit.SECONDS);

        }
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);

        }catch (InterruptedException e){
            e.printStackTrace();

        }

        System.out.printf("Main: Ends at: %s\n",new Date());

    }
}

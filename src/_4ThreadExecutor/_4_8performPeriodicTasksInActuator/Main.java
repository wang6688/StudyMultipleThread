package _4ThreadExecutor._4_8performPeriodicTasksInActuator;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 该任务每2秒执行一次；剩余的延迟时间会每隔500毫秒在控制台上输出，这个500毫秒则是主线程将被休眠的时间。
 * 当关闭执行器时，定时任务将结束执行，然后在控制台上也看不到更多的信息了。
 */
public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s\n",new Date());

        Task task = new Task("Task");
        //调用scheduledAtFixRate()方法将这个任务  发送给执行器。传递给这个方法的参数分别为上一步创建的task对象、数字1、数字2、
        //以及TimeUnit.SECONDS常量。这个方法返回一个用来控制状态的ScheduledFuture对象
        ScheduledFuture<?> result = executor.scheduleAtFixedRate( task,1,2, TimeUnit.SECONDS);

        for (int i =0; i<10; i++){
            System.out.printf("Main: Delay : %d\n",result.getDelay(TimeUnit.MILLISECONDS));
           //
            // Sleep the thread during 500 milliseconds
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            executor.shutdown();

            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.printf("Main: Finished at: %s\n",new Date());

        }
    }
}

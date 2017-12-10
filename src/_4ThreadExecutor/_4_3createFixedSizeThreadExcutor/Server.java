package _4ThreadExecutor._4_3createFixedSizeThreadExcutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server  {
    private ThreadPoolExecutor executor;

    public Server() {
       this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    public void executeTask(Task task){
        System.out.printf("Server : A new task has arrived\n");
        System.out.printf("Server: Task Count: %d\n",executor.getTaskCount());
        executor.execute(task);
        System.out.printf("Server: Pool Size: %d\n",executor.getPoolSize());
        System.out.printf("Server : Active Count:  %d\n", executor.getActiveCount());
        System.out.printf("Server : Completed Tasks: %d\n",executor.getCompletedTaskCount());

    }

    public void endServer(){
        executor.shutdown();
    }

}

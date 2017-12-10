package _4ThreadExecutor._4_5runMultipleTasksAndProcessFirstResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        String username = "test";
        String pasword = "test";
        UserValidator ldapValidator = new UserValidator("LDAP");
        UserValidator dbValidator = new UserValidator("DataBase");
        TaskValidator ldapTask = new TaskValidator(ldapValidator,username,pasword);
        TaskValidator dbTask = new TaskValidator(dbValidator,username,pasword);

        List<TaskValidator> taskList = new ArrayList<>();
        taskList.add(ldapTask);
        taskList.add(dbTask);
        //通过Executors的工厂类的newCachedThreadPool()方法创建一个新的 ThreadPoolExecutor执行器对象，并创建一个名为result的String对象。
        ExecutorService executor= Executors.newCachedThreadPool();
        String result;

        try {
            result= executor.invokeAny(taskList);
            System.out.printf("Main: Result: %s\n",result);

        }catch (InterruptedException e){
            e.printStackTrace();

        }catch (ExecutionException e){
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.printf("Main: End of the Execution\n");
    }

}

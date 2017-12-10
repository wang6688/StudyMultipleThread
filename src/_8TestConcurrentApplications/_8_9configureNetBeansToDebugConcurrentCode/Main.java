package _8TestConcurrentApplications._8_9configureNetBeansToDebugConcurrentCode;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
         Lock lock1,lock2;

         lock1 = new ReentrantLock();
         lock2 = new ReentrantLock();

         Task1 task1 = new Task1(lock1,lock2);
         Task2 task2 = new Task2(lock1,lock2);

         Thread thread1 = new Thread(task1);
         Thread thread2 = new Thread(task2);

         thread1.start();
         thread2.start();

         while ((thread1.isAlive())&& (thread2.isAlive())){
             System.out.printf("Main: The example is "+ " running");
             try {
                 TimeUnit.MILLISECONDS.sleep(500);
             }catch (InterruptedException e){
                 e.printStackTrace();
             }
         }
    }
}

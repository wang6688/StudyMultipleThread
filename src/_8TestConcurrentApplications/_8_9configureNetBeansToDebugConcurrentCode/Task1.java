package _8TestConcurrentApplications._8_9configureNetBeansToDebugConcurrentCode;

import java.util.concurrent.locks.Lock;

public class Task1 implements  Runnable {

    private Lock lock1,lock2;

    public Task1(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }



    /*@Override
    public void run() {
        lock1.lock();
        System.out.printf("Task 1: Lock1 locked\n");

        lock2.lock();
        System.out.printf("Task1 : Lock 2 locked\n");
        lock2.unlock();
        lock1.unlock();
    }*/


    @Override
    public void run() {
        synchronized (lock1){
            System.out.printf("Task 1: Lock 1 locked \n");
            synchronized (lock2){
                System.out.printf("Task 1: Lock 2 locked \n");
            }
        }
    }
}

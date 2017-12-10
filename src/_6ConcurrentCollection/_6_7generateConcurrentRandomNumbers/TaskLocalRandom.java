package _6ConcurrentCollection._6_7generateConcurrentRandomNumbers;

import java.util.concurrent.ThreadLocalRandom;

public class TaskLocalRandom implements  Runnable {

    public TaskLocalRandom() {
        ThreadLocalRandom.current();
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i =0;i<10;i++){
            System.out.printf("%s: %d\n",name,ThreadLocalRandom.current().nextInt(10));

        }
    }
}

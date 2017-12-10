package _6ConcurrentCollection._6_9useAnArrayOfAtoms;

import org.omg.CORBA.INTERNAL;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Main {
    public static void main(String[] args) {
        final int THREADS = 100;
        AtomicIntegerArray vector = new AtomicIntegerArray(1000);

        Incrementer incrementer = new Incrementer(vector);

        Decrementer decrementer = new Decrementer(vector);

        Thread threadIncrementer[] = new Thread[THREADS];
        Thread threadDecrementer[] = new Thread[THREADS];

        for (int i =0; i<THREADS; i++){
            threadIncrementer[i] = new Thread(incrementer);
            threadDecrementer[i] = new Thread(decrementer);

            threadIncrementer[i].start();
            threadDecrementer[i].start();
        }

        for (int i =0; i<100;i++){
            try {
                threadIncrementer[i].join();
                threadDecrementer[i].join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        for ( int i = 0; i<vector.length();i++){
            if (vector.get(i)!=0){
                System.out.printf("Vector["+i+"] : "+vector.get(i));

            }
        }
        System.out.printf(" Main: End of the example");
    }
}

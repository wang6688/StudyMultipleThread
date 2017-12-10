package _2ThreadSynchronizedBasis._2_5useLockAchieveSynchronization;

/**
 *  这个范例的主要部分是打印队列类Printqueue中的printJob()方法。我们使用锁实现一个临界区，
 *  并且保证同一时间只有一个执行线程访问这个临界区时，必须创建ReentrantLock对象。
 *  在这个临界区的开始，必须通过lock()方法获取对锁的控制。当线程A访问这个方法时，如果没有其他线程获取对这个锁的控制，
 *  lock()方法将让线程A获得锁并且立刻执行临界区的代码，否则，如果其他线程B正在执行这个锁保护的临界区代码，
 *  lock()方法将让线程休眠直到线程B执行完临界区的代码。
 *
 *  在线程离开临界区的时候，我们必须使用unlock()方法来释放它持有的锁，以让其他线程来访问临界区。
 *  如果在离开临界区的时候没有调了用unlock()方法，其他线程将永久的 等待，从而导致了死锁情景。
 *  若果在临界区使用了try-catch块，不要忘记将unlock()方法让如finally部分。
 */
public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread thread[] = new Thread[10];
        for (int i =0; i <10; i++){
            thread[i] = new Thread(new Job(printQueue),"Thread "+i);
        }
        for (int i = 0 ; i < 10; i++){
            thread[i].start();
        }
    }
}

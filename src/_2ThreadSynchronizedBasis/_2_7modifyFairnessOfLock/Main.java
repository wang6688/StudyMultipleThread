package _2ThreadSynchronizedBasis._2_7modifyFairnessOfLock;

/**
 * 所有线程创建的间隔是0.1秒。第一个线程请求锁是线程0，然后是线程1，以此类推。
 * 当线程0 执行第一个加锁的代码块，其余9个线程将等待获取这个锁。当线程0释放了锁，
 * 它又立即请求锁，这个时候就有10个线程试图获取锁。在公平模式下，Lock接口将选择线程1，因为这个线程等待的时间最久，然后。
 * 他选择线程2，然后线程3，以此类推。在所有线程都执行完第一个被锁保护的代码块之前，他们都没有执行第二个被锁保护的代码块。
 * 当所有线程执行完第一个加锁代码块之后，又轮到了线程0，然后是线程1，以此类推。
 *
 *
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
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

package _2ThreadSynchronizedBasis._2_7modifyFairnessOfLock;

/**、ReentrantLock和RenentrantReadWriteLock类的构造器都含有一个布尔参数fair，它允许你控制这两个类的行为。
 * 默认fair值 时false，它称为非公平模式。在非公平模式下，当有很多线程在等待锁时，锁将选择他们中的一个来访问临界区，
 * 这个选择时没有任何约束的。 入股哦是fair值时true，则称指为公平模式。
 * 在公平模式下，当还有很多线程在等待锁时，锁将选择他们中等待时间最长的那个线程来访问临界区。
 * 这两种模式只适用于lock()和unlock()方法。而Lock接口的tryLock()没有将线程置于休眠，fair属性并不影响这个方法。
 *
 *
 *
 *
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

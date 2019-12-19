package _2ThreadSynchronizedBasis._2_5useLockAchieveSynchronization;

/**
 *  这个范例的主要部分是打印队列类Printqueue中的printJob()方法。
 * 基于Lock接口及其实现类(例如ReentrantLock)的同步代码块机制比 synchronized关键字更强大更灵活。
 * 使用synchronized关键字时，只能在同一个synchronized块机构中获取和释放控制。
 * Lock接口允许实现更复杂的临界区结构（即 控制的获取和释放不出现在同一个块结构中）
 *
 * 相比synchronzied关键字，Lock接口提供了更多的功能。 其中一个新功能时tryLock()方法的实现。这个方法试图获取锁，
 * 如果锁已被其他线程获取，它将返回false并继续往下执行代码。
 * 使用synchronized关键字时，如果线程A试图执行一个同步代码块，而线程B已在执行这个同步代码块，则线程A就会被挂起 直到线程B运行完这个同步代码块。
 * 使用锁的tryLock()方法通过返回值将得知是否有其他线程正在使用这个锁的保护的代码块。
 *
 * Lock接口允许分离读和写操作，允许多个读线程和只有一个写线程。
 * 相比synchronized关键字Lock接口具有更好的性能。
 *
 * Lock接口（和它的实现类ReentrantLock）还提供了另一个方法来获取锁，即tryLock()方法。跟lock()方法最大的不同是：
 * 线程使用tryLock()不能获取锁，tryLock()会立即返回，它不会将线程置入休眠。tryLock()方法返回一个布尔值，
 * true表示线程获取了锁，false表示没有获取锁。
 *
 * 编程人员应该重是tryLock（）方法的返回值及其对应的行为。如果这个方法返回false，则程序不会执行临界区代码。
 * 如果执行了，这个应用很可能会出现错误的结果。
 * ReentrantLock类也允许使用递归调用。如果一个线程获取了锁并且而进行了递归调用 它将继续持有这个锁，因此调用lock()方法后也将立即返回，
 * 并且线程将继续执行递归调用。
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

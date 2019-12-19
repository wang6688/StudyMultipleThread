package _2ThreadSynchronizedBasis._2_2useSynchronizedAchieveSynchronizationMethod;

/**
 *  一个对象的方法采用synchronized 关键字进行声明，只能被一个线程访问。如果线程A 正在执行一个同步方法syncMethodA(),线程B要执行这个对象的其他同步方法syncMethodB(),
 *  * 线程B将被阻塞直到线程A访问完。但如果线程B访问的是同一个类的不同对象，那么两个线程都不会被阻塞。
 *
 *
 */
public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 实现addAmount()方法。它会将传入的数量加入到余额balance中，并且在同一时间，只允许一个线程改变这个值，
     * 所以我们使用 synchronized 关键字 将这个方法标记成临界区。
     * @param amount
     */
    public synchronized   void addAmount(double amount){
        double tmp = balance;
        System.out.println("线程："+Thread.currentThread().getName()+"读取出余额为:"+tmp+"并+");
        try {
            Thread.sleep(10);

        }catch (InterruptedException e){
            e.printStackTrace();

        }
        tmp+= amount;
        balance = tmp;
        System.out.println("线程："+Thread.currentThread().getName()+"增加余额后为:"+balance);
    }

    /**
     * 实现 subtractAmount()方法。它将传入的数量从余额中扣除，并且在同一时间只允许一个线程改变这个值，
     * 所以我们使用synchronized 关键字 将这个方法标记成临界区。
     *  @param amount
     */
    public   synchronized   void subtractAmount(double amount){

        double tmp = balance;
        System.out.println("线程："+Thread.currentThread().getName()+"读取出余额为:"+tmp+"并-");

        try {
            Thread.sleep(10);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        tmp-= amount;
        balance = tmp;
        System.out.println("线程："+Thread.currentThread().getName()+"减少余额后为:"+balance);

    }


}

package _2ThreadSynchronizedBasis._2_2useSynchronizedAchieveSynchronizationMethod;

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
    public  synchronized void addAmount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(10);

        }catch (InterruptedException e){
            e.printStackTrace();

        }
        tmp+= amount;
        balance = tmp;
    }

    /**
     * 实现 subtractAmount()方法。它将传入的数量从余额中扣除，并且在同一时间只允许一个线程改变这个值，
     * 所以我们使用synchronized 关键字 将这个方法标记成临界区。
     *  @param amount
     */
    public synchronized   void subtractAmount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(10);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        tmp-= amount;
        balance = tmp;
    }


}

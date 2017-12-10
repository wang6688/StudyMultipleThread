package _2ThreadSynchronizedBasis._2_2useSynchronizedAchieveSynchronizationMethod;

/**
 *  实现公司模拟类Commpany。它使用addAmount()对账户的余额进行充值。这个类实现Runnable 接口
 *  作为线程运行。
 */
public class Company implements Runnable {
    private Account account;

    public Company(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i =0; i<100; i++){
            account.addAmount(1000);
        }
    }
}

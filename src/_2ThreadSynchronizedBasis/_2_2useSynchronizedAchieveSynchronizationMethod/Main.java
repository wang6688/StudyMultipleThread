package _2ThreadSynchronizedBasis._2_2useSynchronizedAchieveSynchronizationMethod;


/**
 * 每个用synchronized 关键字声明的方法都是临界区。在Java中，同一个对象的临界区，在同一时间只有一个允许被访问。
 * 静态方法则有不同的行为。用synchronized关键字声明的静态方法，同时只能够被一个执行线程访问，但是其他线程可以访问这个对象的非静态方法。
 * 必须非常谨慎这一点。因为两个线程可以同时访问一个对象的两个不同的synchronzied 方法，即其中一个是静态方法，另一个是非静态方法。
 * 如果两个方法都改变了相同的数据，将会出现数据不一致的错误。
 *
 * 一个对象的方法采用synchronized 关键字进行声明，只能被一个线程访问。如果线程A 正在执行一个同步方法syncMethodA(),线程B要执行这个对象的其他同步方法syncMethodB(),
 * 线程B将被阻塞直到线程A访问完。但如果线程B访问的是同一个类的不同对象，那么两个线程都不会被阻塞。
 */
public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);
        //创建一个公司类的Company 对象，并用它作为传入参数创建线程。
        Company company = new Company(account);
        Thread companyThread = new Thread(company,"company");
        //创建一个ATM模拟类Bank对象，并用它作为传入参数创建线程。
        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank,"bank");
        //将初始余额打印到控制台，并启动这两个线程。
        System.out.printf("Account： Initial Balance: %f\n",account.getBalance());
        companyThread.start();
        bankThread.start();
        //使用join()方法等待这两个线程运行完成，然后打印账户的最终余额到控制台。
        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account : Final Balance : %f\n",account.getBalance());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

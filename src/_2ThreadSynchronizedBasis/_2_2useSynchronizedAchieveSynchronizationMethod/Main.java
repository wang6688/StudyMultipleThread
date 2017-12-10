package _2ThreadSynchronizedBasis._2_2useSynchronizedAchieveSynchronizationMethod;


public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBalance(1000);
        //创建一个公司类的Company 对象，并用它作为传入参数创建线程。
        Company company = new Company(account);
        Thread companyThread = new Thread(company);
        //创建一个ATM模拟类Bank对象，并用它作为传入参数创建线程。
        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);
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

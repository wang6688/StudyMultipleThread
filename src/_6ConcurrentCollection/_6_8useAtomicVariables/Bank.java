package _6ConcurrentCollection._6_8useAtomicVariables;

public class Bank implements  Runnable {
    private Account account;

    public Bank(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for ( int i =0; i<10; i++){
            account.substractAmount(1000);
        }
    }
}

package _6ConcurrentCollection._6_8useAtomicVariables;

import java.util.concurrent.atomic.AtomicLong;

public class Account {
    private AtomicLong balance;

    public Account() {
        this.balance = new AtomicLong();
    }

    public long getBalance() {
        return balance.get();
    }

    public void setBalance(long balance) {
        this.balance.set(balance);
    }

    public void addAmount(long amount){
        this.balance.getAndAdd(amount);
    }

    public void substractAmount(long amount){
        this.balance.getAndAdd(-amount);
    }
}

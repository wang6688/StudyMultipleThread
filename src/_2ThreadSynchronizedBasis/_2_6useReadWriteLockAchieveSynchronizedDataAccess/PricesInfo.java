package _2ThreadSynchronizedBasis._2_6useReadWriteLockAchieveSynchronizedDataAccess;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//价格信息类
public class PricesInfo {
    private double price1;
    private double price2;

    //声明读写锁ReadWriteLock对象lock
    private ReadWriteLock lock;

    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;
        lock = new ReentrantReadWriteLock();

    }
    //实现getPrice1()方法来返回属性price1的值。 它使用读锁来获取对这个属性的访问。
    public double getPrice1(){
        lock.readLock().lock();
        double value = price1;
        lock.readLock().unlock();
        return value;
    }

    //实现getPrice2()方法来返回属性price2的值。它使用读锁来获取对这个属性的访问。
    public double getPrice2(){
        lock.readLock().lock();
        double value = price2;
        lock.readLock().unlock();
        return value;
    }

    //实现setPrices()方法 为这两个price属性赋值，它使用了写锁来控制对这两个属性的访问。
    public void setPrices(double price1,double price2){
        lock.writeLock().lock();
        this.price1 = price1;
        this.price2 = price2;
        lock.writeLock().unlock();
    }
}

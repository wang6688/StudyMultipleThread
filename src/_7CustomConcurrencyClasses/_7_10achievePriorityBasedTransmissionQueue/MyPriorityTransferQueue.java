package _7CustomConcurrencyClasses._7_10achievePriorityBasedTransmissionQueue;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class MyPriorityTransferQueue extends PriorityBlockingQueue<Event> implements TransferQueue<Event> {
    private AtomicInteger counter;

    private LinkedBlockingQueue<Event> transfered;

    private ReentrantLock lock;

    public MyPriorityTransferQueue() {
        counter = new AtomicInteger(0);
        lock = new ReentrantLock();
        transfered = new LinkedBlockingQueue<Event>();
    }

    @Override
    public boolean tryTransfer(Event e) {
        lock.lock();
        boolean value;
        if (counter.get()==0){
            value = false;
        }else {
            put(e);
            value = true;
        }
        lock.unlock();
        return value;

    }

    @Override
    public void transfer(Event e) throws InterruptedException {
        lock.lock();
        if (counter.get()!=0){
            put(e);
            lock.unlock();
        }else {
            transfered.add(e);
            lock.unlock();
            synchronized (e){
                e.wait();
            }
        }
    }

    @Override
    public boolean tryTransfer(Event e, long timeout, TimeUnit unit) throws InterruptedException {
        lock.lock();
        if (counter.get()!=0){
            put(e);
            lock.unlock();
            return true;
        }else{
            transfered.add(e);
            long newTimeout = TimeUnit.MILLISECONDS.convert(timeout,unit);
            lock.unlock();
            e.wait(newTimeout);
            lock.lock();
            if (transfered.contains(e)){
                transfered.remove(e);
                lock.unlock();
                return false;
            }else {
                lock.unlock();
                return  true;
            }
        }
    }

    @Override
    public boolean hasWaitingConsumer() {
        return (counter.get()!=0);
    }

    @Override
    public int getWaitingConsumerCount() {
        return counter.get();
    }

    @Override
    public Event take() throws InterruptedException {
        lock.lock();
        counter.incrementAndGet();
        Event value = transfered.poll();
        if(value==null){
            lock.unlock();
            value = super.take();
            lock.lock();
        }else {
            synchronized (value){
                value.notify();
            }
        }

        counter.decrementAndGet();
        lock.unlock();
        return value;
    }
}

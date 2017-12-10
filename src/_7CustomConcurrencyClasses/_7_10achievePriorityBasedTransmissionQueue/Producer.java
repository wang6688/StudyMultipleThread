package _7CustomConcurrencyClasses._7_10achievePriorityBasedTransmissionQueue;

public class Producer implements Runnable {
    private MyPriorityTransferQueue buffer;

    public Producer(MyPriorityTransferQueue buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i<100;i++){
            Event event = new Event(Thread.currentThread().getName(),i);
            buffer.put(event);
        }
    }
}

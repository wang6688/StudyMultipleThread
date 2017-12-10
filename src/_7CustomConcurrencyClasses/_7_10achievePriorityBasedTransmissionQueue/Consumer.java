package _7CustomConcurrencyClasses._7_10achievePriorityBasedTransmissionQueue;

public class Consumer implements Runnable {

    private MyPriorityTransferQueue buffer;

    public Consumer(MyPriorityTransferQueue buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i<1002;i++){
            try {
                Event value = buffer.take();
                System.out.printf("Consumer: %s: %d\n",value.getThread(),value.getPriority());

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

package _3ResourceSynchronizedAuxiliaryClass._3_3multCopiesOfResourcesConcurrentAccessControl;

public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        //将工作累Job对象作为传入参数创建10个线程，因而每个线程都将发送文档到打印队列。
        Thread thread [] = new Thread[10];
        for (int i = 0; i<10; i++){
            thread[i] = new Thread(new Job(printQueue),"Thread"+i);
        }
        /*当启动10个线程时，第一个获得信号量的线程将能够访问临界区，其余的线程将被信号量阻塞，直到信号量被释放。
        * 一旦信号量被释放，信号量将选择一个正在等待的线程并且允许它访问临界区，从而所有的工作都将一个接一个地打印它们的文档。*/
        for (int i =0; i<10; i++){
            thread[i].start();
        }
    }
}

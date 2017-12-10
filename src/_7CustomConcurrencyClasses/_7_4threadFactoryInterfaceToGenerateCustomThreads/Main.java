package _7CustomConcurrencyClasses._7_4threadFactoryInterfaceToGenerateCustomThreads;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThreadFactory myFactory = new MyThreadFactory("MyThreadFacotry");
        MyTask task = new MyTask();
        Thread thread = myFactory.newThread(task);

        thread.start();
        thread.join();

        System.out.printf("Main: Thread information.\n");
        System.out.printf("%s\n",thread);
        System.out.printf("Main: End of the example.\n");

    }
}

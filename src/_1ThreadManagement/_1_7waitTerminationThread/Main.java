package _1ThreadManagement._1_7waitTerminationThread;

import java.util.Date;

/**
 * 运行这个程序时，你会看到两个线程对象是如何运行的。DataSourcesLoader线程运行结束，
 * NetworkCOnnnectionsLoader线程也运行结束的时候，主线程对象才会继续运行并且打印出最终的信息。
 */
public class Main {
    public static void main(String[] args) {
        DataSourcesLoader dsLoader = new DataSourcesLoader();
        Thread localThread = new Thread(dsLoader,"DataSourceThread");
        NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
        Thread networkThread = new Thread(ncLoader,"NetworkConnectionLoader");
        networkThread.start();
        localThread.start();

        try{
            //当一个线程对象的join()方法被调用时，调用它的线程（主线程）将被挂起，知道被调用线程对象(DataSourceThread,NetworkConnectionLoader)完成它的任务。
            networkThread.join();
            localThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();

        }
        System.out.printf("Main : Configuration has been loaded:%s\n",new Date());
    }
}

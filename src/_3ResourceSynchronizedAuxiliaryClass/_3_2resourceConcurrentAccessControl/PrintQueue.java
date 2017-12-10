package _3ResourceSynchronizedAuxiliaryClass._3_2resourceConcurrentAccessControl;

import java.util.concurrent.Semaphore;

public class PrintQueue {
    //声明一个对象信号量
    private final Semaphore semaphore;

    public PrintQueue() {
        //初始化 信号量对象，以保护对打印队列的访问。
        semaphore = new Semaphore(1);
    }

    /**
     * 模拟文档的打印。它传入参数是文档对象document。
     * @param document
     */
    public void printJob(Object document){
        try {
            //通过调用acquire（）方法获得信号量，它会抛出InterruptedException异常，
            //然后必须捕获并处理这个异常。
            semaphore.acquire();
            //实现模拟文档的打印，然后等待一段随机时间。
            long duration = (long)(Math.random()*10);
            System.out.printf("%s : PrintQueueueue: Pringting a Job duiring %d seconds\n",Thread.currentThread().getName(),duration);
            Thread.sleep(duration);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {//释放信号量
            semaphore.release();
        }
    }

}

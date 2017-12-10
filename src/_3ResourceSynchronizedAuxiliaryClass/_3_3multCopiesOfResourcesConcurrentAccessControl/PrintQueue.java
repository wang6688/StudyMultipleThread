package _3ResourceSynchronizedAuxiliaryClass._3_3multCopiesOfResourcesConcurrentAccessControl;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
    //声明一个对象信号量
    private final Semaphore semaphore;

    //声明一个boolean型数组freePrinters，用来存放打印机的状态，即空闲或正在打印。
    private  boolean freePrinters[];
    //声明一个锁对象lockPrinters, 用来保护对freePrinters数组的访问。
    private Lock lockPrinters;
    public PrintQueue() {
        semaphore = new Semaphore(3);
        freePrinters = new boolean[3];

        for (int i =0; i<3; i++){
            freePrinters[i] = true;
        }
        lockPrinters = new ReentrantLock();

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
            //使用私有函数getPrinter()获得分配打印工作的打印机编号。
            int assignedPrinter = getPrinter();
            //实现模拟文档的打印，然后等待一段随机时间。
            long duration = (long)(Math.random()*10);
            System.out.printf("%s : PrintQueueueue: Pringting a Job duiring %d seconds\n",Thread.currentThread().getName(),duration);
            Thread.sleep(duration);
            //将打印机标记为空闲，即将这个打印机打印成机对应的freePrinters数组中的值设置成true。
            freePrinters[assignedPrinter]= true;
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {//释放信号量
            semaphore.release();
        }
    }

    private int getPrinter(){
        int ret = -1;
        try {
            //获得锁。
            lockPrinters.lock();
            //在freePrinters数组中找到第一个true值并把索引保存到ref变量中， 将true值重置为false，意味着被定为的打印机将要执行打印工作。
            for (int i =0 ;i<freePrinters.length;i++){
                if (freePrinters[i]){
                    ret = i ;
                    freePrinters[i] = false;
                    break;
                }
            }
            //释放锁对象，并且返回刚刚获得的打印机编号。
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lockPrinters.unlock();
        }
        return ret;
    }
}

package _3ResourceSynchronizedAuxiliaryClass._3_6concurrentPhaseRunTask;

import java.util.concurrent.Phaser;

public class Main {
    public static void main(String[] args) {
        //创建Phaser对象，并制定参与阶段同步的线程是3个。
        Phaser phaser = new Phaser(3);
        //创建3个文件查找类FIleSeach对象，对每一个对象指定不同的查找目录,并且制定查找的事扩展名为.log的文件。
        FileSearch system = new FileSearch("C:\\Windows","log",phaser);
        FileSearch apps = new FileSearch("C:\\Program Files","log",phaser);
        FileSearch documents = new FileSearch("C:\\Documents And Settings","log",phaser);

        //将第一个FileSearch文件查找类对象作为传入参数创建线程，并且启动它。
        Thread systemThread = new Thread(system,"System");
        systemThread.start();
        //将第二个FileSearch文件查找类对象 作为传入参数创建线程，并且启动它。
        Thread appsThread = new Thread(apps,"Apps");
        appsThread.start();
        //将第三个FileSearch文件查找类对象作为传入参数创建线程，并且启动它。
        Thread documentsThread = new Thread(documents,"Documents");
        documentsThread.start();
        //等待三个线程执行结束。
        try {
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //使用Phaser对象的isFInalized()方法，打印出Phaser对象是否已终止。
        System.out.printf("Terminated:"+phaser.isTerminated());
    }

}

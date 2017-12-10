package _1ThreadManagement._1_6threadSleepResume;

import java.util.concurrent.TimeUnit;

public class FileMain {
    public static void main(String[] args) {
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);
        thread.start();
        try{
            //调用TimeUnit类的SECONDS属性的sleep()方法，休眠5秒钟
            TimeUnit.SECONDS.sleep(5);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //设置中断FileClock线程的标志位
        //thread.interrupt();

    }
}

package _3ResourceSynchronizedAuxiliaryClass._3_7concurrentPhaseTaskInStageOfSwitching;

import java.io.PrintStream;
import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable{
    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    /**
     * 模拟考试过程。
     *
     */
    @Override
    public void run() {
        //将一个学生到达考场的信息打印到控制台，并且调用phaser对象的arriveAndAwaitAdvance()方法等待其他的线程。
      System.out.printf("%s: Has arrived to do the exam. %s\n", Thread.currentThread().getName(), new Date());
      phaser.arriveAndDeregister();
      //打印当前学生正在做考题的信息到控制台。并且调用doExercies1()方法模拟做完一道考题的过程，接着打印当前学生做完第一道题的信息到控制台。
        //然后调用phaser的arriveAndAwaitAdvance()方法等待其他学生完成第一道考题。
        System.out.printf("%s : Is going to do the first exercise. %s\n",Thread.currentThread().getName(),new Date());
       doExercise1();
        System.out.printf("%s : Has done the first exercies. %s\n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndDeregister();

        //为第二道题和第三道题实现同样的代码。
        System.out.printf("%s : Is going to do the second exercise. %s\n",Thread.currentThread().getName(),new Date());
        doExercise2();
        System.out.printf("%s : Has done the second exercese. %s\n ",Thread.currentThread().getName(),new Date());


        System.out.printf("%s : Is going to do the third exercise. %s\n",Thread.currentThread().getName(),new Date());
        doExercise3();
        System.out.printf("%s : Has done the third exercese. %s\n ",Thread.currentThread().getName(),new Date());
    }

    /**
     * 辅助方法1，它将线程休眠一段时间。
     */
    private void doExercise1(){
        try {
            long duration = (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);

        }catch (InterruptedException e){
            e.printStackTrace();

        }
    }
    /**
     * 辅助方法2，它将线程休眠一段时间。
     */
    private void doExercise2(){
        try {
            long duration = (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);

        }catch (InterruptedException e){
            e.printStackTrace();

        }
    }
    /**
     * 辅助方法3，它将线程休眠一段时间。
     */
    private void doExercise3(){
        try {
            long duration = (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);

        }catch (InterruptedException e){
            e.printStackTrace();

        }
    }
}

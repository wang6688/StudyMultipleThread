package _3ResourceSynchronizedAuxiliaryClass._3_7concurrentPhaseTaskInStageOfSwitching;

public class Main {
    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser();
        //创建5个学生类对象，并且通过register()方法将他们注册到phaser对象中
        Student students[] = new Student [5];
        for (int i =0; i<students.length;i++){
            students[i]= new Student(phaser);
            phaser.register();
        }
        //将创建的Student对象逐个作为传入参数创建线程，并且启动它们
        Thread threads [] = new Thread[students.length];
        for (int i = 0; i<students.length;i++){
            threads[i] = new Thread(students[i],"Student "+i);
            threads[i].start();
        }
        //等待5个线程的完成。
        for (int i =0 ;i<threads.length;i++){
            try {
                threads[i].join();

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        //通过调用isTerminated()方法，将phaser是否处于中止态的信息打印到控制台。
        System.out.printf("Main: The phaser has finished: %s.\n",phaser.isTerminated());
    }
}

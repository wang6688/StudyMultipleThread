package _1ThreadManagement._1_5threadInterruptControl;

import java.util.concurrent.TimeUnit;

/**
 * 当运行这个 范例时，程序将进入文件夹查找是否包含指定的文件。例如，如果要查找的文件夹目录结构是\b\c\d，这个程序将
 * 递归调用processDirectory() 方法3次，不管递归调用了多少次，只要线程检测到它已经被中断了，就会立即抛出InterruptedException，然后继续执行run()方法。
 */

//TODO: 实际结果与书上运行的结果不一致
public class Main {
    public static void main(String[] args) {
        FileSearch searcher = new FileSearch("d:\\","hosts4-master.zip");
        Thread thread = new Thread(searcher);
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(10);
            System.err.println("主线程休眠结束，马上通知子线程中断");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //注意：线程中断仅仅是置线程的中断状态位，不会停止线程。需要用户自己去监视线程的状态为并做处理。支持线程中断的方法（也就是线程中断后会抛出interruptedException的方法）就是在监视线程的中断状态，一旦线程的中断状态被置为“中断状态”，就会抛出中断异常。
        //interrupt（）是用来设置中断状态的。返回true说明中断状态被设置了而不是被清除了。我们调用sleep、wait等此类可中断（throw InterruptedException）方法时，一旦方法抛出InterruptedException，当前调用该方法的线程的中断状态就会被jvm自动清除了，就是说我们调用该线程的isInterrupted 方法时是返回false。如果你想保持中断状态，可以再次调用interrupt方法设置中断状态。这样做的原因是，java的中断并不是真正的中断线程，而只设置标志位（中断位）来通知用户。如果你捕获到中断异常，说明当前线程已经被中断，不需要继续保持中断位。
        //interrupted是静态方法，返回的是当前线程的中断状态。例如，如果当前线程被中断（没有抛出中断异常，否则中断状态就会被清除），你调用interrupted方法，第一次会返回true。然后，当前线程的中断状态被方法内部清除了。第二次调用时就会返回false。如果你刚开始一直调用isInterrupted，则会一直返回true，除非中间线程的中断状态被其他操作清除了。
        thread.interrupt();  //设置中断线程。 interrupt方法用于中断线程。调用该方法的线程的状态为将被置为"中断"状态。
        System.err.println("main 线程结束");
    }
}

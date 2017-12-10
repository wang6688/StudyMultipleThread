package _1ThreadManagement._1_12handleUncontrolExceptionOnThreadGroup;

/**
 * 运行结果可以看出: 当一个线程对象抛出了异常，其余的线程对象都被中断，当线程抛出非捕获异常时，JVM将为这个异常寻找3中可能
 * 的处理器。
 * 首先，寻找抛出这个异常的线程的非捕获异常处理器，如果这个处理器不存在，JVM继续查找这个线程所在的线程组的非捕获异常处理器。
 * 如果也不存在，JVM将寻找默认的非捕获异常处理器。
 * 如果这些处理器都不存在，JVM将把堆栈中的异常信息打印到控制台，并且退出这个程序。
 * */
public class Main {
    public static void main(String[] args) {
        MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
        Task task = new Task();
        for (int i=0; i<2; i++){
            Thread t = new Thread(threadGroup,task);
            t.start();
        }
    }
}

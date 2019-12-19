package _1ThreadManagement._1_9hanleUncontrolException;

public class Main {
    /**
     * @param args
     * @Desc 当一个线程抛出了异常并且没有被捕获时(这种情况只可能是运行时异常),JVM检查这个线程是否被预置了未捕获异常处理器。如果找到，JVM将调用线程对象的这个方法，并将线程对象和异常作为传入参数。
     * 如果线程没有被预置未捕获异常处理器，JVM将打印堆栈记录到控制台，并退出程序。
     * 线程寻找 异常处理器流程
     * 1. 查找线程对象的未捕获异常处理器 getUncaughtExceptionHandler。如果找不到继续2
     * 2. 查找线程对象所在的线程组的未捕获异常处理器。 如果找不到继续3
     * 3. 查找默认的未捕获异常处理器。
     * default: 如果没有一个处理器存在，JVM则将堆栈异常记录打印到控制台，并退出程序。
     */
    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());

        thread.start();
    }
}

package _1ThreadManagement._1_10useThreadLocalVariables;

import java.util.concurrent.TimeUnit;

public class Core {
    /**
     * @param args
     * 如果创建的对象是实现了Runnable接口的类的实例，用它作为传入参数创建多个线程对象并启动这些线程，那么所有的线程将共享相同的属性。
     * 再某种情况下，这个对象的属性不需要被所有线程共享。Java并发API提供了一个干净的机制，即线程局部变量，其具有很好的性能。
     *
     */
    public static void main(String[] args) {
      // UnsafeTask task = new UnsafeTask();
        SafeTask task = new SafeTask();
        for (int i =0; i<10;i++){
            Thread thread = new Thread(task);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }
}

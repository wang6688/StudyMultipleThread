package _2ThreadSynchronizedBasis._2_8useMultipleConditionInLock;

/**
 * 文本文件模拟类FIleMock。有两个属性： 字符串数组content和int属性index。
 * content用来存储文件的内容。index用来表示要从这个文件读取的内容的行号。
 *
 * 工作原理：
 * 与锁绑定的所有条件对象都是通过Lock接口声明 的newCondition()方法创建的。
 * 在使用条件的时候，必须获取这个条件绑定的锁，所以带条件的代码必须在调用Lock对象的lock()方法和unlock()方法之间。
 * 当线程调用条件的await()方法时，它将自动释放这个条件绑定的锁，其他某个线程才可以获取这个锁并且执行相同的操作，或者执行这个锁保护的 另一个临界区代码。
 * 备注： 当一个线程调用了条件对象的signal()或者signallAll()方法后，一个或者多个在该条件上挂起的线程将被唤醒，但这并不能保证让他们挂起的条件已经满足，
 * 所以必须在while循环中调用await(),在条件成立之前不能离开这个循环。如果条件不成立，将再次调用await()。
 * 必须小心使用await（）和signal（）方法。如果调用了一个条件的await（）方法，却从不调用它的signal（）方法，这个线程将永久休眠。
 * 因调用await（）方法进入休眠的线程可能会被终端，所以必须处理InterruptedException异常。
 *
 *
 * 更多信息：
 * Condition接口还提供了await（）方法的其他形式。
 * await（long time，TimeUnit unit）， 知道发生以下情况之一之前，线程将一直处于休眠状态。
 * 1. 其他某个线程中断当前线程
 * 2.其他某个线程调用了将当前线程挂起的条件的signal（）或者signalAll（）方法。
 * 3.指定的等待时间已经过去。
 * 4.通过TimeUnit类的常量 指定的等待时间已经过去。 awaitUninterrupibly（）： 它是不可终端的 。这个线程将休眠直到其他某个线程调用了将它挂起的条件的signal（）或者signalAll()方法。
 *  awaitUnit（date ）： 直到发生一下情况之一之前，线程将一直处于休眠状态。
 *  其他某个线程中断当前线程。
 *  其他某个线程调用了将他挂起的条件的signal（）或 singalALl（）方法。
 *  指定的最后期限到了。
 *  也可以将条件与读写锁 ReadLock 和WariteLock 一起使用。
 */
public class FileMock {
    private String content[];
    private int index;

    /**
     * 公共FileMock类的构造器初始化文件内容content，这里使用了随机字符。
     * @param size
     * @param length
     */
    public FileMock(int size, int length) {
        content = new String[size];
        for (int i =0; i<size;i++){
            StringBuilder buffer = new StringBuilder(length);
            for (int j = 0; j<length;j++){
                int indice = (int)Math.random()*255;
                buffer.append((char)indice);
            }
            content[i]=buffer.toString();
        }
        index = 0;

    }

    /**
     * 如果文件有可以处理的数据行则返回true，如果已经到达了模拟文件的结尾则返回false
     * @return
     */
    public boolean hasMoreLines(){
        return index < content.length;
    }

    /**
     * 返回index制定行的内容，并且将index自动增加1.
     * @return
     */
    public String getLine(){
        if(this.hasMoreLines()){
            System.out.println("Mock:  "+(content.length-index));
            return content[index++];
        }
        return null;
    }

}

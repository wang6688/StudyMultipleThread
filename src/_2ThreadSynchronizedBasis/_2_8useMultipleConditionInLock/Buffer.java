package _2ThreadSynchronizedBasis._2_8useMultipleConditionInLock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private LinkedList<String> buffer;  //用来存放共享数据

    private int maxSize; //用来存放buffer的长度
    private ReentrantLock lock;   //用来对修改buffer的代码块进行控制。

    private Condition lines;        //两个条件属性。
    private Condition space;
    private boolean pendingLines;  //用来表明缓冲区中是否还有数据。

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }

    /**
     * 这个方法的传入参数是字符串，它将把这个字符串写入到缓冲区中。
     * 首先，insert()方法要获取锁，当获取锁之后，它将检查这个缓冲区是否还有空位。
     * 如果缓冲区满了，它将调用条件space的await()方法等待空位出现。
     * 当其他线程调用条件space的signal()方法或者siganlAll()方法时，这个线程将被唤醒。
     * 在有空位后，线程会将数据行保存到缓冲区中，并且调用条件lines的signallAll()方法。
     * 一会儿我们会看到，条件lines将唤醒所有等待缓冲区中有数据的线程。
     * @param line
     */
    public void insert(String line){
        lock.lock();
        try {
            while(buffer.size()==maxSize){
                space.await();
            }
            buffer.offer(line);
            System.out.printf("%s: Inserted Line : %d\n",Thread.currentThread().getName(),buffer.size());
            lines.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * @return
     * 它返回缓冲区中的第一个字符串。首先，get()方法要获取锁。
     * 当获取锁之后，它将检查缓冲区是不是有数据行。如果缓冲区是空的，就调用条件lines的await()方法来
     * 等待缓冲区数据的出现。 当其他线程调用条件的lines的signalI()或者siganlAll()方法时，这个 线程被唤醒。
     * 在有数据后，get()方法获取缓冲区中的第一行，并且调用条件的space的signalAll()方法，并且返回这个数据行字符串。
     * *
     */
    public String get(){
        String line = null;
        lock.lock();
        try {
            while((buffer.size()==0)&&(hasPendingLines())){
                lines.await();
            }
            if(hasPendingLines()){
                line = buffer.poll();
                System.out.printf("%s : Line Readed: %d\n",Thread.currentThread().getName(),buffer.size());
                space.signalAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return line;
    }


    public void setPedingLines(boolean pendingLines){
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines(){
        return pendingLines || buffer.size() >0;
    }


}

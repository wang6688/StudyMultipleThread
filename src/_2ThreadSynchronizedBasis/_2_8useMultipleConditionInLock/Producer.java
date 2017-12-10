package _2ThreadSynchronizedBasis._2_8useMultipleConditionInLock;

import java.util.Random;

public class Producer implements  Runnable {
    private FileMock mock;
    private Buffer buffer;

    public Producer(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    /**
     * 实现run方法。这个方法用来读文件FIleMock中所有数据行，并且使用insert（）方法
     * 将读到的数据行插入到缓冲区。一旦读完文件，将调用setPendingLines()方法来通知缓冲区停止生成更多的行。
     */
    @Override
    public void run() {
        buffer.setPedingLines(true);
        while(mock.hasMoreLines()){
            String line = mock.getLine();
            buffer.insert(line);
        }
        buffer.setPedingLines(false);
    }
}

package _1ThreadManagement._1_8createAndRunGuardThread;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    /**
     * 这个程序有3个WriterTask线程，每个线程向队列写入一个事件，然后休眠1秒钟。在第一个10秒钟内，
     * 队列有30个时间，直到3个WriterTask 都休眠后，CleanerTask才开始执行，但是它没有删除任何事件。
     * 因为所有的事件都小于10秒钟。在接下来的运行中，CleanerTask每秒删除3个对象，同事WriterTask会写入3个对象，
     * 所以队列的长度一致介于27~30之间。
     * 你可以不断调试WriterTask休眠的时间。如果使用一个更小的值，会发现CleanerTask将有更少的CPU事件，
     * 并且队列的长度将增加，因为CleanerTask没有删除对象。
     * @param args
     */
    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();
        WriterTask writer = new WriterTask(deque);
        for (int i =0; i<3;i++){
            Thread thread = new Thread(writer);
            thread.start();
        }
        CleanerTask cleaner = new CleanerTask(deque);
        cleaner.start();
    }
}

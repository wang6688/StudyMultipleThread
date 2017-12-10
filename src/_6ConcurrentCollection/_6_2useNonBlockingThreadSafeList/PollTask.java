package _6ConcurrentCollection._6_2useNonBlockingThreadSafeList;

import java.util.concurrent.ConcurrentLinkedDeque;

public class PollTask implements  Runnable {
    private ConcurrentLinkedDeque<String > list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    /**
     * 将列表中的10000个字符串取出，总共取5000次，每次取出两个元素。
     */
    @Override
    public void run() {
        for ( int i = 0; i<5000;i++){
            list.pollFirst();
            list.pollLast();
        }
    }
}

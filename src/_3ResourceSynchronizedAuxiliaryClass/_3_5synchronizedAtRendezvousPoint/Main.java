package _3ResourceSynchronizedAuxiliaryClass._3_5synchronizedAtRendezvousPoint;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) {
        //声明和初始化5个常亮。它们将作为这个应用程序的参数。
        final  int ROWS = 10000;
        final  int NUMBERS = 1000;
        final  int SEARCH = 5;
        final  int PARTICIPANTS = 5;
        final  int LINES_PARTICIPANT = 2000;

        //创建矩阵类MatrixMock对象mock。他有1000行，每行有1000个数字，而这里要查找的数字是5.
        MatrixMock mock = new MatrixMock(ROWS,NUMBERS,SEARCH);
        //创建结果类Results对象results。他有1000个元素。
        Results results = new Results(ROWS);
        //创建Grouper对象grouper
        Grouper grouper = new Grouper(results);
        //创建CyclicBarrier类对象barrier。这个对象将等待5个线程运行结束然后，它将执行创建的Grouper线程对象。
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS,grouper);
        //创建5个查找类Searcher对象， 将他们分别作为传入参数创建线程并启动
        Searcher searchers[] = new Searcher[PARTICIPANTS];
        for (int i = 0 ;i<PARTICIPANTS;i++){
            searchers[i] = new Searcher(i*LINES_PARTICIPANT,(i*LINES_PARTICIPANT)+LINES_PARTICIPANT,mock,results,5,barrier);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        System.out.printf("Main:The main thread has finished.\n");
    }
}

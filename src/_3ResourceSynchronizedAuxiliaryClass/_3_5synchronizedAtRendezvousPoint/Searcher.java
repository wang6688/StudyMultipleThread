package _3ResourceSynchronizedAuxiliaryClass._3_5synchronizedAtRendezvousPoint;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 查找类，它在随机数矩阵指定的行中查找某个数。创建Searcher类并期限Runnable接口。
 */
public class Searcher implements Runnable {
    //这两个属性决定了查找的子集范围。
    private int firstRow;
    private int lastRow;

    private MatrixMock mock;
    private Results results;

    //用于存放要查找的数字。
    private  int number;

    private final CyclicBarrier barrier;

    public Searcher(int firstRow, int lastRow, MatrixMock mock, Results results, int number, CyclicBarrier barrier) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.mock = mock;
        this.results = results;
        this.number = number;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        int counter; //用来存放每行查找到的次数
        //将查找范围打印到控制台。
        System.out.printf("%s: Processing lines from %d to %d.\n",Thread.currentThread().getName(),firstRow,lastRow);
        //在要查找的所有行中进行查找。对每一行查找指定的数字，并将查找到的次数保存到对应的results对象的相应位置。
        for (int i = firstRow; i<lastRow;i++){
            int row[] = mock.getRow(i);
            counter = 0;
            for ( int j = 0; j<row.length;j++){
                if (row[j] == number){
                    counter++;
                }
            }
            results.setData(i,counter);
        }
        //将线程查找完成的信息打印到控制台。
        System.out.printf("%s: Lines processed.\n",Thread.currentThread().getName());

        try {
            barrier.await();

        }catch (InterruptedException e){
            e.printStackTrace();
        }catch ( BrokenBarrierException e){
            e.printStackTrace();
        }
    }
}

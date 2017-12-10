package _3ResourceSynchronizedAuxiliaryClass._3_5synchronizedAtRendezvousPoint;

import _1ThreadManagement._1_11threadGroup.Result;

/**
 * 实现一个类来计算在矩阵中查找到的总次数。计算是基于Results对象的，后者存放了矩阵中每行查找的次数。
 * 创建Grouper类并制定它实现Runnable接口。
 */
public class Grouper implements Runnable {
    private Results results;

    public Grouper(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult = 0; //并将开始统计的信息打印到控制台。
        System.out.printf("Grouper: Processing results ...\n");
        //使用reuslts对象的getDate()方法获得存放每行发生次数的数组 。 然后，处理这个数组并将结果累加到finalResult变量。
        int data[] = results.getData();
        for ( int  number:data){
            finalResult += number;
        }
        System.out.printf("Grouper: Total result:%d.\n",finalResult);

    }
}

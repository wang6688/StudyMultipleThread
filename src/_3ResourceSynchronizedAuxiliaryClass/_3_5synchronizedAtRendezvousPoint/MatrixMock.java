package _3ResourceSynchronizedAuxiliaryClass._3_5synchronizedAtRendezvousPoint;

import java.util.Random;

/**
 * 矩阵类，用来 生成一个1~10组成的随机矩阵，线程将从这个矩阵中查找指定的数组。
 *
 */
public class MatrixMock {
    //声明私有 二维数组data
    private int data[][];
    //构造器: 传入的参数是矩阵的行数，每行的长度，要寻找的数字
    public MatrixMock(int size, int length, int number) {
        int counter = 0;
        data = new int[size][length];
        Random random = new Random();

        //用随机数字为矩阵赋值。每生成一个数字，就用它跟要查找的数字进行比较。如果一致，就将计数器counter加1.
        for (int i =0; i<size;i++){
            for (int j = 0;j<length;j++){
                data[i][j] = random.nextInt(10);
                if(data[i][j] == number){
                    counter++;
                }
            }
        }
        //将在矩阵中查找到的次数打印到控制台。这个信息用来检查线程是否得到了正确的结果。
        System.out.printf("Mock: There are %d ocurrences of number in generated data.\n",counter,number);

    }

    //参数是矩阵行序号，如果矩阵中存在这个行，就返回行据，如果不存在，就返回null。
    public  int []  getRow(int row){
        if((row)>=0 && (row<data.length)){
            return  data[row];
        }
        return null;
    }
}

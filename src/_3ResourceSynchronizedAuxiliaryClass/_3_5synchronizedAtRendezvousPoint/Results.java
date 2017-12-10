package _3ResourceSynchronizedAuxiliaryClass._3_5synchronizedAtRendezvousPoint;

/**
 *  这个类保存矩阵中每行找到制定数字的次数
 */
public class Results  {
    private int data[];

    public Results(int size) {
        data = new int[size];
    }

    public int[] getData() {
        return data;
    }

    public void setData(int position,int value) {
        this.data[position] = value;
    }
}

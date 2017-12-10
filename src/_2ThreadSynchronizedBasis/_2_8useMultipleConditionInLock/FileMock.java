package _2ThreadSynchronizedBasis._2_8useMultipleConditionInLock;

/**
 * 文本文件模拟类FIleMock。有两个属性： 字符串数组content和int属性index。
 * content用来存储文件的内容。index用来表示要从这个文件读取的内容的行号。
 */
public class FileMock {
    private String content[];
    private int index;

    /**
     * 公国FileMock类的构造器初始化文件内容content，这里使用了随机字符。
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

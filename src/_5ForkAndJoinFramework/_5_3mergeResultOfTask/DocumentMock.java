package _5ForkAndJoinFramework._5_3mergeResultOfTask;

import java.util.Random;

public class DocumentMock {
    private String words[] = {"the","hello","goodbye","packt","java","thread","pool","random","class","main"};

    public String [][] generateDocument(int numLines,int numWords,String word){
        int counter = 0;
        String document[][] = new String[numLines][numWords];
        Random random = new Random();
        //为字符串矩阵天上字符串。通过随机数组取得数组words中的某一字符串，然后存入到字符串矩阵document对应的位置上，
        //同时计算生成的字符串矩阵中将要查找的词出现的次数。这个值可以用来与后续程序运行查找任务时统计的次数相比较，检查两个值是否相同。
        for (int i =0; i<numLines;i++){
            for (int j = 0; j<numWords;j++){
                int index = random.nextInt(words.length);
                document[i][j] = words[index];
                if (document[i][j].equals(word)){
                    counter++;
                }
            }
        }
        System.out.printf("DocumentMock: The word appers "+counter+" times in the document");
        return  document;
    }
}

package _1ThreadManagement._1_3getAndSetThreadInfo;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        Thread threads[] = new Thread[10];   //  创建 一个容量 为 10 的线程数组 ，以用来 存储线程
        Thread.State status[] = new Thread.State[10];  //  创建一个容量为10 的Thread.State 数组 ，以用来存放这10个线程运行时的状态。

        /*创建一个容量为10 的Calculator 对象数组，为每个对象都设置不同的数字，
        然后使用他们作为Thread构造器的参数来创建10个线程对象。并且将其中5个线程的优先级设置为最高，
                另外5个设置为最低。 */
        for (int i =0;i< 10; i++){
            threads[i] = new Thread(new Calculator(i));
            if((i%2==0)){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else{
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
             threads[i].setName("Thread " + i );
        }

        //  创建一个PrintWriter对象，用来把线程的状态演变写入到文件中。
        try{
            FileWriter file = new FileWriter("c:\\log.txt");
            PrintWriter pw = new PrintWriter(file);
            //  把 这10个线程的状态写入文件中。现在的线程状态是New
            for (int i =0; i<10;i++){
                pw.println("Main: Status of Thread "+i +":"+threads[i].getState());

                status[i] = threads[i].getState();
            }

            //开始 执行10个线程
            for (int i =0; i<10; i++){
                threads[i].start();
            }

            //  直到10个线程都 运行完成，我们就可以查看他们的状态。所有人和一个线程的状态发生  了变化，我们就会将他写入到文件中。
            boolean finish = false;
            while (!finish){
                for (int i =0 ;i<10; i++){
                    if(threads[i].getState()!=status[i]){
                        writeThreadInfo(pw, threads[i],status[i]);
                        status[i] = threads[i].getState();
                    }
                }
                finish = true;
                for(int i =0; i <10; i++){
                    finish= finish&& (threads[i].getState() == Thread.State.TERMINATED);
                }
            }
        } catch (IOException e) {
            System.out.println("创建文件时 发生异常");
        }




    }
    // 编写 writeThreadInfo() 方法，用来写下线程的ID、名称、优先级、旧的状态和新的状态。
    private static  void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state){
        pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
        pw.printf("Main: Priority: %d\n",thread.getPriority());
        pw.printf("Main: Old State: %s\n",state);
        pw.printf("Main : New State: %s\n",thread.getState());
        pw.printf("Main : ************************************\n");
        pw.flush();
    }

}

package _3ResourceSynchronizedAuxiliaryClass._3_8concurrentDataExchangeBetweenTasks;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumer implements  Runnable {
    //它是生产者和消费者进行交换的数据结构
    private List<String> buffer;
    private final Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        //循环执行10次数据交换
        int cycle = 1;
        for (int i =0; i<10; i++ ){
            System.out.printf("Consumer: Cycle %d\n",cycle);
            //在每个循环中，消费者要消费数据,所以先调用exchanger()方法与生产者同步。
            try {
                buffer = exchanger.exchange(buffer);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //将生产者放入消费者buffer列表中的10个字符串打印到控制台，并且从buffer列表中删除，保持一个空的列表。
            System.out.printf("Consumer: "+buffer.size());
            for (int j =0; j<10; j++){
                String message = buffer.get(0);
                System.out.println("Consumer: "+message);
                buffer.remove(0);

            }
            cycle++;
        }

    }
}

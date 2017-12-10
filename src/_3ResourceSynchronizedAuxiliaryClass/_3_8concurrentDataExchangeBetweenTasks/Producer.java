package _3ResourceSynchronizedAuxiliaryClass._3_8concurrentDataExchangeBetweenTasks;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements  Runnable {

    //用于生产者和消费者进行交换的数据结构。
    private List<String> buffer;
    //用于同步生产者和消费者的交换对象
    private final Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;
        //循环执行10次数据交换
        for (int i =0; i<10;i++){
            System.out.printf("Producer: Cycle %d\n",cycle);
            //z在每个循环中，添加10个字符串到buffer列表中
            for (int j = 0;j<10; j++){
                String message = "Event "+ ((i*10)+j);
                System.out.printf("Producer: %s\n",message);
                buffer.add(message);
            }
            //调用exchange()方法与消费者进行数据交换。 由于这个方法会抛出InterruptedException异常，必须捕获并处理这个异常。
            try {
                buffer = exchanger.exchange(buffer);

            }catch (InterruptedException e){
                e.printStackTrace();

            }
            System.out.printf("Producer: "+buffer.size());
            cycle++;
        }
    }
}

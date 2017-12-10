package _5ForkAndJoinFramework._5_2createForkAndJoinThreadPool;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(10000);

        Task task  = new Task(products,0,products.size(),0.20);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);

        do {
            System.out.printf("Main: Thread Count: %d\n",pool.getActiveThreadCount());
            System.out.printf("Main: Thread Steal: %d\n",pool.getStealCount());
            System.out.printf("Main: Parallelism: %d\n",pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }while (!task.isDone());

        //调用shutdown()方法关闭线程池
        pool.shutdown();

        if (task.isCompletedNormally()){//检查任务是否已经完成并且没有错误，在这里控制台输出信息表示任务已经处理结束。
            System.out.printf("Main: The process has completed normally.\n");

        }
        //在增加之后，所有产品的期望价格是12元。在控制台输出所有产品的名称和价格，如果产品的价格不是12元，就将产品信息打印出来，
        //以便确认所有的产品价格都正确地增加了。
        for (int i = 0;i<products.size();i++){
            Product product = products.get(i);
            if (product.getPrice()!=12){
                System.out.printf("Product %s: %f\n",product.getName(),product.getPrice());
            }
        }
        System.out.printf("Main: End of the program.\n");

    }
}

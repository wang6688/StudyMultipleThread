package _5ForkAndJoinFramework._5_2createForkAndJoinThreadPool;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {
    private static  final long serialVersionUID = 1L;
    private List<Product> products;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if (last-first<10){ //如果last和first属性值的差异小于10(一个任务只能更新少于10件产品的价格），
            //则调用updatePrices()方法增加这些产品的价格。
            updatePrices();

        }else {//如果last和first属性值的差异大于或等于10，就创建两个新的Task对象，一个处理前一半的产品，
            //另一个处理后一半的产品，然后调用ForJoinPool的invokeAll()方法来执行这两个新的任务。
            int middle = (last+first)/2;
            System.out.printf("Task : Pending tasks: %s\n",getQueuedTaskCount());
            Task t1 = new Task(products,first,middle+1,increment);
            Task t2 = new Task(products,middle+1,last,increment);
            invokeAll(t1,t2);
        }
    }

    private void updatePrices(){
        for ( int i = first; i<last;i++){
            Product product  = products.get(i);
            product.setPrice(product.getPrice()*(1+increment));

        }
    }
}

package _1ThreadManagement._1_8createAndRunGuardThread;

import java.util.Date;
import java.util.Deque;

public class CleanerTask extends Thread {
    private Deque<Event> deque;

    public CleanerTask(Deque<Event> deque) {

        this.deque = deque;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true){
            Date date = new Date();
            System.out.println("准备清理队列中的时间了"+date.getTime());

            clean(date);

        }
    }

    /**
     * 实现clean()方法，clean() 将读取队列的最后一个事件对象，如果这个事件是10秒钟前创建的，就将它删除并且检查下一个。
     * 如果有事件被删除，clean()将，打印出这个被删除的事件的信息，也打印出队列的长度，这样，我们就可以看到程序的演化过程。
     * @param date
     */
    private void clean(Date date){
        long difference;
        boolean delete;
        if(deque.size()==0){
            return ;
        }
        delete = false;
        do{
            Event e = deque.getLast();
            System.out.println("比较队列中最后一个的事件生成时间"+e.getDate().getTime()+" 和 要清理的当前时间"+date.getTime());
            difference = date.getTime() -e.getDate().getTime();
            if(difference >1000){
                System.out.printf("Cleaner: %s\n",e.getEvent());
                deque.removeLast();
                delete = true;
            }
        }while (difference >1000);
        if(delete){
            System.out.printf("Cleaner: Size of the queue: %d\n",deque.size());
        }

    }
}

package _1ThreadManagement._1_7waitTerminationThread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetworkConnectionsLoader implements Runnable {
    @Override
    public void run() {
        System.out.printf("Beginning network connections loading:%s\n",new Date());
        try{
            TimeUnit.SECONDS.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Network connections loading has finished:%s\n",new Date());
    }
}

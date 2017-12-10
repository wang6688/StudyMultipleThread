package _6ConcurrentCollection._6_3useBlokedThreadSafeList;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {
    private LinkedBlockingDeque<String> requestList;

    public Client(LinkedBlockingDeque<String> requestList) {
        this.requestList = requestList;
    }

    /**
     * 使用requestList对象的put方法，每两秒向列表requestList中插入5个字符串。重复3次。
     */
    @Override
    public void run() {
        for ( int i=0; i<3;i++){
            for (int j =0; j<5;j++){
                StringBuilder request = new StringBuilder();
                request.append(i);
                request.append(" : ");
                request.append(j);
                try {
                    requestList.push(request.toString());
                }catch ( Exception e){
                    e.printStackTrace();
                }

                System.out.printf("Client: %s at %s.\n",request,new Date());
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("Client: End.\n");
    }
}

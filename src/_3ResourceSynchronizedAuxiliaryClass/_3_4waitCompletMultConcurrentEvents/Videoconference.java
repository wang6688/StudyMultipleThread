package _3ResourceSynchronizedAuxiliaryClass._3_4waitCompletMultConcurrentEvents;

import java.util.concurrent.CountDownLatch;

public class Videoconference implements  Runnable {
    private final CountDownLatch controller;

    public Videoconference(int number) {
        this.controller = new CountDownLatch(number);
    }

    @Override
    public void run() {
        //使用getCount()方法打印出这次视频会议的人数
        System.out.printf("VideoConference: Initialization: %d participants.\n",controller.getCount());
        //使用await()方法等待所有的与会者到达。
        try {
            controller.await();
            //当所有与会者都到齐后，打印出与会者到齐会议开始的信息。
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.printf("VideoConference: Let's start...\n");

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //实现arrive()方法，每个与会者进入视频会议的时候，这个方法将被调用。
    public  void arrive(String name){
        //打印出 与会者到达的信息
        System.out.printf("%s has arrived.",name);
        controller.countDown();
        //打印出还没有到达的与会者数目，通过CountDownLatch对象的getCount()方法实现。
        System.out.printf("VideoConference: Waiting for %d participants.\n",controller.getCount());
    }
}

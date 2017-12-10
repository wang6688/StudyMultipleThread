package _3ResourceSynchronizedAuxiliaryClass._3_4waitCompletMultConcurrentEvents;

import java.util.concurrent.TimeUnit;

/**
 * 这个类表示的是视频会议的与会者。
 */
public class Participant implements  Runnable {
    //声明 Videoconference 型私有属性conference。
    private Videoconference conference;
    private String name;

    public Participant(Videoconference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    @Override
    public void run() {
        //让线程休眠一段随机时间
        long duration = (long)(Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);

        }catch (InterruptedException e){
            e.printStackTrace();
        }

        //使用视频会议对象的conference的arrive()方法来声明一个与会者的到来。
        conference.arrive(name);
    }
}

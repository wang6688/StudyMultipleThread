package _3ResourceSynchronizedAuxiliaryClass._3_4waitCompletMultConcurrentEvents;

public class Main {
    public static void main(String[] args) {
        //创建视频会议对象conference，他要等待10个与会者到齐。
        Videoconference conference = new Videoconference(10);
        //将视频会议对象作为传入参数创建线程，并且启动。
        Thread threadConference = new Thread(conference);
        threadConference.start();
        //创建10个与会者对象，分别将每一个作为传入参数创建线程，并且启动。
        for (int i =0 ;i <10; i++){
            Participant p = new Participant(conference,"Participant "+i);
            Thread t = new Thread(p);
            t.start();
        }
    }

}

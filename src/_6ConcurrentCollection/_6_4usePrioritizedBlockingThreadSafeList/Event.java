package _6ConcurrentCollection._6_4usePrioritizedBlockingThreadSafeList;

public class Event implements Comparable<Event> {
    private int thread;// 存放 event的线程数
    private int priority;  //存放event的优先级。

    public Event(int thread, int priority) {
        this.thread = thread;
        this.priority = priority;
    }

    public int getThread() {
        return thread;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Event e) {
        if(this.priority>e.getPriority()){
            return -1;
        }else if(this.priority<e.getPriority()){
            return 1;
        }else{
            return 0;
        }
    }
}

package _4ThreadExecutor._4_10controlCompletionTaskInActuator;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ResultTask extends FutureTask<String> {
    private String name;

    /**
     * 实现类构造器。它接受一个Callable对象作为参数，调用父类构造器，并用接受到的任务属性来初始化name属性。
     *
     * @param callable
     */
    public ResultTask(Callable<String> callable) {
        super(callable);
        this.name = ((ExecutableTask)callable).getName();
    }

    @Override
    protected void done() {
        if (isCancelled()){
            System.out.printf("%s: Has been canceled\n",name);
        }else {
            System.out.printf("%s: Has finished\n",name);
        }
    }
}

package _3ResourceSynchronizedAuxiliaryClass._3_6concurrentPhaseRunTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 在一个文件夹及其子文件夹中查找过去24小时内修改过的指定扩展名的文件。
 */
public class FileSearch implements  Runnable {
    //用来存储查找的文件夹。
    private String initPath;
    //用来存储要查找的文件的扩展名。
    private String end;
    //用来存储要查找到的文件的完整路径
    private List<String> results;
    //用来控制任务不同阶段的同步
    private Phaser phaser;
    private File file;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
        results = new ArrayList<>();
    }





    /**
     * 实现辅助的方法，它们将用于run()方法中。第一个方法时directoryProcess（）方法，
     * 它的传入参数是文件类File对象，并且它将处理文件夹的所有文件和子文件夹。
     * 对于每一个文件夹，这个方法将递归调用。对于每个文件，这个方法将调用fileProcess()方法。
     */
    private void directoryProcess(File file){
        File list[] = file.listFiles();
        if(list !=null){
            for (int i =0; i<list.length;i++){
                if(list[i].isDirectory()){
                    directoryProcess(list[i]);
                }else{
                    fileProcess(list[i]);
                }

            }
        }
    }

    /**
     * 实现fileProcess方法，它的传入参数是文件类File对象，用于检查这个
     * 传入的文件的扩展名是不是我们指定的。如果是，文件的绝对路径将被加入到结果集中。
     * @param file
     */
    private void fileProcess(File file) {
        if(file.getName().endsWith(end)){
            results.add(file.getAbsolutePath());
        }
    }

    /**
     * 该方法没有传入参数，而是对第一个阶段查找到的文件列表进行过滤，将不是过去24小时修改过的文件删除。这里创建一个空的列表，
     * 接着获得当前日期。
     */
    private void filterResults(){
        List<String > newResults = new ArrayList<>();
        Long actualData = new Date().getTime();
        //遍历结果列表中的每个元素，对每个元素用文件路径创建文件类FIle对象，并获得最后修改的时间。
        for (int i =0; i<results.size();i++){
            File file = new File(results.get(i));
            long fileDate = file.lastModified();
            //比较修改时间和当前时间。如果间隔小于一天，那么稳健的完整路径将被添加到新创建的列表中。
            if (actualData-fileDate < TimeUnit.MICROSECONDS.convert(1,TimeUnit.DAYS)){
                newResults.add(results.get(i));

            }
        }
        //将新列表引用赋给老列表。
        results = newResults;
    }

    /**
     * 该方法将在第一个阶段和第二个阶段结束的时候被调用，用来检查结果集是不是空的。这个方法没有传入任何参数。
     * @return
     */
    private boolean checkResults(){
        //检查结果列表的长度，如果是0，将没有找到任何文件的信息打印到控制台，并且调用Phaser对象的arriveAndDeregister()fangaf，
        //来通知Phaser对象 当前线程已经结束这个阶段，并且将不再参与接下来的阶段操作。
        if (results.isEmpty()){
            System.out.printf("%s ： Phase %d : 0 results.\n",Thread.currentThread().getName(),phaser.getPhase());
            System.out.printf("%s : Phase %d : end.\n",Thread.currentThread().getName(),phaser.getPhase());
            phaser.arriveAndDeregister();
            return false;
        }else{
            //如果结果集不是空的，则将查找的文件数打印到控制台，并调用Phaser对象的arriveAndAwaitAdvance()方法，来通知Phaser对象
            //当前线程已经完成了当前阶段，需要被阻塞直到其他线程也能完成当前阶段。
            System.out.printf("%s: Phase : %d results.\n",Thread.currentThread().getName(),phaser.getPhase(),results.size());
            phaser.arriveAndDeregister();
            return true;
        }

    }

    /**
     * 将结果元素集打印到控制台
     */
    private void showInfo(){
        for (int i = 0; i<results.size();i++){
            File file = new File(results.get(i));
            System.out.printf("%s: %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
        }
        phaser.arriveAndDeregister();
    }


    /**
     * 使用上述实现的辅助方法操作，并且使用Phaser对象控制阶段的改变。首先，调用Phaser对象的arriveAndAwaitAdvance()方法，
     * 使查找工作在所有线程都被创建之后再开始。
     */
    @Override
    public void run() {
        phaser.arriveAndDeregister();
        //将查找任务开始执行的信息打印到控制台。
        System.out.printf("%s: Starting.\n",Thread.currentThread().getName());
        //在initPath属性存储的文件夹中，使用directoryProcess()方法查找这个我呢歼击机及其子文件夹中指定扩展名的文件。
        File file = new File(initPath);
        if (file.isDirectory()){
            directoryProcess(file);
        }
        //使用checkReulsts()方法检查结果集是不是空的，如果是，结束对应线程，并用关键字return返回。
        if (!checkResults()){
            return;
        }
        //使用filterResults()方法过滤结果集。
        filterResults();
        //使用checkResults()方法再次检查新的结果集是不是空的，如果是，对应线程将结束，并使用关键字return返回。
        if (!checkResults()){
            return;
        }
        //使用showInfo()方法将最终的结果集打印到控制条，并且插销线程的注册，然后将线程完成的信息打印到控制台。
        showInfo();
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed.\n",Thread.currentThread().getName());

    }


}

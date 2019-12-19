package _1ThreadManagement._1_5threadInterruptControl;

import java.io.File;

public class FileSearch implements  Runnable {
    private String initPath;
    private String fileName;

    public FileSearch(String initPath, String fileName) {
        this.initPath = initPath;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(initPath);
        if(file.isDirectory()){
            try {
                directoryProcess(file);
            }catch (InterruptedException e){
                System.err.printf("%s : The search has been interrupted",Thread.currentThread().getName());
            }
        }
    }

    private void directoryProcess(File file)throws  InterruptedException{
        System.out.println("查找目录:"+file.getName());
        File list[]  = file.listFiles();
        if(list!=null){
            for (int i =0; i<list.length;i++){
                if(list[i].isDirectory()){
                    directoryProcess(list[i]);
                }else {
                    fileProcess(list[i]);
                }
            }
        }
        if(Thread.interrupted()){
            System.err.println("没有找到您查找的文件");
            throw  new InterruptedException();
        }
    }

    private void fileProcess(File file)throws  InterruptedException{
        System.out.println("查找wenjian :"+file.getName());

        if(file.getName().equals(fileName)){
            System.out.printf("%s : %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
        }
        if(Thread.interrupted()){
            System.err.println("收到通知，即将结束子线程");
            throw  new InterruptedException();
        }
    }
}

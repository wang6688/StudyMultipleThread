package _7CustomConcurrencyClasses._7_4threadFactoryInterfaceToGenerateCustomThreads;

import java.util.Date;

public class MyThread extends  Thread {
    private Date creationDate;
    private Date startDate;
    private Date finishDate;

    public MyThread(Runnable target, String name) {
        super(target, name);
         setCreationDate();
    }

    public void setCreationDate() {
        this.creationDate = new Date();
    }

    public void setStartDate() {
        this.startDate =  new Date();
    }

    public void setFinishDate() {
        this.finishDate =  new Date();
    }

    @Override
    public void run() {
        setStartDate();
        super.run();
        setFinishDate();
    }

    public long getExecutionTIme(){
        return finishDate.getTime()-startDate.getTime();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName());
        builder.append(": ");
        builder.append("Creation Date: ");
        builder.append(creationDate);
        builder.append(": Running Time: ");
        builder.append(getExecutionTIme());
        builder.append(" Milliseconds.");
        return builder.toString();
    }
}

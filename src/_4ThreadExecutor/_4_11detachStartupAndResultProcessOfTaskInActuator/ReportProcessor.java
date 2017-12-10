package _4ThreadExecutor._4_11detachStartupAndResultProcessOfTaskInActuator;

import java.util.concurrent.*;

public class ReportProcessor implements  Runnable {
    private CompletionService<String> service;

    private boolean end;

    public ReportProcessor(CompletionService<String> service) {
        this.service = service;
        this.end = false;
    }

    @Override
    public void run() {
        while (!end){
            try {
                Future<String> result = service.poll(20, TimeUnit.SECONDS);
                if (result!=null){
                    String report = result.get();
                    System.out.printf("ReportReceiver: Report Received: %s\n",report);
                }
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();

            }
        }
        System.out.printf("ReportSender: End\n");

    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}

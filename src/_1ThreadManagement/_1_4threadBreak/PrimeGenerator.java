package _1ThreadManagement._1_4threadBreak;

/**
 *   如果一个Java程序有不止一个执行线程，当所有线程都运行结束的时候，这个Java程序才能运行结束。
 *  更确切的说应该是所有的非守护线程运行结束时，或者其中一个线程调用了System.exit()方法时，这个Java程序才运行结束。
 *  如果你想终止一个程序，或者程序的某个用户试图取消线程对象正在运行的任务，就需要结束这个线程。
 *
 * 素数生成器
 */
public class PrimeGenerator extends Thread{

    @Override
    public void run() {
        long number = 1L;
        while(true){
            if(isPrime(number)){
                System.out.printf("Number %d is Prime\n",number);
            }
            if (isInterrupted()){
                System.out.printf("The Prime Generator has been Interrupted\n");
                return ;
            }
            number++;
        }
    }
    private boolean isPrime(long number){
        if(number <=2){
            return false;
        }
        for(long i =2;i<number;i++){
            if((number%i)==0){
                return false;
            }
        }
        return true;
    }
}

package _3ResourceSynchronizedAuxiliaryClass._3_7concurrentPhaseTaskInStageOfSwitching;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser {
    /**
     * 覆盖onAvance方法。根据传入参数若果phase的值，调用不同的辅助方法，
     * 如果phase是0将调用studentsArrived()方法。如果phase是1，将调用finishFistExercise()方法。
     * 如果phase是2，将调用finishSecondExercise（）方法。
     * 如果phase是3 ，将调用finishExam()方法。如果不是上述值，将返回
     *true表明这个phaser已经终止了。
     */
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase){
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    /**
     * 该辅助方法打印两条信息到控制台，并且返回false表明phaser已经开始执行了。
     */
     private boolean studentsArrived (){
         System.out.printf("Phaser: The exam are going to start. The students are ready.\n");
         System.out.printf("Phaser: We have %d students.\n",getRegisteredParties());
         return false;
     }
    /**
     * 该辅助方法打印两条信息到控制台，并且返回false表明phaser已经开始执行了。
     */
     private boolean finishFirstExercise(){
         System.out.printf("Phaser: All the students have finished the first exercise.\n");
         System.out.printf("Phaser: It's time for the second one.\n");
         return false;
     }
    /**
     * 该辅助方法打印两条信息到控制台，并且返回false表明phaser已经开始执行了。
     */
     private boolean finishSecondExercise(){
         System.out.printf("Phaser: All the students have finished the second exercies.\n");
         System.out.printf("Phaser: It's time for the third one.\n");
         return  false;
     }

     private boolean finishExam(){
         System.out.printf("Phaser: All the students have finished the exam.\n");
         System.out.printf("Phaser : Thank you for your time.\n");
         return true;
     }
}

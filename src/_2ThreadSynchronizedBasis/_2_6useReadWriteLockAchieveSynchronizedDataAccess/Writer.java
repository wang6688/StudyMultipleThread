package _2ThreadSynchronizedBasis._2_6useReadWriteLockAchieveSynchronizedDataAccess;

/**
 * 这个类将修改价格信息PricesInfo类的属性值。
 */
public class Writer implements Runnable {
    private PricesInfo pricesInfo;

    public Writer(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    /**
     * 实现run方法，它将循环修改 两个价格3次，每次修改后线程将休眠2秒钟。
     */
    @Override
    public void run() {
        for (int i =0; i<3;i++){
            System.out.printf("Writer : Attempt to modify the prices.\n");
            pricesInfo.setPrices(Math.random()*10,Math.random()*8);
            System.out.printf("Writer: Prices have been modified.\n");
            try {
                Thread.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

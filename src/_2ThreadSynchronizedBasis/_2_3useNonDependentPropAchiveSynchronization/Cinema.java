package _2ThreadSynchronizedBasis._2_3useNonDependentPropAchiveSynchronization;

/**
 * 用synchronized 关键字保护代码块时，使用对象作为它的传入参数。JVM保证同一时间只有一个线程能够访问这个对象的代码块(注意：是对象，而不是类)
 */
public class Cinema {
    private long vacanciesCinema1;
    private long vacanciesCinema2;
    private final Object controlCinema1,controlCinema2;

    public Cinema() {
        controlCinema1 = new Object();
        controlCinema2 = new Object();
        vacanciesCinema1 = 20;
        vacanciesCinema2 = 20;
    }
    //实现sellTickets1()方法，当第一个电影院有票卖出的时候将调用这个方法。
    // 使用controlCinema1 对象控制同步代码块的访问。
    public boolean sellTIckets1(int number){
        synchronized (controlCinema1){
            if(number <vacanciesCinema1){
                vacanciesCinema1 -= number;
                return true;
            }else{
                return false;
            }
        }
    }

    //实现sellTikckets2()方法，当第二个电影院有票卖出的时候将调用这个方法。
    //它使用controlCinema2 对象控制同步代码块的访问。
    public boolean sellTickets2 (int number){
        synchronized (controlCinema2){
            if(number < vacanciesCinema2){
                vacanciesCinema2 -= number;
                return true;

            }else{
                return false;
            }
        }
    }

    // 实现returnTickets1（）方法，当第一个电影院有票退回的时候将调用这个方法。
    //它使用controlCinema1对象控制 同步代码块的访问。
    public boolean returnTickets1(int number){
        synchronized (controlCinema1){
            vacanciesCinema1 += number;
            return  true;
        }
    }
    //实现returnTickets2（）方法，当第二个电影院有票退回的时候将调用这个方法。
    //它使用controlCinema2 对象控制同步代码块的访问。
    public boolean returnTickets2(int number){
        synchronized (controlCinema2){
            vacanciesCinema2 += number;
            return true;
        }
    }
    //实现另外两个方法，他们返回各自电影的票数

    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }
}

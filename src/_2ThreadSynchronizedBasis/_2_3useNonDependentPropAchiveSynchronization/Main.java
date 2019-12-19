package _2ThreadSynchronizedBasis._2_3useNonDependentPropAchiveSynchronization;

/**
 * 通过范例学习实现电影院售票场景的编程，这个范例模拟了有两个影厅和两个售票处的电影院。一个售票处卖出的一张票，只能用于其中要给电影院，不能同时用于两个电影院，因此每个影厅的剩余票数是独立的属性 。
 * 这个例子使用了一个对象来控制vacanciesCinema1属性的访问，所以同一时间只有
 * 一个线程能够修改这个属性；使用了另一个对象来控制vacanciesCinema2属性的访问，所以
 * 同一时间只有一个线程能够修改这个属性。但是这个例子允许同时运行两个线程:一个修改vacancesCinema1属性，
 * 另一个修改vacanciesCinema2属性。
 * 可以按到最终结果总是与每个电影院的剩余票数一致。
 */
public class Main {
    public static void main(String[] args) {
        Cinema cinema =  new Cinema();
        TicketOffice1 ticketOffice1 = new TicketOffice1(cinema);
        Thread thread1 = new Thread(ticketOffice1,"TicketOffice1");

        TicketOffice2 ticketOffice2 = new TicketOffice2(cinema);
        Thread thread2 = new Thread(ticketOffice2,"TicketOffice2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.printf("Room 1 Vacancies : %d\n",cinema.getVacanciesCinema1());
        System.out.printf("Room 2 Vacancies:  %d\n",cinema.getVacanciesCinema2());
    }
}

package _2ThreadSynchronizedBasis._2_3useNonDependentPropAchiveSynchronization;

public class TicketOffice2 implements Runnable {
    private Cinema cinema;

    public TicketOffice2(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * 实现run方法，它模拟了对两个电影院的操作 。
     */
    @Override
    public void run() {
        cinema.sellTickets2(2);
        cinema.sellTickets2(4);
        cinema.sellTIckets1(2);
        cinema.sellTIckets1(1);
        cinema.sellTickets2(2);
        cinema.sellTIckets1(3);
        cinema.sellTickets2(2);
        cinema.sellTIckets1(2);

    }
}

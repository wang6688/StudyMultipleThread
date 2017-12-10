package _2ThreadSynchronizedBasis._2_3useNonDependentPropAchiveSynchronization;

public class TicketOffice1 implements Runnable {
    private Cinema cinema;

    public TicketOffice1(Cinema cinema) {
        this.cinema = cinema;
    }

    /**
     * 实现run方法，它模拟了对两个电影院的操作。
     */
    @Override
    public void run() {
        cinema.sellTIckets1(3);
        cinema.sellTIckets1(2);
        cinema.sellTickets2(2);
        cinema.returnTickets1(3);
        cinema.sellTIckets1(5);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);

    }
}

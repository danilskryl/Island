import Animals.init.Initialization;
import Animals.init.Statistic;

public class Main {
    public static void main(String[] args) {
        new Initialization(10,10).init();
        new Thread(Statistic.getStatistic()).start();
    }
}
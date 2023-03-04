import Animals.init.Initialization;
import Animals.init.Statistic;

public class Main {
    public static void main(String[] args) {
        new Initialization(500,500).init();
        new Thread(new Statistic()).start();
    }
}
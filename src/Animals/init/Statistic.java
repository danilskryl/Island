package Animals.init;

import Animals.herbivores.Herbivore;
import Animals.interfaces.Animal;
import Animals.predators.Predator;
import lombok.Getter;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.Thread.sleep;

@Getter
public final class Statistic implements Runnable {
    public static CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>();
    private static Statistic statistic;
    private Statistic(){}
    public static Statistic getStatistic() {
        if (statistic == null) {
            statistic = new Statistic();
        }
        return statistic;
    }
    public void getStat() {
        System.out.printf("\nSTATISTIC:\nThe count of animals - %d\n", animals.size());
        System.out.printf("The count of predators - %d\n", getCountPredators());
        System.out.printf("The count of herbivores - %d\n\n", getCountHerbivore());
    }
    private long getCountPredators() {
        return animals.stream().filter(a -> a instanceof Predator).count();
    }
    private long getCountHerbivore() {
        return animals.stream().filter(a -> a instanceof Herbivore).count();
    }
    @Override
    public void run() {
        while (animals.size() > 0) {
            getStat();
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

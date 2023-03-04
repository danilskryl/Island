package Animals.init;

import Animals.herbivores.Herbivore;
import Animals.interfaces.Animal;
import Animals.predators.Predator;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

@Getter
public final class Statistic implements Runnable {
    public static List<Animal> animals = new ArrayList<>();

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
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

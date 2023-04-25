package statistic;

import animals.herbivores.Herbivore;
import animals.interfaces.Animal;
import animals.omnivorous.Omnivorous;
import animals.predators.Predator;
import lombok.Getter;

import java.util.concurrent.CopyOnWriteArrayList;


@Getter
public final class Statistic {
    private final CopyOnWriteArrayList<Animal> animals;

    public Statistic(CopyOnWriteArrayList<Animal> animals) {
        this.animals = animals;
    }

    private int day = 1;

    public void getStat() {
        System.out.println("\n".repeat(20));
        System.out.printf("\nSTATISTIC on %d day:\nThe count of animals - %d\n", day++, animals.size());
        System.out.printf("The count of predators - %d\n", getCountPredators());
        System.out.printf("The count of herbivores - %d\n", getCountHerbivore());
        System.out.printf("The count of omnivorous - %d\n\n\n", getCountOmnivorous());
    }

    private long getCountPredators() {
        return animals.stream().filter(a -> a instanceof Predator).count();
    }

    private long getCountHerbivore() {
        return animals.stream().filter(a -> a instanceof Herbivore).count();
    }

    private long getCountOmnivorous() {
        return animals.stream().filter(a -> a instanceof Omnivorous).count();
    }
}
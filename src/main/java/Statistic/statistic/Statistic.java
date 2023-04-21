package Statistic.statistic;

import Animals.herbivores.Herbivore;
import Animals.interfaces.Animal;
import Animals.omnivorous.Omnivorous;
import Animals.predators.Predator;
import lombok.Getter;

import java.util.concurrent.*;


@Getter
public final class Statistic {
    private final CopyOnWriteArrayList<Animal> animals;
    public Statistic(CopyOnWriteArrayList<Animal> animals) {
        this.animals = animals;
    }

    public void getStat() {
        System.out.printf("\nSTATISTIC:\nThe count of animals - %d\n", animals.size());
        System.out.printf("The count of predators - %d\n", getCountPredators());
        System.out.printf("The count of herbivores - %d\n", getCountHerbivore());
        System.out.printf("The count of omnivorous - %d\n\n", getCountOmnivorous());
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
package Island;

import Animals.grass.Grass;
import Animals.herbivores.*;
import Animals.interfaces.Animal;
import Animals.omnivorous.*;
import Animals.predators.*;
import Statistic.statistic.Statistic;
import lombok.Getter;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Getter
public final class Island {
    private final int countPredators;
    private final int countHerbivores;
    private final int countOmnivorous;
    private final int height;
    private final int width;
    private final Grass grass;
    private final CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>();
    private final ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(20);
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final Statistic statistic = new Statistic(animals);
    private final ReadWriteLock locker = new ReentrantReadWriteLock();
    public Island(int height, int width, int countPredators, int countHerbivores, int countOmnivorous) {
        this.height = height;
        this.width = width;
        this.countPredators = countPredators;
        this.countHerbivores = countHerbivores;
        this.countOmnivorous = countOmnivorous;
        grass = new Grass(height, width);
    }

    private void init() {
        for (int i = 0; i < countPredators; i++) createRandomPredator();
        for (int i = 0; i < countHerbivores; i++) createRandomHerbivore();
        for (int i = 0; i < countOmnivorous; i++) createRandomOmnivorous();
    }

    public void start() {
        init();
        executorService.scheduleAtFixedRate(statistic,0,3, TimeUnit.SECONDS);
    }

    private void createRandomPredator() {
        int num = random.nextInt(0, 5);
        switch (num) {
            case 0 -> new Wolf(this);
            case 1 -> new Bear(this);
            case 2 -> new Fox(this);
            case 3 -> new Eagle(this);
            case 4 -> new Anaconda(this);
        }
    }

    private void createRandomHerbivore() {
        int num = random.nextInt(0, 7);
        switch (num) {
            case 0 -> new Deer(this);
            case 1 -> new Buffalo(this);
            case 2 -> new Goat(this);
            case 3 -> new Sheep(this);
            case 4 -> new Horse(this);
            case 5 -> new Rabbit(this);
            case 6 -> new Caterpillar(this);
        }
    }

    private void createRandomOmnivorous() {
        int num = random.nextInt(0, 3);
        switch (num) {
            case 0 -> new Boar(this);
            case 1 -> new Duck(this);
            case 2 -> new Mouse(this);
        }
    }

    public CopyOnWriteArrayList<Animal> getAnimals() {
        return animals;
    }
}
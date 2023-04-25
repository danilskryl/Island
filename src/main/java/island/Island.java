package island;

import animals.herbivores.*;
import animals.interfaces.Animal;
import animals.omnivorous.Boar;
import animals.omnivorous.Duck;
import animals.omnivorous.Mouse;
import animals.predators.*;
import statistic.Statistic;
import grass.Grass;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Getter
public final class Island implements Runnable {
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
    private static final Logger LOGGER = LoggerFactory.getLogger(Island.class);

    public Island(int height, int width, int countPredators, int countHerbivores, int countOmnivorous) {
        this.height = height;
        this.width = width;
        this.countPredators = countPredators;
        this.countHerbivores = countHerbivores;
        this.countOmnivorous = countOmnivorous;
        grass = new Grass(height, width);
        LOGGER.debug("Created island with height - {} and width - {}. " +
                        "Amount of predators - {}, herbivores - {}, omnivorous - {}",
                height, width, countPredators, countHerbivores, countOmnivorous);
    }

    private void init() {
        for (int i = 0; i < countPredators; i++) createRandomPredator();
        for (int i = 0; i < countHerbivores; i++) createRandomHerbivore();
        for (int i = 0; i < countOmnivorous; i++) createRandomOmnivorous();
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

    @Override
    public void run() {
        LOGGER.debug("Island is running");
        init();
        while (animals.size() > 0) {
            statistic.getStat();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("All animals is died");
        LOGGER.info("All animals is died");
        System.exit(0);
    }
}
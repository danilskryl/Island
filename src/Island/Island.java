package Island;

import Animals.herbivores.*;
import Animals.interfaces.Animal;
import Animals.omnivorous.Boar;
import Animals.omnivorous.Duck;
import Animals.omnivorous.Mouse;
import Animals.omnivorous.Omnivorous;
import Animals.predators.*;
import Statistic.statistic.Statistic;
import lombok.Getter;
import lombok.Setter;
import java.util.concurrent.*;

@Getter
@Setter
public final class Island {
    private int countPredators;
    private int countHerbivores;
    private int countOmnivorous;
    private int height;
    private int width;
    private int[][] field = new int[height][width];
    private CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Predator> predators = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Herbivore> herbivores = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Omnivorous> omnivorous = new CopyOnWriteArrayList<>();
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private Statistic statistic = new Statistic(animals);
    public Island(int height, int width,int countPredators, int countHerbivores, int countOmnivorous) {
        this.height = height;
        this.width = width;
        this.countPredators = countPredators;
        this.countHerbivores = countHerbivores;
        this.countOmnivorous = countOmnivorous;
    }
    public void start() {
        init();
        new Thread(statistic).start();
    }
    private void init() {
        for (int i = 0; i < countPredators; i++)  createRandomPredator();
        for (int i = 0; i < countHerbivores; i++) createRandomHerbivore();
        for (int i = 0; i < countOmnivorous; i++) createRandomOmnivorous();
    }
    private void createRandomPredator() {
        int num = random.nextInt(0,5);
        switch (num) {
            case 0 -> new Wolf(this);
            case 1 -> new Bear(this);
            case 2 -> new Fox(this);
            case 3 -> new Eagle(this);
            case 4 -> new Anaconda(this);
        }
    }
    private void createRandomHerbivore() {
        int num = random.nextInt(0,7);
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
        int num = random.nextInt(0,3);
        switch (num) {
            case 0 -> new Boar(this);
            case 1 -> new Duck(this);
            case 2 -> new Mouse(this);
        }
    }
    public synchronized CopyOnWriteArrayList<Animal> getAnimals() {
        return animals;
    }
}

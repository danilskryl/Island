package Animals.init;

import Animals.herbivores.*;
import Animals.predators.*;
import lombok.RequiredArgsConstructor;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class Initialization {
    private final int countPredators;
    private final int countHerbivores;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    public void init() {
        for (int i = 0; i < countPredators; i++) createRandomPredator();
        for (int i = 0; i < countHerbivores; i++) createRandomHerbivore();
    }
    private void createRandomPredator() {
        int num = random.nextInt(0,5);
        switch (num) {
            case 0 -> new Wolf();
            case 1 -> new Bear();
            case 2 -> new Fox();
            case 3 -> new Eagle();
            case 4 -> new Anaconda();
        }
    }
    private void createRandomHerbivore() {
        int num = random.nextInt(0,7);
        switch (num) {
            case 0 -> new Deer();
            case 1 -> new Buffalo();
            case 2 -> new Goat();
            case 3 -> new Sheep();
            case 4 -> new Horse();
            case 5 -> new Rabbit();
            case 6 -> new Caterpillar();
        }
    }
}

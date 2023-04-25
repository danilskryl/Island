package animals.predators;

import island.Island;

import java.util.Map;

public class Bear extends Predator {
    public Bear(Island island) {
        super(island);
        weight = 500.0;
        maxCountOnCell = 5;
        speed = 2;
        kgToFedUp = 80.0;
        animalsEaten = Map.of(
                "Anaconda", 80,
                "Horse", 40,
                "Deer", 80,
                "Rabbit", 80,
                "Mouse", 90,
                "Duck", 10,
                "Buffalo", 20,
                "Boar", 50,
                "Sheep", 70,
                "Goat", 70
        );
    }
}

package animals.predators;

import island.Island;

import java.util.Map;

public class Eagle extends Predator {
    public Eagle(Island island) {
        super(island);
        weight = 6.0;
        maxCountOnCell = 20;
        speed = 3;
        kgToFedUp = 1.0;
        animalsEaten = Map.of(
                "Fox", 10,
                "Rabbit", 90,
                "Mouse", 90,
                "Duck", 80
        );
    }
}

package Animals.omnivorous;

import Island.Island;

import java.util.Map;

public class Duck extends Omnivorous {
    public Duck(Island island) {
        super(island);
        weight = 1;
        maxCountOnCell = 200;
        speed = 4;
        kgToFedUp = 0.15d;
        animalsEaten = Map.of(
                "Caterpillar", 90
        );
    }
}

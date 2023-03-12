package Animals.omnivorous;

import Animals.Island;

import java.util.Map;

public class Mouse extends Omnivorous {
    public Mouse(Island island) {
        super(island);
        weight = 0.05;
        maxCountOnCell = 500;
        speed = 1;
        kgToFedUp = 0.01;
        animalsEaten = Map.of(
                "Caterpillar",90
        );
    }
}

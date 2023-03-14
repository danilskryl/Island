package Animals.herbivores;

import Island.Island;
import java.util.Map;

public class Horse extends Herbivore {
    public Horse(Island island) {
        super(island);
        weight = 400.0;
        maxCountOnCell = 20;
        speed = 4;
        kgToFedUp = 60.0;
        animalsEaten = Map.of();
    }
}

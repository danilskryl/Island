package Animals.herbivores;

import Island.Island;

public class Buffalo extends Herbivore {
    public Buffalo(Island island) {
        super(island);
        weight = 700.0;
        maxCountOnCell = 10;
        speed = 3;
        kgToFedUp = 100.0;
    }
}

package Animals.herbivores;

import Animals.Island;

public class Goat extends Herbivore {
    public Goat(Island island) {
        super(island);
        weight = 60.0;
        maxCountOnCell = 140;
        speed = 3;
        kgToFedUp = 10.0;
    }
}

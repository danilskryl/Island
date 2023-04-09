package Animals.herbivores;

import Island.Island;

public class Rabbit extends Herbivore {
    public Rabbit(Island island) {
        super(island);
        weight = 2.0;
        maxCountOnCell = 150;
        speed = 2;
        kgToFedUp = 0.45;
    }
}

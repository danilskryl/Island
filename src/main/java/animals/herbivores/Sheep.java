package animals.herbivores;

import island.Island;

public class Sheep extends Herbivore {
    public Sheep(Island island) {
        super(island);
        weight = 70.0;
        maxCountOnCell = 140;
        speed = 3;
        kgToFedUp = 15.0;
    }
}

package animals.herbivores;

import island.Island;

public class Deer extends Herbivore {
    public Deer(Island island) {
        super(island);
        weight = 300.0;
        maxCountOnCell = 20;
        speed = 4;
        kgToFedUp = 50.0;
    }
}

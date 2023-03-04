package Animals.herbivores;

import Animals.interfaces.AbleToEatGrass;

public class Caterpillar extends Herbivore implements AbleToEatGrass {
    public Caterpillar() {
        weight = 0.01;
        maxCountOnCell = 1000;
        speed = 0;
        kgToFedUp = 0;
    }
}

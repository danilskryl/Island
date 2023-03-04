package Animals.herbivores;

import Animals.interfaces.AbleToEatGrass;

public class Horse extends Herbivore implements AbleToEatGrass {
    public Horse() {
        weight = 400.0;
        maxCountOnCell = 20;
        speed = 4;
        kgToFedUp = 60.0;
    }
}

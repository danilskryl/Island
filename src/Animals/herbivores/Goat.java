package Animals.herbivores;

import Animals.interfaces.AbleToEatGrass;

public class Goat extends Herbivore implements AbleToEatGrass {
    public Goat() {
        weight = 60.0;
        maxCountOnCell = 140;
        speed = 3;
        kgToFedUp = 10.0;
    }
}

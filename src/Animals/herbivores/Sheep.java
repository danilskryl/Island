package Animals.herbivores;

import Animals.interfaces.AbleToEatGrass;

public class Sheep extends Herbivore implements AbleToEatGrass {
    public Sheep() {
        weight = 70.0;
        maxCountOnCell = 140;
        speed = 3;
        kgToFedUp = 15.0;
    }
}

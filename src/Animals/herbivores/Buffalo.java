package Animals.herbivores;

import Animals.interfaces.AbleToEatGrass;

public class Buffalo extends Herbivore implements AbleToEatGrass {
    public Buffalo() {
        weight = 700.0;
        maxCountOnCell = 10;
        speed = 3;
        kgToFedUp = 100.0;
    }
}

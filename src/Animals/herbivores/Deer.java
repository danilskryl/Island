package Animals.herbivores;

import Animals.interfaces.AbleToEatGrass;

public class Deer extends Herbivore implements AbleToEatGrass {
    public Deer() {
        weight = 300.0;
        maxCountOnCell = 20;
        speed = 4;
        kgToFedUp = 50.0;
    }
}

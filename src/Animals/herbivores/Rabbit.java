package Animals.herbivores;

import Animals.interfaces.AbleToEatGrass;

public class Rabbit extends Herbivore implements AbleToEatGrass {
    public Rabbit() {
        weight = 2.0;
        maxCountOnCell = 150;
        speed = 2;
        kgToFedUp = 0.45;
    }
}

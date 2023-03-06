package Animals.herbivores;

import Animals.interfaces.AbleToEatGrass;
import Animals.interfaces.Animal;
import Animals.threadFactory.AnimalFactory;

public abstract class Herbivore extends Animal implements AbleToEatGrass {
    public Herbivore() {
        super();
        AnimalFactory.herbivores.add(this);
    }
}
package Animals.predators;

import Animals.interfaces.AbleToEatMeat;
import Animals.interfaces.Animal;
import Animals.threadFactory.AnimalFactory;

public abstract class Predator extends Animal implements AbleToEatMeat {
    public Predator() {
        super();
        AnimalFactory.predators.add(this);
    }
}

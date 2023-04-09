package Animals.predators;

import Animals.interfaces.Animal;
import Island.Island;

public abstract class Predator extends Animal {
    public Predator(Island island) {
        super(island);
    }
}
package Animals.predators;

import Animals.Island;
import Animals.interfaces.Animal;

public abstract class Predator extends Animal {
    public Predator(Island island) {
        super(island);
    }
}
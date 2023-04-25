package animals.predators;

import animals.interfaces.Animal;
import island.Island;

public abstract class Predator extends Animal {
    public Predator(Island island) {
        super(island);
    }
}
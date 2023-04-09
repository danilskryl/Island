package Animals.herbivores;

import Animals.interfaces.Animal;
import Island.Island;

public abstract class Herbivore extends Animal {
    public Herbivore(Island island) {
        super(island);
    }

    @Override
    public void eat() {
        if (getFullness() > 80) return;
        double[][] grass = getIsland().getGrass().getField();
        if (grass[getX()][getY()] >= getKgToFedUp()) {
            getIsland().getGrass().decrement(getX(), getY(), getKgToFedUp() / 5);
            setFullness(getFullness() + getKgToFedUp() / 10);
        }
    }
}
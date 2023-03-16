package Animals.herbivores;

import Island.Island;
import Animals.grass.Grass;
import Animals.interfaces.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(Island island) {
        super(island);
    }

    @Override
    public void eat() {
        if (getFullness() > 80) return;
        double[][] grass = Grass.getGrass().getField();
        if (grass[getX()][getY()] >= getKgToFedUp()) {
            Grass.getGrass().decrement(getX(),getY(),getKgToFedUp() / 5);
            setFullness(getFullness() + getKgToFedUp()/ 10);
        }
    }
}
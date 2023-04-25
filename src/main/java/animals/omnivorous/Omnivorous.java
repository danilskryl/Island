package animals.omnivorous;

import animals.interfaces.Animal;
import island.Island;

import java.util.List;

public abstract class Omnivorous extends Animal {

    public Omnivorous(Island island) {
        super(island);
    }

    @Override
    public synchronized void eat() {
        if (getFullness() >= 100) return;
        List<Animal> listAnimals = animalsOnCellWhichCanEat(this);
        if (listAnimals.size() == 0) return;

        int animalIndex = getRandom().nextInt(0, listAnimals.size());
        int chance = getRandom().nextInt(1, 101);
        Animal animal = listAnimals.get(animalIndex);
        if (chance < getAnimalsEaten().get(animal.getClass().getSimpleName())) {
            setFullness(getFullness() + (getKgToFedUp() / animal.getWeight()));
            if (getFullness() > 100) setFullness(100);
            animal.die();
        } else {
            double[][] grass = getIsland().getGrass().getField();
            if (grass[getX()][getY()] >= getKgToFedUp()) {
                getIsland().getGrass().decrement(getX(), getY(), getKgToFedUp() / 10);
                setFullness(getFullness() + getKgToFedUp() / 10);
            }
        }
    }
}
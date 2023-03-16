package Animals.omnivorous;

import Island.Island;
import Animals.grass.Grass;
import Animals.interfaces.Animal;
import java.util.List;

public abstract class Omnivorous extends Animal {

    public Omnivorous(Island island) {
        super(island);
    }

    @Override
    public synchronized void eat() {
        if (getFullness() >= 100) return;
        List<Animal> animalsOnCellWhichCanEat = getIsland().getAnimals().stream()
                .filter(h -> h.getX() == this.getX() && h.getY() == this.getY())
                .filter(h -> animalsEaten.containsKey(h.getClass().getSimpleName()))
                .toList();
        if (!(animalsOnCellWhichCanEat.size() == 0)) {
            int animalIndex = getRandom().nextInt(0, animalsOnCellWhichCanEat.size());
            int chance = getRandom().nextInt(1, 101);
            Animal animal = animalsOnCellWhichCanEat.get(animalIndex);
            if (chance < this.getAnimalsEaten().get(animal.getClass().getSimpleName())) {
                this.setFullness(getFullness() + (this.getKgToFedUp() / animal.getWeight()));
                if (this.getFullness() > 100) this.setFullness(100);
                animal.die();
            }
        } else {
            double[][] grass = Grass.getGrass().getField();
            if (grass[getX()][getY()] >= getKgToFedUp()) {
                Grass.getGrass().decrement(getX(),getY(),getKgToFedUp() / 10);
                setFullness(getFullness() + getKgToFedUp()/ 10);
            }
        }
    }
}
package Animals.herbivores;
import Island.Island;

public class Caterpillar extends Herbivore {
    public Caterpillar(Island island) {
        super(island);
        weight = 0.01;
        maxCountOnCell = 1000;
        speed = 1;
        kgToFedUp = 0;
    }
}

package Animals.predators;

import Island.Island;
import java.util.Map;

public class Anaconda extends Predator {
    public Anaconda(Island island) {
        super(island);
        weight = 15.0;
        maxCountOnCell = 30;
        speed = 1;
        kgToFedUp = 3;
        animalsEaten = Map.of(
                "Fox",15,
                "Rabbit",20,
                "Mouse",40,
                "Duck",10
        );
    }
}

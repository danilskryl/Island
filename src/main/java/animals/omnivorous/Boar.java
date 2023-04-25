package animals.omnivorous;

import island.Island;

import java.util.Map;

public class Boar extends Omnivorous {
    public Boar(Island island) {
        super(island);
        weight = 400;
        maxCountOnCell = 50;
        speed = 2;
        kgToFedUp = 50;
        animalsEaten = Map.of(
                "Mouse", 50,
                "Caterpillar", 90
        );
    }
}

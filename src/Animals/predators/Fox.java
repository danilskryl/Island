package Animals.predators;
import Animals.interfaces.AbleToEatMeat;

import java.util.Map;

public class Fox extends Predator implements AbleToEatMeat {
    public Fox() {
        weight = 8.0;
        maxCountOnCell = 30;
        speed = 2;
        kgToFedUp = 2.0;
        animalsEaten = Map.of(
               "Rabbit",70,
               "Mouse",90,
               "Duck",60,
               "Caterpillar",40
        );
    }
}

package Animals.predators;
import Animals.interfaces.AbleToEatMeat;

import java.util.Map;

public class Eagle extends Predator implements AbleToEatMeat {
    public Eagle() {
        weight = 6.0;
        maxCountOnCell = 20;
        speed = 3;
        kgToFedUp = 1.0;
        animalsEaten = Map.of(
                "Fox",10,
                "Rabbit",90,
                "Mouse",90,
                "Duck",80
        );
    }
}
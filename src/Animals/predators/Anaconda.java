package Animals.predators;
import Animals.interfaces.AbleToEatMeat;

import java.util.Map;

public class Anaconda extends Predator implements AbleToEatMeat {
    public Anaconda() {
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

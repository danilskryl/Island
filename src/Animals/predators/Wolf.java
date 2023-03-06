package Animals.predators;
import Animals.interfaces.AbleToEatMeat;

import java.util.Map;

public class Wolf extends Predator implements AbleToEatMeat {
    public Wolf() {
        speed = 3;
        weight = 50.0;
        maxCountOnCell = 30;
        kgToFedUp = 8.0;
        animalsEaten = Map.of(
                "Horse",10,
                "Deer",15,
                "Rabbit",60,
                "Mouse",80,
                "Goat",60,
                "Sheep",70,
                "Boar",15,
                "Buffalo",10,
                "Duck",40
        );
    }
}

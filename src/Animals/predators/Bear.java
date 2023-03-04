package Animals.predators;
import Animals.interfaces.AbleToEatMeat;

public class Bear extends Predator implements AbleToEatMeat {
    public Bear() {
        weight = 500.0;
        maxCountOnCell = 5;
        speed = 2;
        kgToFedUp = 80.0;
    }
}

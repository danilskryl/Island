package Animals.predators;
import Animals.interfaces.AbleToEatMeat;

public class Fox extends Predator implements AbleToEatMeat {
    public Fox() {
        weight = 8.0;
        maxCountOnCell = 30;
        speed = 2;
        kgToFedUp = 2.0;
    }
}

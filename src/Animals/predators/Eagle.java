package Animals.predators;
import Animals.interfaces.AbleToEatMeat;

public class Eagle extends Predator implements AbleToEatMeat {
    public Eagle() {
        weight = 6.0;
        maxCountOnCell = 20;
        speed = 3;
        kgToFedUp = 1.0;
    }
}

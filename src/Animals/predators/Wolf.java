package Animals.predators;
import Animals.interfaces.AbleToEatMeat;

public class Wolf extends Predator implements AbleToEatMeat {
    public Wolf() {
        speed = 3;
        weight = 50.0;
        maxCountOnCell = 30;
        kgToFedUp = 8.0;
    }
}

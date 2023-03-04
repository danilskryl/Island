package Animals.predators;
import Animals.interfaces.AbleToEatMeat;

public class Anaconda extends Predator implements AbleToEatMeat {
    public Anaconda() {
        weight = 15.0;
        maxCountOnCell = 30;
        speed = 1;
        kgToFedUp = 3;
    }
}

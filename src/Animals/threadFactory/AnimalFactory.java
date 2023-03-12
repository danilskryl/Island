package Animals.threadFactory;

import Animals.herbivores.Herbivore;
import Animals.omnivorous.Omnivorous;
import Animals.predators.Predator;
import lombok.Getter;

@Getter
public class AnimalFactory {
    public Thread newThread(Runnable r) {
        if (r instanceof Predator) {
            return new Thread(r);
        } else if (r instanceof Herbivore) {
            return new Thread(r);
        } else if (r instanceof Omnivorous) {
            return new Thread(r);
        }
        return null;
    }
}

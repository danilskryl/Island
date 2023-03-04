package Animals.threadFactory;

import Animals.herbivores.Herbivore;
import Animals.predators.Predator;

public class AnimalFactory {
    public Thread newThread(Runnable r) {
        if (r instanceof Predator) {
            Thread thread = new Thread(r);
            Predator.predators.add(thread);
            return thread;
        } else if (r instanceof Herbivore) {
            Thread thread = new Thread(r);
            Herbivore.herbivores.add(thread);
            return thread;
        }
        return null;
    }
}

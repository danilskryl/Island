package Animals.threadFactory;

import Animals.herbivores.Herbivore;
import Animals.interfaces.Animal;
import Animals.omnivorous.Omnivorous;
import Animals.predators.Predator;
import java.util.concurrent.CopyOnWriteArrayList;

public class AnimalFactory {
    public static CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Predator> predators = new CopyOnWriteArrayList<>();
    public static CopyOnWriteArrayList<Herbivore> herbivores = new CopyOnWriteArrayList<>();
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

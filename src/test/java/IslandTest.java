import animals.herbivores.Herbivore;
import animals.omnivorous.Omnivorous;
import animals.predators.Predator;
import island.Island;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IslandTest {
    Island island;
    int height = 40;
    final int width = 30;
    int countPredators = 10;
    int countOmnivorous = 10;
    int countHerbivores = 10;

    @BeforeEach
    public void createIsland() {
        island = new Island(height, width, countPredators, countHerbivores, countOmnivorous);
        makeInit(island);
    }

    @Test
    void countAnimals() {
        assertEquals(30, island.getAnimals().size());
    }

    @Test
    void countPredators() {
        assertEquals(10, island.getAnimals().stream().filter(a -> a instanceof Predator).count());
    }

    @Test
    void countHerbivores() {
        assertEquals(10, island.getAnimals().stream().filter(a -> a instanceof Herbivore).count());
    }

    @Test
    void countOmnivorous() {
        assertEquals(10, island.getAnimals().stream().filter(a -> a instanceof Omnivorous).count());
    }

    private void makeInit(Island island) {
        try {
            Method method = island.getClass().getDeclaredMethod("init");
            method.setAccessible(true);
            method.invoke(island);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
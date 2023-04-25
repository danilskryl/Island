import animals.omnivorous.Mouse;
import animals.predators.Wolf;
import island.Island;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalTest {
    int x = 50;
    int y = 50;
    Wolf wolf;
    Wolf wolfFemale;
    Island island = new Island(100, 100, 0, 0, 0);

    @BeforeEach
    void initWolfs() {
        wolf = new Wolf(island);
        wolfFemale = new Wolf(island);
        wolf.setX(x);
        wolf.setY(y);
        wolfFemale.setX(x);
        wolfFemale.setY(y);
    }

    @Test
    void reproductTest() {
        wolf.setSex(false);
        wolfFemale.setSex(true);
        wolfFemale.setTimeToReproduct(0);
        wolfFemale.reproduct();
        Assertions.assertEquals(3, island.getAnimals().size());
    }

    @Test
    void moveTest() {
        wolf.move();
        assertTrue(wolf.getX() != x || wolf.getY() != y);
        wolfFemale.move();
        assertTrue(wolfFemale.getX() != x || wolfFemale.getY() != y);
    }

    @Test
    void eatTest() {
        Mouse mouse = new Mouse(island);
        mouse.setX(x);
        mouse.setY(y);
        wolf.setFullness(60);
        for (int i = 0; i < 5; i++) {
            wolf.eat();
        }
        Assertions.assertEquals(2, island.getAnimals().size());
    }
}

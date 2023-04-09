import Animals.omnivorous.Mouse;
import Animals.predators.Wolf;
import Island.Island;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnimalTest {
    int x = 50;
    int y = 50;
    Wolf wolfMale;
    Wolf wolfFemale;
    Island island = new Island(100, 100, 0, 0, 0);

    @BeforeEach
    void initWolfs() {
        wolfMale = new Wolf(island);
        wolfFemale = new Wolf(island);
        wolfMale.setX(x);
        wolfMale.setY(y);
        wolfFemale.setX(x);
        wolfFemale.setY(y);
    }

    @Test
    void reproductTest() {
        wolfMale.setSex(false);
        wolfFemale.setSex(true);
        wolfFemale.setTimeToReproduct(0);
        wolfFemale.reproduct();
        Assertions.assertEquals(3, island.getAnimals().size());
    }

    @Test
    void moveTest() {
        wolfMale.move();
        Assertions.assertTrue(wolfMale.getX() != x || wolfMale.getY() != y);
        wolfFemale.move();
        Assertions.assertTrue(wolfFemale.getX() != x || wolfFemale.getY() != y);
    }

    @Test
    void eatTest() {
        Mouse mouse = new Mouse(island);
        mouse.setX(x);
        mouse.setY(y);
        wolfMale.setFullness(60);
        for (int i = 0; i < 5; i++) {
            wolfMale.eat();
        }
        Assertions.assertEquals(2,island.getAnimals().size());
    }
}

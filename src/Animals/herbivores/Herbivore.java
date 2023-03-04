package Animals.herbivores;

import Animals.init.Statistic;
import Animals.interfaces.AbleToEatGrass;
import Animals.interfaces.Animal;
import Animals.threadFactory.AnimalFactory;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

@Getter
@Setter
public abstract class Herbivore implements Animal, AbleToEatGrass, Runnable {
    public static List<Thread> herbivores = new ArrayList<>();
    public static List<Herbivore> herbivoreList = new ArrayList<>();
    private int x;
    private int y;
    protected int speed;
    protected double weight;
    protected int maxCountOnCell;
    protected double kgToFedUp;
    protected double fullness = 100;
    protected boolean isAlive = true;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private AnimalFactory animalFactory = new AnimalFactory();
    public Herbivore() {
        setX(random.nextInt(40));
        setY(random.nextInt(100));
        animalFactory.newThread(this).start();
        herbivoreList.add(this);
        Statistic.animals.add(this);
    }
    @Override
    public void move() {
        int turn = random.nextInt(4);
        if (turn == 0) {
            y += speed;
        } else if (turn == 1) {
            y -= speed;
        } else if (turn == 2) {
            x += speed;
        } else {
            x -= speed;
        }
    }
    @Override
    public void reproduct() {
    }
    @Override
    public void eat() {

    }
    public void die() {
        Statistic.animals.remove(this);
        isAlive = false;
    }
    public void reduction() {
        fullness = fullness - (speed * weight / 100);
        if (fullness <= 0) {
            die();
        }
    }

    @Override
    public void run() {
        while (isAlive) {
            move();
            reduction();
            eat();
            reproduct();
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

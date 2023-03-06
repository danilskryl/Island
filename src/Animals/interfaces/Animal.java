package Animals.interfaces;

import Animals.init.Statistic;
import Animals.predators.Predator;
import Animals.threadFactory.AnimalFactory;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import static java.lang.Thread.sleep;

@Getter
@Setter
public abstract class Animal implements Runnable, Cloneable {
    private int x;
    private int y;
    protected int speed;
    protected double weight;
    protected int maxCountOnCell;
    protected double kgToFedUp;
    protected volatile double fullness = 100;
    protected boolean isAlive = true;
    private boolean sex;
    private int timeToReproduct = 2;
    protected Map<String,Integer> animalsEaten = new HashMap<>();
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private static AnimalFactory animalFactory = new AnimalFactory();
    public Animal() {
        setX(random.nextInt(40));
        setY(random.nextInt(100));
        setSex(random.nextBoolean());
        AnimalFactory.animals.add(this);
        animalFactory.newThread(this).start();
        Statistic.animals.add(this);
    }
    public void move() {
        AtomicInteger turn = new AtomicInteger(random.nextInt(4));
        if (turn.get() == 0) {
            y += speed;
        } else if (turn.get() == 1) {
            y -= speed;
        } else if (turn.get() == 2) {
            x += speed;
        } else {
            x -= speed;
        }
    }
    public void reproduct() {
        long animalsOnCell = AnimalFactory.animals.stream()
                .filter(p -> p.getX() == getX() && p.getY() == getY() && !p.isSex())
                .filter(p -> p.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
                .count();
        if (!(animalsOnCell == 0)) {
            Predator child = this.clone();
            System.out.println(this.getClass().getSimpleName() + " was born");
            animalFactory.newThread(child).start();
            AnimalFactory.predators.add(child);
            AnimalFactory.animals.add(child);
            Statistic.animals.add(child);
            setTimeToReproduct(5);
        }
    }
    public void eat() {
        List<Animal> animalsOnCellWhichCanEat = AnimalFactory.animals.stream()
                .filter(h -> h.getX() == this.getX() && h.getY() == this.getY())
                .filter(h -> animalsEaten.containsKey(h.getClass().getSimpleName()))
                .toList();
        if (!(animalsOnCellWhichCanEat.size() == 0)) {
            int animalIndex = random.nextInt(0, animalsOnCellWhichCanEat.size());
            int chance = random.nextInt(1, 101);
            Animal animal = animalsOnCellWhichCanEat.get(animalIndex);
            if (chance < this.getAnimalsEaten().get(animal.getClass().getSimpleName())) {
                this.setFullness(getFullness() + (this.getKgToFedUp() / animal.getWeight()));
                if (this.getFullness() > 100) this.setFullness(100);
                animal.die();
                System.out.println(this.getClass().getSimpleName() + " eat " + animal.getClass().getSimpleName());
            }
        }
    }
    public void die() {
        System.out.println(this.getClass().getSimpleName() + " died of hunger");
        Statistic.animals.remove(this);
        isAlive = false;
    }
    public void reduction() {
        setFullness(getFullness() - (speed * weight / 100));
        if (fullness <= 0) {
            die();
        }
    }
    @Override
    public Predator clone() {
        try {
            Predator clone = (Predator) super.clone();
            clone.setFullness(100);
            clone.setX(this.x);
            clone.setY(this.y);
            clone.setSpeed(this.getSpeed());
            clone.setKgToFedUp(this.getKgToFedUp());
            clone.setWeight(this.getWeight());
            clone.setSex(random.nextBoolean());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    public synchronized double getFullness() {
        return fullness;
    }
    public synchronized void setFullness(double fullness) {
        this.fullness = fullness;
    }
    @Override
    public void run() {
        while (isAlive) {
            move();
            reduction();
            eat();
            move();
            if (sex && timeToReproduct == 0) {
                reproduct();
            }
            setTimeToReproduct(timeToReproduct - 1);
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package Animals.interfaces;

import Island.Island;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public abstract class Animal implements Organism, Runnable, Cloneable {
    private int x;
    private int y;
    public boolean sex;
    protected double weight;
    protected double kgToFedUp;
    protected double speed;
    protected int maxCountOnCell;
    private boolean isAlive = true;
    private volatile double fullness = 100;
    protected Map<String, Integer> animalsEaten = new HashMap<>();
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private int timeToReproduct = 0;
    private Island island;

    public Animal(Island island) {
        this.island = island;
        setX(random.nextInt(0, getIsland().getHeight()));
        setY(random.nextInt(0, getIsland().getWidth()));
        setSex(random.nextBoolean());
        getIsland().getAnimals().add(this);
        getIsland().getExecutorService().scheduleAtFixedRate(this,0,2,TimeUnit.SECONDS);
    }
    @Override
    public synchronized void move() {
        boundsCheck();
        if (getSpeed() == 0) return;
        AtomicInteger turn = new AtomicInteger(random.nextInt(0, (int) speed));
        if (turn.get() == 0) {
            y += getSpeed();
        } else if (turn.get() == 1) {
            y -= getSpeed();
        } else if (turn.get() == 2) {
            x += getSpeed();
        } else {
            x -= getSpeed();
        }
        boundsCheck();
        if (getCountAnimalsOnCell() > getMaxCountOnCell()) {
            move();
        }
        //System.out.println("move" + getClass().getSimpleName());
    }
    public long getCountAnimalsOnCell() {
        return getIsland().getAnimals().stream()
                .filter(a -> a.getX() == getX() && a.getY() == getY() && !a.isSex())
                .filter(p -> p.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
                .count();
    }
    @Override
    public synchronized void reproduct() {
        if (!(getCountAnimalsOnCell() == 0)) {
            Animal child = this.clone();
            System.out.println(this.getClass().getSimpleName() + " was born");
            getIsland().getAnimals().add(child);
            getIsland().getExecutorService().scheduleAtFixedRate(child, 0, 2, TimeUnit.SECONDS);
            setTimeToReproduct(5);
        }
    }
    @Override
    public synchronized void eat() {
        if (getFullness() >= 100) return;
        List<Animal> animalsOnCellWhichCanEat = getIsland().getAnimals().stream()
                .filter(h -> h.getX() == this.getX() && h.getY() == this.getY())
                .filter(h -> getAnimalsEaten().containsKey(h.getClass().getSimpleName()))
                .toList();
        if (!(animalsOnCellWhichCanEat.size() == 0)) {
            int animalIndex = getRandom().nextInt(0, animalsOnCellWhichCanEat.size());
            int chance = getRandom().nextInt(1, 101);
            Animal animal = animalsOnCellWhichCanEat.get(animalIndex);
            if (chance < this.getAnimalsEaten().get(animal.getClass().getSimpleName())) {
                this.setFullness(getFullness() + (this.getKgToFedUp() / animal.getWeight()));
                if (this.getFullness() > 100) this.setFullness(100);
                animal.die();
                System.out.println(getClass().getSimpleName() + " eat " + animal.getClass().getSimpleName());
            }
        }
    }
    public synchronized void die() {
        getIsland().getAnimals().remove(this);
        setAlive(false);
        Thread.currentThread().interrupt();
        //System.out.println("die " + getClass().getSimpleName());
    }
    private synchronized void reduction() {
        setFullness(getFullness() - (getSpeed() * getWeight() / 200));
        if (getFullness() <= 0) {
            die();
        }
    }
    private synchronized void boundsCheck() {
        if (getX() > getIsland().getHeight())
            setX(getX() - getIsland().getHeight());
        else if (getX() < 0)
            setX(getX() + getIsland().getHeight());
        if (getY() > getIsland().getWidth())
            setY(getY() - getIsland().getWidth());
        else if (getY() < 0)
            setY(getY() + getIsland().getWidth());
    }
    protected synchronized double getFullness() {
        return fullness;
    }
    protected synchronized void setFullness(double fullness) {
        this.fullness = fullness;
    }
    @Override
    public Animal clone() {
        try {
            return (Animal) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    @Override
    public void run() {
        move();
        eat();
        reduction();
        if (isSex() && getTimeToReproduct() == 0) {
            reproduct();
        }
        if (getTimeToReproduct() > 0) {
            setTimeToReproduct(getTimeToReproduct() - 1);
        }
        if (getX() > getIsland().getHeight()) {
            System.out.println("error XX");
        }
        if (getY() > getIsland().getWidth()) {
            System.out.println("error YY");
        }
    }
}
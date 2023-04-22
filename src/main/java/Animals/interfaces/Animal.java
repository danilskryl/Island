package Animals.interfaces;

import Island.Island;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public abstract class Animal implements Organism, Runnable, Cloneable {
    private int x;
    private int y;
    private boolean sex;
    protected double weight;
    protected double kgToFedUp;
    protected double speed;
    protected int maxCountOnCell;
    private double fullness = 100;
    protected Map<String, Integer> animalsEaten = new HashMap<>();
    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private int timeToReproduct = 3;
    private Island island;
    private ScheduledFuture<?> task;
    private static final Logger logger = LoggerFactory.getLogger(Animal.class);

    public Animal(Island island) {
        this.island = island;
        setX(random.nextInt(0, getIsland().getHeight()));
        setY(random.nextInt(0, getIsland().getWidth()));
        setSex(random.nextBoolean());
        getIsland().getLocker().writeLock().lock();
        getIsland().getAnimals().add(this);
        task = getIsland().getExecutorService().scheduleAtFixedRate(this, 100, 2000, TimeUnit.MILLISECONDS);
        getIsland().getLocker().writeLock().unlock();
    }

    @Override
    public void move() {
        if (getSpeed() == 0) return;
        int turn = random.nextInt(0, 4);

        if (turn == 0)      y += getSpeed();
        else if (turn == 1) y -= getSpeed();
        else if (turn == 2) x += getSpeed();
        else                x -= getSpeed();

        boundsCheck();
        if (getCountAnimalsOnCell() > getMaxCountOnCell()) {
            move();
        }
    }

    public long getCountAnimalsOnCell() {
        return getIsland().getAnimals().stream()
                .filter(a -> a.getX() == getX() && a.getY() == getY() && !a.isSex())
                .filter(p -> p.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
                .count();
    }

    @Override
    public void reproduct() {
        if (!(isSex() && getTimeToReproduct() == 0)) return;
        if (!(getCountAnimalsOnCell() == 0)) {
            getIsland().getLocker().writeLock().lock();
            Animal child = this.clone();
            getIsland().getAnimals().add(child);
            getIsland().getExecutorService().scheduleAtFixedRate(child, 0, 2, TimeUnit.SECONDS);
            getIsland().getLocker().writeLock().unlock();
            setTimeToReproduct(6);
            logger.info("{} was born", child.getClass().getSimpleName());
        }
    }

    @Override
    public void eat() {
        if (getFullness() >= 100) return;
        List<Animal> listAnimals = animalsOnCellWhichCanEat(this);
        if (listAnimals.isEmpty()) return;

        int animalIndex = getRandom().nextInt(0, listAnimals.size());
        int chance = getRandom().nextInt(1, 101);
        Animal animal = listAnimals.get(animalIndex);
        if (chance < getAnimalsEaten().get(animal.getClass().getSimpleName())) {
            setFullness(getFullness() + (getKgToFedUp() / animal.getWeight()));
            if (getFullness() > 100) setFullness(100);
            animal.die();
        }
        logger.info("{} ate {}", this.getClass().getSimpleName(), animal.getClass().getSimpleName());
    }

    public List<Animal> animalsOnCellWhichCanEat(Animal animal) {
        getIsland().getLocker().readLock().lock();
        List<Animal> animalsOnCellWhichCanEat = getIsland().getAnimals().stream()
                .filter(a -> a.getX() == animal.getX() && a.getY() == animal.getY())
                .filter(a -> getAnimalsEaten().containsKey(a.getClass().getSimpleName()))
                .toList();
        getIsland().getLocker().readLock().unlock();
        return animalsOnCellWhichCanEat;
    }

    public void die() {
        getIsland().getLocker().writeLock().lock();
        getIsland().getAnimals().remove(this);
        getIsland().getLocker().writeLock().unlock();
        task.cancel(false);
        logger.info("{} died", this.getClass().getSimpleName());
    }

    private void reduction() {
        setFullness(getFullness() - (getSpeed() * getWeight() / 200));
        if (getFullness() <= 0) {
            die();
        }
    }

    private void boundsCheck() {
        if (getX() > getIsland().getHeight())
            setX(getX() - getIsland().getHeight());
        else if (getX() < 0)
            setX(getX() + getIsland().getHeight());
        if (getY() > getIsland().getWidth())
            setY(getY() - getIsland().getWidth());
        else if (getY() < 0)
            setY(getY() + getIsland().getWidth());
    }

    @Override
    public Animal clone() {
        try {
            return (Animal) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error("{} can't clone", this);
            throw new AssertionError();
        }
    }

    @Override
    public void run() {
        move();
        eat();
        reduction();
        reproduct();
        if (getTimeToReproduct() > 0) setTimeToReproduct(getTimeToReproduct() - 1);
    }
}
package Animals.grass;

import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;
import static java.lang.Thread.sleep;

@Getter
@Setter
public class Grass implements Runnable {
    private static Grass grass;
    private double[][] field = new double[41][101];
    private Grass() {
        init();
        new Thread(this).start();
    }
    public static synchronized Grass getGrass() {
        if (grass == null) {
            grass = new Grass();
        }
        return grass;
    }
    public void init() {
        for (double[] ints : field) {
            Arrays.fill(ints, 100);
        }
    }
    public synchronized void recovery() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = field[i][j] + 1.5;
            }
        }
    }
    public synchronized void decrement(int x, int y, double value) {
        field[x][y] = field[x][y] - value;
    }
    @Override
    public void run() {
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        recovery();
    }
}

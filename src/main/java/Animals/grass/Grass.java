package Animals.grass;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

import static java.lang.Thread.sleep;

@Getter
@Setter
public class Grass implements Runnable {
    private double[][] field;

    public Grass(int height, int width) {
        field = new double[height][width];
        init();
        new Thread(this).start();
    }

    private void init() {
        for (double[] ints : field) {
            Arrays.fill(ints, 100);
        }
    }

    private synchronized void recovery() {
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

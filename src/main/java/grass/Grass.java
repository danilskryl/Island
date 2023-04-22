package grass;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static java.lang.Thread.sleep;

@Getter
@Setter
public class Grass implements Runnable {
    private double[][] field;
    private static final Logger LOGGER = LoggerFactory.getLogger(Grass.class);

    public Grass(int height, int width) {
        field = new double[height][width];
        init();
        new Thread(this).start();
        LOGGER.debug("Grass is created");
    }

    private void init() {
        try {
            for (double[] ints : field) {
                Arrays.fill(ints, 100);
            }
            LOGGER.debug("Grass field are filled");
        } catch (Throwable e) {
            LOGGER.error(e.getMessage());
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
        LOGGER.debug("Grass field is decremented for value=[{}] on cell x=[{}] y=[{}]", value, x, y);
    }

    @Override
    public void run() {
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        recovery();
        LOGGER.debug("Grass is recovering");
    }
}

package Console.userConsole;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

@Getter
public class Console {
    private final Scanner scanner = new Scanner(System.in);
    private int predators;
    private int herbivores;
    private int omnivorous;
    private int height;
    private int width;
    private static final Logger LOGGER = LoggerFactory.getLogger(Console.class);

    public void start() {
        do {
            System.out.println("~~~~~~~SIMULATION~~~~~~~\n");
            System.out.print("Enter height of island - ");
            height = scanner.nextInt();
            System.out.print("Enter width of island - ");
            width = scanner.nextInt();
            System.out.print("Enter count of predators - ");
            predators = scanner.nextInt();
            System.out.print("Enter count of herbivores - ");
            herbivores = scanner.nextInt();
            System.out.print("Enter count of omnivorous - ");
            omnivorous = scanner.nextInt();
            if (predators >= 0 && herbivores >= 0 && omnivorous >= 0 && height > 0 && width > 0) {
                System.out.println("\n".repeat(20));
                System.out.println("Running simulation");
                LOGGER.debug("User entered valid data. Console is finished.");
                break;
            } else {
                LOGGER.error("User entered less than 0 in console. " +
                        "Entered data: height - [{}], width - [{}], predators - [{}], herbivores - [{}], omnivorous - [{}]",
                        height, width, predators, herbivores, omnivorous);
                System.out.println("\nYou entered less than 0. Try again!\n");
            }
        } while (true);
    }
}

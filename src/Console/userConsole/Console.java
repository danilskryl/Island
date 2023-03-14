package Console.userConsole;

import lombok.Getter;
import java.util.Scanner;

@Getter
public class Console {
    private final Scanner scanner = new Scanner(System.in);
    private int predators;
    private int herbivores;
    private int omnivorous;
    public void start() {
        do {
            System.out.println("~~~~~~~SIMULATION~~~~~~~\n");
            System.out.print("Enter count of predators - ");
            predators = scanner.nextInt();
            System.out.print("Enter count of herbivores - ");
            herbivores = scanner.nextInt();
            System.out.print("Enter count of omnivorous - ");
            omnivorous = scanner.nextInt();
            if (predators >= 0 && herbivores >= 0 && omnivorous >= 0) {
                System.out.println("\n".repeat(20));
                System.out.println("Running simulation");
                break;
            } else {
                System.out.println("\nYou entered less than 0. Try again!\n");
            }
        } while (true);
    }
}

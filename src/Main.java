import Island.Island;
import Console.userConsole.Console;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        console.start();
        new Island(40,100,console.getPredators(), console.getHerbivores(), console.getOmnivorous()).start();
    }
}
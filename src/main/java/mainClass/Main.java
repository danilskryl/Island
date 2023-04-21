package mainClass;

import Console.userConsole.Console;
import Island.Island;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Console console = new Console();
        console.start();
        Thread simulation = new Thread(new Island(console.getHeight(),
                console.getWidth(),
                console.getPredators(),
                console.getHerbivores(),
                console.getOmnivorous()));
        simulation.start();
        simulation.join();
        System.exit(0);
    }
}
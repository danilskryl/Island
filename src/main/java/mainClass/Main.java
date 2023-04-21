package mainClass;

import Console.userConsole.Console;
import Island.Island;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        console.start();
        new Thread(new Island(console.getHeight(),
                console.getWidth(),
                console.getPredators(),
                console.getHerbivores(),
                console.getOmnivorous())).start();
    }
}
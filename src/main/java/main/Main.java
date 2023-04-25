package main;

import console.Console;
import island.Island;

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
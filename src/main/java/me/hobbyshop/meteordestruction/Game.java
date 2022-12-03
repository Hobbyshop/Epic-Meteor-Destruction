package me.hobbyshop.meteordestruction;

import lombok.Getter;

public class Game {

    @Getter
    private static final Game instance = new Game();

    public void run() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        Game.instance.run();
    }

}

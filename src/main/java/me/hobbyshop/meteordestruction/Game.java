package me.hobbyshop.meteordestruction;

import lombok.Getter;
import me.hobbyshop.meteordestruction.utility.Window;

public class Game {

    @Getter
    private static final Game instance = new Game();

    @Getter
    private Window window;

    public void run() {
        this.init();

        while (!window.shoulcClose()) {
            this.update();
            this.render();
        }

        this.cleanup();
    }

    private void init() {
        this.window = new Window("Epic Meteor Destruction   (Brutal Game)", 420, 187);
        this.window.init();
    }

    private void update() {
        window.update();
    }

    private void render() {
        window.swapBuffers();
    }

    private void cleanup() {
        this.window.destroy();
    }

    public static void main(String[] args) {
        Game.instance.run();
    }

}

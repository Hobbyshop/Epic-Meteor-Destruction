package me.hobbyshop.meteordestruction;

import lombok.Getter;
import me.hobbyshop.meteordestruction.utility.Logger;
import me.hobbyshop.meteordestruction.utility.Window;
import org.lwjgl.Version;

public class Game {

    @Getter
    private static final Game instance = new Game();

    @Getter
    private Window window;

    public void run() {
        new Thread(() -> {
            this.init();

            while (!window.shoulcClose()) {
                this.update();
                this.render();
            }

            this.cleanup();
        }, "game").start();
    }

    private void init() {
        Logger.info("LWJGL Version: \"" + Version.getVersion() + "\"");

        this.window = new Window("Epic Meteor Destruction   (Brutal Game)", 1280, 760);
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
        Game.getInstance().run();
    }

}

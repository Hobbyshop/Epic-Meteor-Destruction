package me.hobbyshop.meteordestruction;

import lombok.Getter;
import me.hobbyshop.meteordestruction.graphics.Shader;
import me.hobbyshop.meteordestruction.math.Mesh;
import me.hobbyshop.meteordestruction.math.Vec3;
import me.hobbyshop.meteordestruction.math.Vertex;
import me.hobbyshop.meteordestruction.renderers.GameRenderer;
import me.hobbyshop.meteordestruction.utility.Logger;
import me.hobbyshop.meteordestruction.utility.Window;
import org.lwjgl.Version;

public class Game {

    @Getter
    private static final Game instance = new Game();

    @Getter
    private Window window;

    @Getter
    private GameRenderer renderer;

    private Shader shader;
    private Mesh mesh = new Mesh(new Vertex[] {
            new Vertex(new Vec3(-0.5f,  0.5f, 0.0f), new Vec3(1.0F, 1.0F, 1.0F)),
            new Vertex(new Vec3(-0.5f, -0.5f, 0.0f), new Vec3(1.0F, 1.0F, 1.0F)),
            new Vertex(new Vec3( 0.5f, -0.5f, 0.0f), new Vec3(1.0F, 1.0F, 1.0F)),
            new Vertex(new Vec3( 0.5f,  0.5f, 0.0f), new Vec3(1.0F, 1.0F, 1.0F))
    }, new int[] {
            0, 1, 2,
            0, 3, 2
    });

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
        this.shader = new Shader("/shaders/main.vert", "/shaders/main.frag");
        this.renderer = new GameRenderer();

        this.window.init();

        this.mesh.build();
        this.shader.create();
    }

    private void update() {
        window.update();
    }

    private void render() {
        renderer.renderMesh(mesh, shader);
        window.swapBuffers();
    }

    private void cleanup() {
        this.shader.destroy();

        this.window.destroy();
    }

    public static void main(String[] args) {
        Game.getInstance().run();
    }

}

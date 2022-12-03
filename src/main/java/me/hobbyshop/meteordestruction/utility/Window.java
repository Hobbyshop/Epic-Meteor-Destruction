package me.hobbyshop.meteordestruction.utility;

import lombok.Getter;
import lombok.Setter;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    @Getter @Setter
    private String title;
    @Getter @Setter
    private int width,
                height;

    private long id;

    private boolean resized = false;

    public Window(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public void init() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        id = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (id == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(id, (vidMode.width() - this.width) / 2, (vidMode.height() - this.height) / 2);

        glfwSetWindowSizeCallback(id, (id, w, h) -> {
            this.width = w;
            this.height = h;

            this.resized = true;
        });

        glfwMakeContextCurrent(id);
        GL.createCapabilities();

        glfwSwapInterval(1);
        glfwShowWindow(id);
    }

    public void destroy() {
        glfwDestroyWindow(id);

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void update() {
        if (resized) {
            GL11.glViewport(0, 0, this.width, this.height);
            resized = false;
        }

        GL11.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        glfwPollEvents();
    }

    public void swapBuffers() {
        glfwSwapBuffers(id);
    }

    public boolean shoulcClose() {
        return glfwWindowShouldClose(id);
    }

}

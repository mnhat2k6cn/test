import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class ArkanoidGame {
    private long window;
    private GameManager gameManager;

    public void run() {
        init();
        loop();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);


        window = glfwCreateWindow(1200, 800, "Arkanoid Game", NULL, NULL);
        if (window == NULL) throw new RuntimeException("Failed to create GLFW window");

//        try (MemoryStack stack = stackPush()) {
//            IntBuffer pWidth = stack.mallocInt(1);
//            IntBuffer pHeight = stack.mallocInt(1);
//            glfwGetWindowSize(window, pWidth, pHeight);
//            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
//            glfwSetWindowPos(
//                    window,
//                    (vidmode.width() - pWidth.get(0)) / 2,
//                    (vidmode.height() - pHeight.get(0)) / 2
//            );
//        }

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);

        GL.createCapabilities();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0.0, 800.0, 600.0, 0.0, -1.0, 1.0);
        glMatrixMode(GL_MODELVIEW);

        gameManager = new GameManager(window);
    }

    private void loop() {
        float lastTime = (float) glfwGetTime();
        while (!gameManager.shouldClose()) {
            float currentTime = (float) glfwGetTime();
            float deltaTime = currentTime - lastTime;
            lastTime = currentTime;

            glfwPollEvents();
            gameManager.updateGame(deltaTime);
            gameManager.render();
            glfwSwapBuffers(window);
        }
    }

    public static void main(String[] args) {
        new ArkanoidGame().run();
    }
}

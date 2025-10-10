import static org.lwjgl.opengl.GL11.*;
import java.util.List;

public class Renderer {
    public void renderGame(Paddle paddle, Ball ball, List<Brick> bricks, List<PowerUp> powerUps, int lives, GameState gameState) {
        glClear(GL_COLOR_BUFFER_BIT);
        if (gameState == GameState.PLAYING) {
            paddle.render();
            ball.render();
            for (Brick brick : bricks) brick.render();
            for (PowerUp powerUp : powerUps) powerUp.render();
            renderUI(lives);
        } else if (gameState == GameState.GAME_OVER) {
            renderGameOver();
        } else if (gameState == GameState.VICTORY) {
            renderVictory();
        }
    }

    private void renderUI(int lives) {
        for (int i = 0; i < lives; i++) {
            glBegin(GL_QUADS);
            glVertex2f(10 + i * 20, 10);
            glVertex2f(25 + i * 20, 10);
            glVertex2f(25 + i * 20, 20);
            glVertex2f(10 + i * 20, 20);
            glEnd();
        }
    }

    private void renderGameOver() {
        glBegin(GL_QUADS);
        glVertex2f(350, 280);
        glVertex2f(450, 280);
        glVertex2f(450, 320);
        glVertex2f(350, 320);
        glEnd();
    }

    private void renderVictory() {
        glBegin(GL_QUADS);
        glVertex2f(350, 280);
        glVertex2f(450, 280);
        glVertex2f(450, 320);
        glVertex2f(350, 320);
        glEnd();
    }
}

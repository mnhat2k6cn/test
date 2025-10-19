import static org.lwjgl.opengl.GL11.*;
import java.util.List;

public class Renderer {

    private int gameOverTexture = TextureLoader.loadTexture("GameOver.png");

    public void renderGame(Paddle paddle, Ball ball, List<Brick> bricks, List<PowerUp> powerUps, int lives, GameState gameState) {
        glClear(GL_COLOR_BUFFER_BIT);
        if (gameState == GameState.PLAYING) {
            paddle.render();
            ball.render();
            for (int i = 0; i < bricks.size(); i++) {
                Brick brick = bricks.get(i);
                brick.render();
            }

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
            glColor3f(1.0f, 1.0f, 1.0f);
            glBegin(GL_QUADS);
            glVertex2f(10 + i * 20, 10);
            glVertex2f(25 + i * 20, 10);
            glVertex2f(25 + i * 20, 20);
            glVertex2f(10 + i * 20, 20);
            glEnd();
        }
    }

    private void renderGameOver() {
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, gameOverTexture);//khai báo opengl rằng các lệnh tiếp theo dùng ảnh này


//        // Lấy kích thước ảnh hiển thị
//        float width = 300f;
//        float height = 150f;
//        float x = (800 - width) / 2;
//        float y = (600 - height) / 2;

        glColor3f(1f, 1f, 1f);

        glBegin(GL_QUADS);
    //     (0,1)        (1,1)
        //  +------------+
        //  |            |
        //  |   Texture  |
        //  |   (ảnh)    |
        //  +------------+
        //(0,0)        (1,0)
        glTexCoord2f(0, 1); glVertex2f(266, 200);                   // góc trên-trái của ảnh x ,y
        glTexCoord2f(1, 1); glVertex2f(266*2, 200);           // góc trên-phải của ảnh x + width , y
        glTexCoord2f(1, 0); glVertex2f(266*2, 400);  // góc dưới-phải của ảnh       x + width,  y + height
        glTexCoord2f(0, 0); glVertex2f(266, 400);          // góc dưới-trái của ảnh x,  y + height
        glEnd();

        glDisable(GL_TEXTURE_2D);
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

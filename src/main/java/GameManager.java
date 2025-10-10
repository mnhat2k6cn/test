import java.util.*;
import static org.lwjgl.glfw.GLFW.*;

public class GameManager {
    private Paddle paddle;
    private Ball ball;
    private List<Brick> bricks;
    private List<PowerUp> powerUps;
    private int score;
    private int lives;
    private GameState gameState;
    private long window;

    private Renderer renderer;
    private boolean leftPressed, rightPressed;

    public GameManager(long window) {
        this.window = window;
        this.lives = 3;
        this.score = 0;
        this.gameState = GameState.PLAYING;
        this.bricks = new ArrayList<>();
        this.powerUps = new ArrayList<>();
        this.renderer = new Renderer();

        startGame();
        setupInput();
    }

    private void setupInput() {
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_A || key == GLFW_KEY_LEFT) {
                leftPressed = (action == GLFW_PRESS || action == GLFW_REPEAT);
            }
            if (key == GLFW_KEY_D || key == GLFW_KEY_RIGHT) {
                rightPressed = (action == GLFW_PRESS || action == GLFW_REPEAT);
            }
            if (key == GLFW_KEY_SPACE && action == GLFW_PRESS) {
                if (gameState == GameState.GAME_OVER || gameState == GameState.VICTORY) {
                    restartGame();
                }
            }
        });
    }

    public void startGame() {
        paddle = new Paddle(350, 550, 100, 20);
        ball = new Ball(400, 300, 15);
        createBricks();
    }

    private void createBricks() {
        bricks.clear();
        float brickWidth = 75;
        float brickHeight = 25;
        float startX = 50;
        float startY = 50;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 10; col++) {
                float x = startX + col * (brickWidth + 5);
                float y = startY + row * (brickHeight + 5);
                if (row < 2) bricks.add(new StrongBrick(x, y, brickWidth, brickHeight));
                else bricks.add(new NormalBrick(x, y, brickWidth, brickHeight));
            }
        }
    }

    public void updateGame(float deltaTime) {
        if (gameState != GameState.PLAYING) return;
        handleInput(deltaTime);
        paddle.update(deltaTime);
        ball.update(deltaTime);
        powerUps.removeIf(powerUp -> {
            powerUp.update(deltaTime);
            return powerUp.isOutOfBounds();
        });
        checkCollisions();

        if (ball.isOutOfBounds()) {
            lives--;
            if (lives <= 0) gameState = GameState.GAME_OVER;
            else ball = new Ball(400, 300, 15);
        }
        if (bricks.isEmpty()) gameState = GameState.VICTORY;
    }

    private void handleInput(float deltaTime) {
        if (leftPressed) paddle.moveLeft(deltaTime);
        else if (rightPressed) paddle.moveRight(deltaTime);
        else paddle.stop();
    }

    private void checkCollisions() {
        if (ball.checkCollision(paddle)) ball.bounceOffPaddle(paddle);

        Iterator<Brick> brickIter = bricks.iterator();
        while (brickIter.hasNext()) {
            Brick brick = brickIter.next();
            if (ball.checkCollision(brick)) {
                ball.bounceOff(brick);
                brick.takeHit();
                if (brick.isDestroyed()) {
                    score += 10;
                    if (Math.random() < 0.3) {
                        if (Math.random() < 0.5)
                            powerUps.add(new ExpandPaddlePowerUp(brick.getCenterX(), brick.getCenterY()));
                        else
                            powerUps.add(new FastBallPowerUp(brick.getCenterX(), brick.getCenterY()));
                    }
                    brickIter.remove();
                }
                break;
            }
        }

        Iterator<PowerUp> powerUpIter = powerUps.iterator();
        while (powerUpIter.hasNext()) {
            PowerUp powerUp = powerUpIter.next();
            if (paddle.checkCollision(powerUp)) {
                powerUp.applyEffect(paddle);
                if (powerUp instanceof FastBallPowerUp) ball.setSpeed(450.0f);
                powerUpIter.remove();
            }
        }
    }

    public void render() {
        renderer.renderGame(paddle, ball, bricks, powerUps, lives, gameState);
    }

    private void restartGame() {
        lives = 3;
        score = 0;
        gameState = GameState.PLAYING;
        powerUps.clear();
        startGame();
    }

    public boolean shouldClose() {
        return org.lwjgl.glfw.GLFW.glfwWindowShouldClose(window);
    }

    public GameState getGameState() { return gameState; }
}

public class Paddle extends MovableObject {
    private float speed;
    private PowerUp currentPowerUp;
    private float originalWidth;

    public Paddle(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.speed = 400.0f;
        this.originalWidth = width;
    }

    @Override
    public void update(float deltaTime) {
        move(deltaTime);
        if (x < 0) x = 0;
        if (x + width > 800) x = 800 - width;
    }

    public void moveLeft(float deltaTime) { dx = -speed; }
    public void moveRight(float deltaTime) { dx = speed; }
    public void stop() { dx = 0; }

    public void applyPowerUp(PowerUp powerUp) {
        this.currentPowerUp = powerUp;
        powerUp.applyEffect(this);
    }

    public void expandPaddle() { width = originalWidth * 1.5f; }
    public void resetSize() { width = originalWidth; }
}

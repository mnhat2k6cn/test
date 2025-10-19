public class Ball extends MovableObject {
    private float speed;
    private float directionX, directionY;

    public Ball(float x, float y, float size) {
        super(x, y, size, size);
        this.speed = 250.0f;
        this.directionX = 1.0f;
        this.directionY = -1.0f;
        this.dx = directionX * speed;
        this.dy = directionY * speed;
    }

    @Override
    public void update(float deltaTime) {
        move(deltaTime);
        if (x <= 0 || x + width >= 800) {
            dx = -dx;
            directionX = -directionX;
        }
        if (y <= 0) {
            dy = -dy;
            directionY = -directionY;
        }
        if (x < 0) x = 0;
        if (x + width > 800) x = 800 - width;
        if (y < 0) y = 0;
    }

    public void bounceOff(GameObject other) {
        dy = -dy;
        directionY = -directionY;
    }

    public void bounceOffPaddle(Paddle paddle) {
        float ballCenter = getCenterX();
        float paddleCenter = paddle.getCenterX();
        float hitPos = (ballCenter - paddleCenter) / (paddle.getWidth() / 2);
        hitPos = Math.max(-1.0f, Math.min(1.0f, hitPos));
        directionX = hitPos;
        directionY = -Math.abs(directionY);
        float length = (float) Math.sqrt(directionX * directionX + directionY * directionY);
        directionX /= length;
        directionY /= length;
        dx = directionX * speed;
        dy = directionY * speed;
    }

    public boolean isOutOfBounds() { return y > 600; }

    public void setSpeed(float speed) {
        this.speed = speed;
        float length = (float) Math.sqrt(directionX * directionX + directionY * directionY);
        dx = (directionX / length) * speed;
        dy = (directionY / length) * speed;
    }
}

public abstract class MovableObject extends GameObject {
    protected float dx, dy;

    public MovableObject(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.dx = 0;
        this.dy = 0;
    }

    public void move(float deltaTime) {
        x += dx * deltaTime;
        y += dy * deltaTime;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }
}

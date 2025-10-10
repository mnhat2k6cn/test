public abstract class PowerUp extends GameObject {
    protected String type;
    protected float duration;
    protected float dy;

    public PowerUp(float x, float y, String type, float duration) {
        super(x, y, 20, 20);
        this.type = type;
        this.duration = duration;
        this.dy = 100.0f;
    }

    @Override
    public void update(float deltaTime) {
        y += dy * deltaTime;
    }

    public abstract void applyEffect(Paddle paddle);

    public boolean isOutOfBounds() { return y > 600; }
    public String getType() { return type; }
}

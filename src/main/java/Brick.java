public abstract class Brick extends GameObject {
    protected int hitPoints;
    protected String type;
    protected boolean destroyed;

    public Brick(float x, float y, float width, float height, int hitPoints, String type) {
        super(x, y, width, height);
        this.hitPoints = hitPoints;
        this.type = type;
        this.destroyed = false;
    }

    @Override
    public void update(float deltaTime) {}

    public void takeHit() {
        hitPoints--;
        if (hitPoints <= 0) destroyed = true;
    }

    public boolean isDestroyed() { return destroyed; }
    public int getHitPoints() { return hitPoints; }
    public String getType() { return type; }
}

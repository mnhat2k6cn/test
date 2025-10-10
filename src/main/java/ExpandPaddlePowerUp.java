public class ExpandPaddlePowerUp extends PowerUp {
    public ExpandPaddlePowerUp(float x, float y) {
        super(x, y, "expand", 10.0f);
    }

    @Override
    public void applyEffect(Paddle paddle) {
        paddle.expandPaddle();
    }
}

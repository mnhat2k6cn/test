public class FastBallPowerUp extends PowerUp {
    public FastBallPowerUp(float x, float y) {
        super(x, y, "fastball", 5.0f);
    }

    @Override
    public void applyEffect(Paddle paddle) {
        // Tăng tốc bóng được xử lý trong GameManager khi nhặt power-up
    }
}
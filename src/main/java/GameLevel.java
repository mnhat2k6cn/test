import java.util.ArrayList;
import java.util.List;

public class GameLevel {
    private int[][] levelLayout;
    private List<Brick> bricks = new ArrayList<>();

    public GameLevel(int levelNumber) {
        if (levelNumber == 1) {
            levelLayout = new int[][]{
                    {0, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                    {0, 0, 1, 0, 1, 1, 0, 1, 0, 0},
                    {0, 1, 1, 0, 0, 0, 0, 1, 1, 0},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
                    {0, 0, 1, 0, 0, 0, 0, 1, 0, 0},
                    {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                    {0, 3, 3, 3, 3, 3, 3, 3, 3, 0}
            };
        } else if (levelNumber == 2) {
            levelLayout = new int[][]{
                    {0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 0, 1, 0, 1, 0, 1, 0, 1, 0}
            };
        }
        createBricksFromLayout();
    }

    private void createBricksFromLayout() {
        float brickWidth = 75;
        float brickHeight = 30;
        float startX = 0;
        float startY = 50;

        for (int row = 0; row < levelLayout.length; row++) {
            for (int col = 0; col < levelLayout[row].length; col++) {
                if (levelLayout[row][col] == 1) {
                    float x = startX + col * (brickWidth + 5);
                    float y = startY + row * (brickHeight + 5);
                    bricks.add(new NormalBrick(x, y, brickWidth, brickHeight));
                }
                if(levelLayout[row][col] == 2) {
                    float x = startX + col * (brickWidth + 5);
                    float y = startY + row * (brickHeight + 5);
                    bricks.add(new StrongBrick(x, y, brickWidth, brickHeight));
                }
                if(levelLayout[row][col] == 3) {
                    float x = startX + col * (brickWidth + 5);
                    float y = startY + row * (brickHeight + 5);
                    bricks.add(new UnbreakBrick(x, y, brickWidth, brickHeight));
                }
            }
        }
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void update() {

    }
}
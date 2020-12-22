package ArkanoidGame;

import java.util.Random;

public class Constants {
    //Screen size
    public static final int screenWidth = 1000;
    public static final int screenHeight = 800;
    public static final int screenBorderWidth = screenWidth - 8;
    public static final int screenBorderHeight = screenHeight - 8;

    //Ball start pos
    public static final int ballposX = 500;
    public static final int ballposY = 500;

    public static int[] randomX = {-5 ,-4 , 4, 5};
    public static int rndX = new Random().nextInt(4);
    public static final int ballxDir = randomX[rndX];
    public static final int ballyDir = -5;

    //Player paddle start pos
    public static final int playerX = 460;
    public static final int playerY = 730;

    //Block size

    public static final int blockRows = 3;
    public static final int blockColumns = 8;
    public static final int blockWidth = (screenWidth-170)/blockColumns;
    public static final int blockHeight = (screenHeight-600)/blockRows;

    //Points and block total
    public static final int pointsForHit = 1;
    public static final int totalBlocks = blockRows * blockColumns;

    public static int extraBallSpawn = totalBlocks-5;
}

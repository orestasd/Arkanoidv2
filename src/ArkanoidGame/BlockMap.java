package ArkanoidGame;

import java.awt.*;

public class BlockMap {
    public int[][] map;


    public BlockMap(int row, int col){
        map = new int[row][col];
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j< map[0].length; j++){
                map[i][j] = 1;
            }
        }
    }

    public void draw(Graphics2D graph){
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j< map[0].length; j++){
                if(map[i][j] > 0){
                    Color randomColor = new Color((int)(Math.random() * 0x1000000));
                    graph.setColor(randomColor);
                    graph.fillRect(j * Constants.blockWidth + 80, i * Constants.blockHeight + 50,
                                        Constants.blockWidth, Constants.blockHeight);

                    graph.setStroke(new BasicStroke(3));
                    graph.setColor(Color.black);
                    graph.drawRect(j * Constants.blockWidth + 80, i * Constants.blockHeight + 50,
                                        Constants.blockWidth, Constants.blockHeight);
                }
            }
        }
    }

    public void setBlockValue(int value, int row, int col){
        map[row][col] = value;
    }
}

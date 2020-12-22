package ArkanoidGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class Game extends JPanel implements KeyListener, ActionListener {

    private boolean gamePlay = false;
    private int gameScore = 0;
    private int totalBlocks = Constants.totalBlocks;

    private final Timer timer;
    private final int delay = 1;

    private int healthBar = 3;

    Player player = new Player();
    Ball ball = new Ball();
    Ball newBall = new Ball();

    private BlockMap map;

    public Game(){
        map = new BlockMap(Constants.blockRows, Constants.blockColumns);
        int delay = this.delay;
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics graph){
        //background
        graph.setColor(Color.black);
        graph.fillRect(1, 1, Constants.screenBorderWidth, Constants.screenBorderHeight);

        //drawing map
        map.draw((Graphics2D) graph);

        //borders
        graph.setColor(Color.GRAY);
        graph.fillRect(0,0,3, Constants.screenBorderHeight);
        graph.fillRect(0,0,Constants.screenBorderWidth, 3);
        graph.fillRect(Constants.screenBorderWidth,0,3, Constants.screenBorderHeight);

        //scores
        graph.setColor(Color.white);
        graph.setFont(new Font("serif",Font.BOLD, 25));
        graph.drawString("" + gameScore, Constants.screenWidth-45, 30);

        //health bar
        graph.setColor(Color.red);
        graph.setFont(new Font("serif",Font.BOLD, 15));
        graph.drawString(healthBar + " ❤", Constants.screenWidth-990, 20);

        //the paddle
        graph.setColor(Color.green);
        graph.fillRect(player.getPlayerX(), player.getPlayerY(), 100, 8);

        //the ball
        graph.setColor(Color.yellow);
        graph.fillOval(ball.getBallposX(), ball.getBallposY(), 20, 20);

        if(ball.getBallposY() > 770){
            if(healthBar < 2){
                gamePlay = false;
                healthBar = 0;
                graph.setColor(Color.red);
                graph.setFont(new Font("serif",Font.BOLD, 40));
                graph.drawString("Game Over, Score: " + gameScore, 320, 400 );

                graph.setFont(new Font("serif",Font.BOLD, 40));
                graph.drawString("Press Enter to restart", 320, 450 );
            }else {
                healthBar--;
                respawnBall();
            }
        }

        if(totalBlocks == 0){
            gamePlay = false;
            newBall = null;
            ball.setBallxDir(0);
            ball.setBallyDir(0);
            graph.setColor(Color.red);
            graph.setFont(new Font("serif",Font.BOLD, 40));
            graph.drawString("You won. Score: " + gameScore, 320, 400 );
            graph.setFont(new Font("serif",Font.BOLD, 40));
            graph.drawString("Press Enter to restart", 320, 450 );
        }

        if(totalBlocks <= Constants.extraBallSpawn && newBall != null){
            graph.setColor(Color.white);
            graph.fillOval(newBall.getBallposX(), newBall.getBallposY(), 20, 20);
        }

        graph.dispose();
    }

    public void respawnBall(){
        ball.setBallxDir(Constants.ballxDir);
        ball.setBallyDir(Constants.ballyDir);
        ball.setBallposX(Constants.ballposX);
        ball.setBallposY(Constants.ballposY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gamePlay){
            ballMovement(ball);
            if(totalBlocks <= Constants.extraBallSpawn && newBall != null) {
                ballMovement(newBall);
            }
        }
        repaint();
    }

    private void ballMovement(Ball ball){
        if(new Rectangle(ball.getBallposX(), ball.getBallposY(), 20, 20)
                    .intersects(new Rectangle(player.getPlayerX(),player.getPlayerY(), 100,8))){
            ball.setBallyDir(- ball.getBallyDir());
        }

        ballInteractions(ball);

        ball.setBallposX(ball.getBallposX() + ball.getBallxDir());
        ball.setBallposY(ball.getBallposY() + ball.getBallyDir());
        if(ball.getBallposX() < 0){
            ball.setBallxDir(-ball.getBallxDir());
        }
        if(ball.getBallposY() < 0){
            ball.setBallyDir(-ball.getBallyDir());
        }
        if(ball.getBallposX() > 970){
            ball.setBallxDir(-ball.getBallxDir());
        }
    }

    private void ballInteractions(Ball ball) {
        LoopBreaker : for(int i = 0; i < map.map.length; i++){
            for(int j = 0; j < map.map[0].length; j++){
                if(map.map[i][j] > 0){
                    int blockX = j * Constants.blockWidth + 80;
                    int blockY = i * Constants.blockHeight + 50;
                    int blockWidth = Constants.blockWidth;
                    int blockHeight = Constants.blockHeight;

                    Rectangle rect = new Rectangle(blockX, blockY, blockWidth, blockHeight);
                    Rectangle ballRect = new Rectangle(ball.getBallposX(), ball.getBallposY(), 20, 20);

                    if(ballRect.intersects(rect)){
                        map.setBlockValue(0, i, j);
                        totalBlocks--;
                        gameScore += Constants.pointsForHit;

                        if(ball.getBallposX() + 19 <= rect.x ||
                                                    ball.getBallposX() + 1 >= rect.x + rect.width){
                            ball.setBallxDir(-ball.getBallxDir());
                        } else{
                            ball.setBallyDir(-ball.getBallyDir());
                        }
                        break LoopBreaker;
                    }
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(player.getPlayerX() >= Constants.screenBorderWidth - 110){
                player.setPlayerX(Constants.screenBorderWidth - 110);
            }
            else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(player.getPlayerX() <= 10){
                player.setPlayerX(10);
            }
            else{
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            Reset();
        }
    }

    private void Reset() {
        if(!gamePlay){
            gamePlay = true;
            Ball newBall = new Ball();
            ball.setBallposX(Constants.ballposX);
            ball.setBallposY(Constants.ballposY);
            ball.setBallxDir(Constants.ballxDir);
            ball.setBallyDir(Constants.ballyDir);
            player.setPlayerX(Constants.playerX);
            gameScore = 0;
            healthBar = 3;
            totalBlocks = Constants.totalBlocks;
            map = new BlockMap(Constants.blockRows, Constants.blockColumns);
            repaint();
        }
    }

    public void moveRight(){
        gamePlay = true;
        player.setPlayerX(player.getPlayerX() + 20);
    }

    public void moveLeft(){
        gamePlay = true;
        player.setPlayerX(player.getPlayerX() - 20);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}

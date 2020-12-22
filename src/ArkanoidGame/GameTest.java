package ArkanoidGame;

import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class GameTest {

    @Test
    void respawnBall() {
        Game testgame = new Game();
        testgame.ball.setBallposX(200);
        testgame.respawnBall();

        assertEquals(Constants.ballposX, testgame.ball.getBallposX());
    }

    @Test
    void ifKeyPressedLeft() {
        Game game = new Game();
        Player player = game.player;
        KeyEvent key = new KeyEvent(game, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT,'Z');
        game.getKeyListeners()[0].keyPressed(key);

       assertEquals(440, player.getPlayerX());
    }

    @Test
    public void testIfPaddleStillCanMoveToRight(){
        Game game = new Game();
        Player player = game.player;
        int setX = player.getPlayerX();
        if(player.getPlayerX() <= Constants.screenBorderWidth - 110){
            game.moveRight();
        }

        assertTrue("Player paddle needs to move to right, if paddle did not touch wall", player.getPlayerX()>setX);
    }
}
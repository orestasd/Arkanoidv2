import ArkanoidGame.Constants;
import ArkanoidGame.Game;

import javax.swing.*;

public class Start {

    public static void main(String[] args) {
        JFrame screen = new JFrame();
        Game gamePlay = new Game();
        screen.setBounds(10,10, Constants.screenWidth, Constants.screenHeight);
        screen.setTitle("Arkanoid");
        screen.setResizable(false);
        screen.setVisible(true);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.add(gamePlay);
    }
}

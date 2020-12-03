package HitBallGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JFrame;

//Abstract Windows Toolkit
import java.awt.*;

/**
 * Created by John Lee, Bugil Academy GLP class of 2017
 * AP Computer Science 2016
 *
 * The HitBallGame class is the main menu of this game. It provides instructions on how to play the game, and
 * contains the methods for starting both the PointGame and the SurvivalGame.
 * Both games are played by pressing predesignated keyboard keys to hit balls as they come down from a ballCreator
 * to the ballDetector where the ball can be detected and hit.
 * The general objective of both games is to hit as many balls as possible without making mistakes
 */
public class HitBallGame extends JPanel implements KeyListener
{
    /**
     * A serial ID for this class
     */
    private static final long serialVersionUID = 1L;
    /**
     * boolean list for pressing keys
     */
    public boolean [] keyDown;
    public boolean[] help;
    public boolean[] play;
    public boolean close;
    public boolean gameOver;

    public HitBallGame()                       // set up graphics window
    {
        this.addKeyListener(this);
        setBackground(Color.WHITE);
        this.help = new boolean[3];
        this.play = new boolean[2];
        play[0] = false;
        play[1] = false;
        for (int i = 0; i < help.length; i++) {
            help[i] = false;
        }
        this.keyDown = new boolean [256];
        for (int i = 0; i < keyDown.length; i++) {
            keyDown[i] = false;
        }
        close = false;
        gameOver = false;
    }

    public void paint(Graphics g)  // draw graphics in the panel
    {
        if (help[0] == false && help[1] == false) {
            Font font = new Font("Verdana", Font.PLAIN, 20);
            g.setFont(font);
            g.drawString("This game is made by John Lee, Bugil Academy GLP Class of 2017", 20, 20);
            g.drawString("Press 'E' to start the PointGame mode", 20, 60);
            g.drawString("Press 'R' to start the SurvivalGame mode", 20, 90);
            g.drawString("Press 'Q' to see instructions and help for PointsGame", 20, 120);
            g.drawString("Press 'W' to see instructions and help for SurvivalGame", 20, 150);
            g.drawString("Good Luck!", 20, 220);
        }
        if (help[0] == true) {
            Font font = new Font("Verdana", Font.PLAIN, 20);
            g.setFont(font);
            g.drawString("This game is made by John Lee, Bugil Academy GLP Class of 2017", 20, 20);
            g.drawString("Press 'E' to start the PointGame mode", 20, 60);
            g.drawString("Press 'R' to start the SurvivalGame mode        Press 'G' to go back", 20, 90);
            g.drawString("In the PointGame, there are four levels.", 20, 150);
            g.drawString("The objective is to hit as many balls as possible.", 20, 180);
            g.drawString("The number of lanes of balls that you have to hit", 20, 210);
            g.drawString("increases as the level increases.", 20, 240);
            g.drawString("Hit balls without making mistakes to gain combos.", 20, 270);
            g.drawString("Combos are reset when you miss or a ball reaches the ground.", 20, 300);
            g.drawString("The amount of points you earn with each hit is multiplied", 20, 330);
            g.drawString("by (1 + yourCombo*0.1).", 20, 360);
            g.drawString("Hit the balls as they pass through the lazer of", 20, 390);
            g.drawString("the ballDetector.", 20, 420);
            g.drawString("The leftmost ballDetector is triggered by the", 20, 450);
            g.drawString("key 'Z', the next ballDetector is triggered by", 20, 480);
            g.drawString("the key 'X,' and so on.", 20, 510);
        }
        if (help[1] == true) {
            Font font = new Font("Verdana", Font.PLAIN, 20);
            g.setFont(font);
            g.drawString("This game is made by John Lee, Bugil Academy GLP Class of 2017", 20, 20);
            g.drawString("Press 'E' to start the PointGame mode", 20, 60);
            g.drawString("Press 'R' to start the SurvivalGame mode         Press 'G' to go back", 20, 90);
            g.drawString("In the SurvivalGame, the objective is to stay alive", 20, 150);
            g.drawString("as long as possible.", 20, 180);
            g.drawString("After you hit a certain number of balls, the level increases.", 20, 210);
            g.drawString("In order to survive, you must hit the falling balls", 20, 240);
            g.drawString("before they reach the ground.", 20, 270);
            g.drawString("You start with 100 lives. If a ball reaches the", 20, 300);
            g.drawString("Ground, you lose 5 lives. If you try to hit a ball", 20, 330);
            g.drawString("but miss, you lose 1 life.", 20, 360);
            g.drawString("Hit balls without making mistakes to gain combos.", 20, 390);
            g.drawString("If you reach 10 combo, you regain 5 lives.", 20, 420);
            g.drawString("If you reach 20 combo, you regain 10 lives.", 20, 450);
            g.drawString("If you reach 30 combo, you regain 15 lives, and so on", 20, 480);
            g.drawString("Combos are reset when you miss or a ball reaches the ground.", 20, 510);
            g.drawString("Hit the balls as they pass through the lazer of", 20, 540);
            g.drawString("the ballDetector.", 20, 570);
            g.drawString("The leftmost ballDetector is triggered by the", 20, 600);
            g.drawString("key 'Z', the next ballDetector is triggered by", 20, 630);
            g.drawString("the key 'X,' and so on.", 20, 660);
        }
    }

    public static void main(String[] args)
    {
        HitBallGame game = new HitBallGame();
        JFrame application = new JFrame();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.add(game);

        application.setSize(800, 800);
        application.setVisible(true);
        game.repaint();
        int timeIncrement = 25;

        long currentTime = System.currentTimeMillis();  //these two time values are used below for modulating FPS
        long currentTime2 = System.currentTimeMillis();
        while (game.gameOver == false){
            game.requestFocus(); //request focus
            game.update(); //calls the big update method of the stage
            if (game.play[0]) {
                application.dispose();
                playPointGame.play();
                game.gameOver = true;

            }
            if (game.play[1]) {
                application.dispose();
                playSurvivalGame.play();
                game.gameOver = true;
            }
            application.repaint(); //paints the stage after update
            while (currentTime < currentTime2+timeIncrement) { //forces the while loop to wait until 25 milliseconds passes
                currentTime = System.currentTimeMillis();
            }
            currentTime2 = System.currentTimeMillis(); //resets the time values so that the time loop can begin again
        }
    }
    public void update () {
        Qupdate();
        Wupdate();
        Eupdate();
        Rupdate();
        Gupdate();
    }

    public void Qupdate () {
        if (keyDown[KeyEvent.VK_Q] == true && help[0] == false && help [1] == false) {
            help[0] = true;
            repaint();
        }
    }
    public void Wupdate () {
        if (keyDown[KeyEvent.VK_W] == true && help[0] == false && help [1] == false) {
            help[1] = true;
            repaint();
        }
    }
    public void Gupdate () {
        if (keyDown[KeyEvent.VK_G] == true && (help[0] == true || help [1] == true)) {
            help[0] = false;
            help[1] = false;
            repaint();
        }
    }
    public void Eupdate () {
        if (keyDown[KeyEvent.VK_E] == true) {
            play[0] = true;
        }
    }
    public void Rupdate() {
        if (keyDown[KeyEvent.VK_R] == true) {
            play[1] = true;
        }
    }
    /**
     * Method that must be overridden because of the implementation of
     * the Keylistener interface
     * @param e KeyEvent that is triggered by the pressing of a key on the keyboard
     */
    public void keyPressed(KeyEvent e){ //to enable pressing of keys
        keyDown[e.getKeyCode()] = true;
    }

    /**
     * Method that must be overridden in the Keylistener interface
     * @param e KeyEvent that is triggered by the release of a key on the Keyboard
     */
    public void keyReleased(KeyEvent e){ //to enable release of keys
        keyDown[e.getKeyCode()] = false;
    }

    /**
     * Method that must be overridden in the Keylistener interface
     * @param e KeyEvent that is triggered by the typing of a key on the Keyboard
     */
    public void keyTyped (KeyEvent e) {

    }

}

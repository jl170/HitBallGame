package HitBallGame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * abstract class of a stage
 * Different implementations can give different types of modes that the game can be played in
 */
public abstract class stage extends JPanel implements KeyListener {
    /**
     * width of the stage
     */
    public int width;
    /**
     * height of the stage
     */
    public int height;
    /**
     * boolean list for pressing keys
     */
    public boolean [] keyDown;
    /**
     * Arraylist of detectors
     */
    public ArrayList<ballDetector> detectorList;
    /**
     * Arraylist of creators
     */
    public ArrayList<ballCreator> creatorList;
    /**
     * Arraylist of arraylist of balls
     */
    public ArrayList<ArrayList<ball>> allBall;
    /**
     * keep track of points earned
     */
    public int points;
    /**
     * turns false when stage is over
     */
    public boolean inPlay;
    /**
     * how many balls that are left in the game
     */
    public int ballCount;
    /**
     * represents current level
     */
    public int level;
    /**
     * value to determine balls' speed
     */
    public int speed;
    /**
     * value to determine balls' radius
     */
    public int radius;
    /**
     * number of lanes of balls/creators/detectors that stage will have
     */
    public int lanes;
    /**
     * boolean list that helps prevent holding down the keys
     */
    public boolean [] canPress;
    /**
     * boolean list that helps with the lazer animation
     */
    public boolean [] canDrawHit;
    /**
     * display class that helps display points, combo, level, etc
     */
    public display info;
    /**
     * int value for maximum points possible
     */
    public int maxPoints;
    /**
     * int value that keeps track of current combo state
     */
    public int combo;
    /**
     * int list that serves as input for the display class
     */
    public int [] displayList;
    /**
     * int list that helps transfer current stage's info to the next stage
     */
    public int [] carryOn;
    /**
     * int that keeps track of the total number of balls hit
     */
    public int totalBallHit;
    /**
     * int that keeps track of the total number of balls that could have been hit
     */
    public int maxBallHit;
    /**
     * int that keeps track of the number of times the player tried to hit a ball
     */
    public int attempts;

    /**
     * Method to be completed by the concrete class that 
     * runs the game using a time increment as an input
     * @param time the time increment input that determines how fast the game is run
     */
    public abstract void update (long time);

    /**
     * Method to be completed by the concrete class that paints the relevant graphics
     * for the game. It will probably be a conglomerate of paint functions of other classes
     * @param g Graphics called from the Java library awt
     */
    public abstract void paint (Graphics g); //paint function for graphics

    /**
     * Update Method for the Z key
     * Determines what the stage should do when the Z key is pressed
     */
    public abstract void Zupdate () ;

    /**
     * Update Method for the X key
     * Determines what the stage should do when the X key is pressed
     */
    public abstract void Xupdate () ;

    /**
     * Update Method for the C key
     * Determines what the stage should do when the C key is pressed
     */
    public abstract void Cupdate () ;

    /**
     * Update Method for the V key
     * Determines what the stage should do when the V key is pressed
     */
    public abstract void Vupdate () ;

    /**
     * Update Method for the B key
     * Determines what the stage should do when the B key is pressed
     */
    public abstract void Bupdate ();

    /**
     * Update Method for the N key
     * Determines what the stage should do when the N key is pressed
     */
    public abstract void Nupdate ();


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
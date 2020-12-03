package HitBallGame;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
/**
 * the ball class represents the dropping projectiles that are made by the ballCreator
 * It can be hit and killed by the ballDetector
 * Includes methods for moving and painting
 */
public class ball extends JPanel {
    /**
     * radius of the ball
     */
    public int radius;
    /**
     * points earned when the ball is hit
     */
    public int pointValue;
    /**
     * x-coordinate of the ball
     */
    public int xPos;
    /**
     * y-coordinate of the ball
     */
    public int yPos;
    /**
     * speed of the ball as it falls
     */
    public int speed;
    /**
     * whether the ball can be counted towards the maximum or not. Turns false when it has been counted once
     */
    public boolean countToMax;

    /**
     * Creates a new <code>ball</code> instance
     * It is created by the stage when the ballCreator signals the stage, and falls down until it is hit by
     * a ballDetector or reaches a certain y-coordinate
     * @param xPos x-coordinate of the ball
     * @param yPos y-coordinate of the ball
     * @param speed speed of the ball
     * @param radius radius of the ball
     */
    public ball (int xPos, int yPos, int speed, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speed = speed;
        this.radius = radius;this.radius = radius;
        this.pointValue = (int)(this.speed*1000/(this.radius)); //function to get pointValue of a ball
        this.countToMax = true;
    }

    /**
     * Method to move the ball
     * Multiplies time increment and speed to get change in position, then adds it to the original position
     * @param time determines how much to increment the position
     */
    public void update (long time) {
        yPos = yPos+ (int)(time*speed);
    }

    /**
     * paint method for the ball
     * paints an oval of radius specified from the ball at the position of the ball
     * @param g Graphics class from the java awt library
     */
    public void paint (Graphics g) {
        g.fillOval (xPos-radius/2, yPos-radius/2, radius, radius);
    }
}
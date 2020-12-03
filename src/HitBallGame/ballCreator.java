package HitBallGame;

import java.awt.Graphics;
import java.lang.Math;

/**
 * The ballCreator class represents the object that creates the balls during the course of the game
 * The ballCreator actually signals a stage to create balls by setting its make boolean true when necessary
 * Each ballCreator is assigned its own lane
 */
public class ballCreator {
    /**
     * x-coordinate of the ballCreator
     */
    private int xPos;
    /**
     * y-coordinate of the ballCreator
     */
    private int yPos;
    /**
     * radius of the ballCreator
     */
    private int radius;
    /**
     * minimum amount of time between the creation of two balls
     */
    public long minimumGap;
    /**
     * maximum amount of time the ballCreator will wait after the minimum time passes to make a ball
     */
    public long timeToMake;
    /**
     * keeps track of time to know when to make a ball
     */
    public long timeCount;
    /**
     * amount of time the ballCreator will wait after the minimum time passes to make a ball
     * Value is timeToMake*(random number from 0 to 1)
     */
    public long randTime;
    /**
     * lane value of the ballCreator. Used to group a ballCreator, a ballDetector, and an ArrayList of balls
     */
    private int lane;
    /**
     * If true, ballCreator will make a ball
     */
    public boolean make;
    /**
     * Determines whether to update the ballCreator or not
     */
    public boolean active;
    /**
     * creates a new <code>ballCreator</code>
     * It itself doesn't create a ball, but has methods for updating its make boolean true if a new ball should be made,
     * based on its time values of minimumGap and timeCount.
     * @param xPos the x-coordinate of the ballCreator
     * @param yPos the y-coordinate of the ballCreator
     * @param radius the radius of the ballCreator
     * @param minGap this value becomes the minimumGap attribute
     * @param timeToMake ths value becomes the timeToMake attribute
     * @param int lane used to group a ballCreator, a ballDetector, and an ArrayList of balls
     */
    public ballCreator (int xPos, int yPos, int radius, long minGap, long timeToMake, int lane, int active) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
        this.minimumGap = minGap;
        this.timeToMake = timeToMake;
        this.lane = lane;
        this.randTime = (int)(timeToMake*Math.random());
        this.make = false;
        if (active == 1) {
            this.active = true;
        }
        if (active == 0) {
            this.active = false;
        }
    }

    /**
     * Method to run the ballCreator
     * determines if a ball should be made, and changes it make boolean to be true if yes
     * @param time the time increment to increase timeCount. When timeCount exceeds randTime + minimumGap, make = true.
     */
    public void update (long time) {
        if (timeCount < minimumGap + randTime) {
            timeCount = timeCount + time;
        }
        if (timeCount == minimumGap + randTime) {
            make = true;
            randTime = (int)(timeToMake*Math.random());
            timeCount = 0;
        }
    }

    /**
     * paint method for the ballCreator
     * paints a thin arc
     * @param g Graphics class from the java awt library
     */
    public void paint (Graphics g) {
        g.drawArc(xPos - radius/2, yPos - radius/2, radius, radius, 0, 180);
    }

    /**
     * @return xPos of the ballCreator
     */
    public int getxPos () {
        return xPos;
    }

    /**
     * @return yPos of the ballCreator
     */
    public int getyPos () {
        return yPos;
    }

    /**
     * return lane of the ballCreator
     */
    public int getlane () {
        return lane;
    }
}
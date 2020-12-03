package HitBallGame;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * The ballDetector class represents the object that detects the balls as they fall down. Detection is triggered by pressing
 * a predesignated keyboard key for each detector. It contains methods for detecting balls as well as return their position in
 * the ArrayList of balls.
 */
public class ballDetector {
    /**
     * horizontal position of the detectorhorizontal position of the detector
     */
    public int xPos;
    /**
     * vertical position of the detectorvertical position of the detector
     */
    public int yPos;
    /**
     * lane of the detector; used to group the detector with an ArrayList of balls and a ballCreator
     */
    private int lane;
    /**
     * Creates a new <code>ballDetector</code> instnace
     * @param xPos horizontal position
     * @param yPos vertical position
     * @param lane lane of the detector; used to group the detector with an ArrayList of balls and a ballCreator
     */
    public ballDetector (int xPos, int yPos, int lane) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.lane = lane;
    }

    /**
     * paint method for ballDetectors
     * ballDetectors look like two bars with a laser between them
     * @param g Graphics class imported from the java awt library
     */
    public void paint (Graphics g) {
        g.fillRect (xPos-45, yPos-40, 10, 80);
        g.fillRect (xPos-25, yPos, 60, 1);
        g.fillRect (xPos+35, yPos-40, 10, 80);
        g.fillRect (xPos-35, yPos-5, 10, 10);
        g.fillRect (xPos+25, yPos-5, 10, 10);
    }

    /**
     * paint method for ballDetectors when they are instructed to detect balls
     * the laser between them becomes thicker
     * @param g Graphics class imported from the java awt library
     */
    public void paintHit (Graphics g) {
        g.fillRect (xPos-45, yPos-40, 10, 80);
        g.fillRect (xPos-25, yPos-2, 60, 4);
        g.fillRect (xPos+35, yPos-40, 10, 80);
        g.fillRect (xPos-35, yPos-5, 10, 10);
        g.fillRect (xPos+25, yPos-5, 10, 10);
    }

    /**
     * method that determines whether if a ball is in the vicinity of the laser of the detector
     * @param myBallList ArrayList of balls that correspond to the lane of the detector
     * @return true if a ball is passing through the laser of the detector
     */
    public boolean detect (ArrayList<ball> myBallList) {
        for (int i = 0; i < myBallList.size(); i++) {
            if (((myBallList.get(i).yPos - this.yPos) < myBallList.get(i).radius) &&
                (myBallList.get(i).yPos - this.yPos) > -1*myBallList.get(i).radius) {
                return true;
            }
        }
        return false;
    }

    /**
     * method that gives what position the ball is in the ArrayList that it is in
     * Should be used only when the detect method returns true
     * @param myBallList ArrayList of balls that correspond to the lane of the detector
     * @return the index of the ball in myBallList
     */
    public int detectPos (ArrayList<ball>myBallList) {
        for (int i = 0; i < myBallList.size(); i++) {
            if (((myBallList.get(i).yPos - this.yPos) < myBallList.get(i).radius*2) &&
                (myBallList.get(i).yPos - this.yPos) > -1*myBallList.get(i).radius*2) {
                return i;
            }
        }
        return 0;
    }
}
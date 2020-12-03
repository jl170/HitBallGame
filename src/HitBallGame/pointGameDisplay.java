package HitBallGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/**
 * the pointGameDisplay class extends the display class and
 *  helps the stage class display information and statistics
 * Its only method is its paint method, and it receives information from the stage classes via an int list
 */
public class pointGameDisplay extends display {
    /**
     * creates a <code>pointGameDisplay</code>
     * The a default font and size is settable
     */
    public pointGameDisplay () {
        this.font = new Font("Verdana", Font.PLAIN, 30);
        this.sizes = new int[5];
    }

    /**
     * paint method for the display class
     * The stage class provides the paint function with information
     * @param g Graphics class from the java awt library
     * @param displaylist an int list that the display class takes to display information
     */
    public void paint (Graphics g, int [] displayList) {
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Points: " + String.valueOf(displayList[0]), 500, 500);
        g.drawString("Level: " + displayList[1], 300, 50);
        g.drawString("Balls Hit: " + String.valueOf(displayList[2]), 500, 540);
        g.drawString("MaximumHit: " + String.valueOf(displayList[4]), 500, 580);
        g.drawString("Combo: " + String.valueOf(displayList[3] + ", Multiplier: " + String.valueOf(1 + displayList[3]*0.1)), 300, 750);
        if (displayList[2] != 0) {
            g.drawString("Accuracy: " + String.valueOf(((int)((double)displayList[2]/displayList[5]*10000))/100.0) + "%", 500, 620);
        }
    }
    /**
     * paintStatistics method for the display class
     * The stage class provides the paintStatistics method with information, and the method
     * displays statistics after the game is over
     * @param g Graphics class from the java awt library
     * @param displayList an int list that the display class takes to display information
     */
    public void paintStatistics (Graphics g, int []displayList) {
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Your Statistics", 130, 100);
        g.drawString("Total Points: " + String.valueOf(displayList[0]), 100, 140);
        g.drawString("Balls Hit: " + String.valueOf(displayList[2]), 100, 180);
        g.drawString("Total number of balls: " + String.valueOf(displayList[4]), 100, 220);
        if (displayList[2] != 0) {
            g.drawString("Accuracy: " + String.valueOf(((int)((double)displayList[2]/displayList[5]*10000))/100.0) + "%", 100, 260);
        }
    }
}
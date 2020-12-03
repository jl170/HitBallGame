package HitBallGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/**
 * the survivalGameDisplay class extends the display calss and 
 * helps the stage class display information and statistics
 * Its only method is its paint method, and it receives information from the stage classes via an int list
 */
public class survivalGameDisplay extends display {
    /**
     * creates a <code>display</code>
     * The a default font and size is settable
     */
    public survivalGameDisplay () {
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
        g.drawString("Lives: " + String.valueOf(displayList[0]), 700, 500);
        g.drawString("Balls Hit: " + String.valueOf(displayList[2]), 700, 540);
        g.drawString("MaximumHit: " + String.valueOf(displayList[3]), 700, 580);
        g.drawString("LivesPlus: " + displayList[5], 700, 620);
        g.drawString("Combo: " + String.valueOf(displayList[1]), 300, 750);
        if (displayList[2] != 0) {
            g.drawString("Accuracy: " + String.valueOf(((int)((double)displayList[2]/displayList[6]*10000))/100.0) + "%", 700, 660);
        }
        g.drawString("Level: " + displayList[8], 300, 50);
        g.drawString("Combo2: " + displayList[4], 700, 460);
        g.drawString("LivesPlus: " + displayList[5], 700, 420);
    }
    /**
     * paintStatistics method for the display class
     * The stage class provides the paintStatistics method with information, and the method
     * displays statistics after the game is over
     * @param g Graphics class from the java awt library
     * @param displayList an int list that the display class takes to display information
     */
    public void paintStatistics (Graphics g, int [] displayList) {
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Your Statistics", 130, 100);
        g.drawString("Balls Hit: " + String.valueOf(displayList[2]), 100, 140);
        g.drawString("Total number of balls: " + String.valueOf(displayList[3]), 100, 180);
        if (displayList[2] != 0) {
            g.drawString("Accuracy: " + String.valueOf(((int)((double)displayList[2]/displayList[6]*10000))/100.0) + "%", 100, 220);
        }
        g.drawString("You died at level: " + displayList[8], 100, 260);
    }
}
package HitBallGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/**
 * the display class helps the stage class display information and statistics
 * Its paint method receives information from the stage classes via an int list, and displays the information during
 * the game. The paintStatistics method displays statistics for the game after the game is over.
 */
public abstract class display {
    /**
     * Font of the text that the display class paints
     */
    public Font font;
    /**
     * Size for the texts that the class will draw
     */
    public int [] sizes;
    /**
     * paint method for the display class
     * The stage class provides the paint function with information
     * @param g Graphics class from the java awt library
     * @param displaylist an int list that the display class takes to display information
     */
    public abstract void paint (Graphics g, int [] displayList);
    /**
     * paintStatistics method for the display class
     * The stage class provides the paintStatistics method with information, and the method
     * displays statistics after the game is over
     * @param g Graphics class from the java awt library
     * @param displayList an int list that the display class takes to display information
     */
    public abstract void paintStatistics (Graphics g, int [] displayList);

}
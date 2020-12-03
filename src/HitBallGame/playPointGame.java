package HitBallGame;

import javax.swing.JFrame;

/**
 * @author JohnLee02123
 * The playPointGame class provides the framework on which to instantiate the PointGame.
 */
public class playPointGame
{
    /**
     * plays the pointsGame mode of the game.
     * Runs through the pointsGame stage by stage, and creates a JFrame as each new stage is made
     * @param args is not used.
     */
    public static void play(){
        int timeIncrement = 25;
        JFrame frame = new JFrame("Level 1"); // create new frame

        pointGame s = new pointGame(1000, 1000, "p-1-1"); //create stage 1

        frame.add(s); //add stage to frame

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1300, 1000);

        long currentTime = System.currentTimeMillis();  //these two time values are used below for modulating FPS
        long currentTime2 = System.currentTimeMillis();
        while (s.inPlay == true){
            s.requestFocus(); //request focus
            s.update(1); //calls the big update method of the stage
            frame.repaint(); //paints the stage after update
            while (currentTime < currentTime2+timeIncrement) { //forces the while loop to wait until 25 milliseconds passes
                currentTime = System.currentTimeMillis();
            }
            currentTime2 = System.currentTimeMillis(); //resets the time values so that the time loop can begin again
        }
        int [] toNext = s.carryOn;


        JFrame frame2 = new JFrame("Level 2"); //creates new frame for stage 2
        pointGame a = new pointGame(1000, 1000, "p-1-2"); //creates stage 2
        a.transfer(toNext);//points and maxPoints and combo values from the end of stage 1 are moved to stage 2
        frame2.add(a); //adds stage 2 to frame

        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
        frame2.setSize(1300, 1000);

        frame.dispose();

        while (a.inPlay == true){
            a.requestFocus(); //request focus
            a.update(1); //calls the big update method of stage 2
            frame2.repaint(); //paints the stage after update
            while (currentTime < currentTime2+timeIncrement) { //forces the while loop to wait until 25 milliseconds passes
                currentTime = System.currentTimeMillis();
            }
            currentTime2 = System.currentTimeMillis(); //resets the time values so that the time loop can begin again
        }
        toNext = a.carryOn;

        //documentation is symmetric

        JFrame frame3 = new JFrame("Level 3");
        pointGame b = new pointGame(1000, 1000, "p-1-3");
        b.transfer(toNext);
        frame3.add(b);

        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setVisible(true);
        frame3.setSize(1300, 1000);

        frame2.dispose();

        while (b.inPlay == true){
            b.requestFocus();
            b.update(1);
            frame3.repaint();
            while (currentTime < currentTime2+timeIncrement) {
                currentTime = System.currentTimeMillis();
            }
            currentTime2 = System.currentTimeMillis();
        }
        toNext = b.carryOn;

        //documentation is symmetric

        JFrame frame4 = new JFrame("Final Level: 4");
        pointGame c = new pointGame(1000, 1000, "p-1-4");
        c.transfer(toNext);
        frame4.add(c);

        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.setVisible(true);
        frame4.setSize(1300, 1000);

        frame3.dispose();

        while (c.inPlay == true){
            c.requestFocus();
            c.update(1);
            frame4.repaint();
            while (currentTime < currentTime2+timeIncrement) {
                currentTime = System.currentTimeMillis();
            }
            currentTime2 = System.currentTimeMillis();
        }
        frame4.repaint();
    }
}

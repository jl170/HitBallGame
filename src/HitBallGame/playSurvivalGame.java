package HitBallGame;

import javax.swing.JFrame;
/**
 * @author JohnLee02123
 * The playSurvivalGame provides the framework on which to instantiate the SurvivalGame.
 */
public class playSurvivalGame {
    public static void play(){
        int timeIncrement = 25;
        JFrame frame = new JFrame("Hello World!"); // create new frame

        survivalGame s = new survivalGame(1000, 1000, "s-1-1"); //create stage 1

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
        frame.repaint();
    }
}

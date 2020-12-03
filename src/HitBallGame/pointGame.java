package HitBallGame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The pointGame class extends the stage abstract class and represents a mode of the game in which
 * players go through different levels of increasing diffculity. The game does not end until the last
 * stage is complete, and the objective is to get as many points as possible.
 */
public class pointGame extends stage implements KeyListener {
    /**
     * number of balls that the pointGame stage will make
     */
    public int ballToProduce;
    /**
     * Creates a new <code>pointGame</code> instance
     * @param width the width of the level
     * @param height the height of the level
     * @param level the name of the level; the play class will call certain pre-made levels by their names
     */
    public pointGame (int width, int height, String level) {
        this.addKeyListener(this); //enable keyboard input
        this.creatorList = new ArrayList<ballCreator> (0); //ArrayList of ballCreators
        this.detectorList = new ArrayList<ballDetector> (0); //ArrayList of ballDetctors
        this.allBall = new ArrayList<ArrayList<ball>> (0); //ArrayList of ArrayList of balls
        this.width = width;
        this.height = height;
        this.ballCount = ballCount;
        this.inPlay = true;
        this.points = 0;
        this.keyDown = new boolean [256]; //for keyboard input
        this.info = new pointGameDisplay ();
        this.maxPoints = 0;
        this.displayList = new int[10]; //set to 10 so that editor doesn't have to worry about editing size later
        this.carryOn = new int[10]; //set to 10 so that editor doesn't have to worry about editing size later
        this.totalBallHit = 0;
        this.maxBallHit = 0;

        for (int i = 0; i < keyDown.length; i++) {
            keyDown [i] = false; //set keyDown components to false (no keys are pressed)
        }
        if (level.equals("p-1-1")) {
            creatorList.add(0, new ballCreator (100, 100, 50, 10, 10, 0, 1)); //add a creator
            detectorList.add(0, new ballDetector (100, 600, 0)); //add a detector under the creator
            this.level = 1;
            this.speed = 3;
            this.radius = 16;
            this.ballToProduce = 30; //30 balls will be produced in this stage
            this.ballCount = this.ballToProduce; //in the pointGame, ballCount would serve the purpose of counting
            //the number of balls that have not disappeared
            for (int i = 0; i < creatorList.size(); i++) {
                allBall.add(new ArrayList<ball>()); //create arrayLists to hold balls created by creator
            }
            this.lanes = 1; //set lane number for this stage
            this.canPress = new boolean [lanes]; //craete canPress boolean list according to number of lanes
            this.canDrawHit = new boolean [lanes]; //craete canDrawHit boolean list according to number of lanes
            for (int i = 0; i < lanes; i++) { //calibrate the two boolean lists
                canPress[i] = true;
                canDrawHit[i] = true;
            }
        }
        if (level.equals("p-1-2")) { //this level will have two lanes, creators, and detectors; everything else is symmetric
            creatorList.add(0, new ballCreator (100, 100, 50, 15, 20, 0, 1));
            creatorList.add(1, new ballCreator (200, 100, 50, 20, 14, 1, 1));
            detectorList.add(0, new ballDetector (100, 600, 0));
            detectorList.add(1, new ballDetector (200, 600, 1));
            this.level = 2;
            this.speed = 5;
            this.radius = 25;
            this.ballToProduce = 50;
            this.ballCount = this.ballToProduce;
            for (int i = 0; i < creatorList.size(); i++) {
                allBall.add(new ArrayList<ball>());
            }
            this.lanes = 2;
            this.canPress = new boolean [lanes];
            this.canDrawHit = new boolean [lanes];
            for (int i = 0; i < lanes; i++) {
                canPress[i] = true;
                canDrawHit[i] = true;
            }

        }
        if (level.equals("p-1-3")) { //this level will have three lanes, creators, and detectors; everything else is symmetric
            creatorList.add(0, new ballCreator (100, 100, 50, 22, 23, 0, 1));
            creatorList.add(1, new ballCreator (200, 100, 50, 18, 20, 1, 1));
            creatorList.add(2, new ballCreator (300, 100, 50, 25, 25, 2, 1));
            detectorList.add(0, new ballDetector (100, 600, 0));
            detectorList.add(1, new ballDetector (200, 600, 1));
            detectorList.add(2, new ballDetector (300, 600, 2));
            this.level = 3;
            this.speed = 5;
            this.radius = 25;
            this.ballToProduce = 70;
            this.ballCount = this.ballToProduce;
            for (int i = 0; i < creatorList.size(); i++) {
                allBall.add(new ArrayList<ball>());
            }
            this.lanes = 3;
            this.canPress = new boolean [lanes];
            this.canDrawHit = new boolean [lanes];
            for (int i = 0; i < lanes; i++) {
                canPress[i] = true;
                canDrawHit[i] = true;
            }

        }
        if (level.equals("p-1-4")) { // this level will have four lanes, detectors, and creators; everything else is symmetric
            creatorList.add(0, new ballCreator (100, 100, 50, 23, 23, 0, 1));
            creatorList.add(1, new ballCreator (200, 100, 50, 19, 20, 1, 1));
            creatorList.add(2, new ballCreator (300, 100, 50, 26, 25, 2, 1));
            creatorList.add(3, new ballCreator (400, 100, 50, 21, 18, 3, 1));
            detectorList.add(0, new ballDetector (100, 600, 0));
            detectorList.add(1, new ballDetector (200, 600, 1));
            detectorList.add(2, new ballDetector (300, 600, 2));
            detectorList.add(3, new ballDetector (400, 600, 3));
            this.level = 4;
            this.speed = 5;
            this.radius = 25;
            this.ballToProduce = 90;
            this.ballCount = this.ballToProduce;
            for (int i = 0; i < creatorList.size(); i++) {
                allBall.add(new ArrayList<ball>());
            }
            this.lanes = 4;
            this.canPress = new boolean [lanes];
            this.canDrawHit = new boolean [lanes];
            for (int i = 0; i < lanes; i++) {
                canPress[i] = true;
                canDrawHit[i] = true;
            }

        }
    }

    /**
     * update Method; used to make the game progress
     * accesses update functions of all balls, ballCreators, and key update Methods
     * @param time determines how fast the game will run
     */
    public void update (long time) {
        for (int i = 0; i < creatorList.size(); i++) { //updates all creatorlists
            if (creatorList.get(i).active) {
                creatorList.get(i).update(time);
            }
            if (creatorList.get(i).make == true && ballToProduce > 0) { //if the creator signals the stage, and the stage
                //allows production, create a ball in the lane of that creator
                //using properties of the ball that the stage specifies, such as
                //speed and radius.
                allBall.get(creatorList.get(i).getlane()).add(
                    new ball(creatorList.get(i).getxPos(), creatorList.get(i).getyPos(),
                        this.speed, this.radius));
                creatorList.get(i).make = false;
                ballToProduce--;
            }
        }
        for (int i = 0; i < allBall.size(); i++) { //updates all balls
            for (int j = 0; j < allBall.get(i).size(); j++) { //use double for loop to access all balls
                allBall.get(i).get(j).update(time); //accesses update function of ball
                if (allBall.get(i).get(j).yPos > 550 &&
                    allBall.get(i).get(j).countToMax) {
                    //When ball reaches 550,
                    maxPoints = maxPoints + allBall.get(i).get(j).pointValue; //keep track of maximum points possible
                    maxBallHit++; //keep track of maximum no. of balls that could have been hit
                    allBall.get(i).get(j).countToMax = false; //countToMax turned false to prevent counting
                    // to maximum points false
                }
                if (allBall.get(i).get(j).yPos > 700) { //when ball reaches 700, terminate the ball, decrease
                    //ballCount, and combo
                    allBall.get(i).remove(j);
                    ballCount--;
                    combo = 0;
                }
            }
        }
        //Determines which keys should be updated depending on the number of lanes
        if (lanes == 1) {
            Zupdate();
        }
        if (lanes == 2) {
            Zupdate();
            Xupdate();
        }
        if (lanes == 3) {
            Zupdate();
            Xupdate();
            Cupdate();
        }
        if (lanes == 4) {
            Zupdate();
            Xupdate();
            Cupdate();
            Vupdate();
        }
        if (lanes == 5) {
            Zupdate();
            Xupdate();
            Cupdate();
            Vupdate();
            Bupdate();
        }
        if (lanes == 6) {
            Zupdate();
            Xupdate();
            Cupdate();
            Vupdate();
            Bupdate();
            Nupdate();
        }
        //When stage is over, update the carryOn list to transfer information from this level to the next
        if (ballCount == 0) {
            inPlay = false;
            carryOn[0] = points;
            carryOn[1] = maxBallHit;
            carryOn[2] = combo;
            carryOn[3] = totalBallHit;
            carryOn[4] = attempts;
        }
    }

    /**
     * Update Method for the Z key
     * Determines what the stage should do when the Z key is pressed
     */
    public void Zupdate () { //update function responsible for the Z key
        if (keyDown[KeyEvent.VK_Z] == true  && canPress[0] == true)//to prevent holding down
        {
            if (detectorList.get(0).detect(allBall.get(0))) {
                int target = detectorList.get(0).detectPos(allBall.get(0));
                points = points + (int)(allBall.get(0).get(target).pointValue*
                    (1 + combo*0.1)); //if ball is detected, its location in allBall Arraylist is obtained
                allBall.get(0).remove(target); //ball is removed if detected
                ballCount--;
                totalBallHit++;
                combo++; //combo increased
            }
            else {
                points = points - 100; //decreases points when key is pressed but doesn't hit ball
                combo = 0; //combo dies when mistake is made
            }
            canPress[0] = false; //to prevent holding down
            canDrawHit[0] = true; //to draw lazer animation
            attempts++;
        }
        if (keyDown[KeyEvent.VK_Z] == false) {
            canPress[0] = true; //allows player to hit the ball again
        }
    }

    /**
     * Update Method for the X key
     * Determines what the stage should do when the X key is pressed
     */
    public void Xupdate () { //symmetrical documentation as Zupdate
        if (keyDown[KeyEvent.VK_X] == true && canPress[1] == true) {
            if (detectorList.get(1).detect(allBall.get(1))) {
                int target = detectorList.get(1).detectPos(allBall.get(1));
                points = points + allBall.get(1).get(target).pointValue;
                allBall.get(1).remove(target);
                ballCount--;
                totalBallHit++;
                combo++;
            }
            else {
                points = points - 100;
                combo = 0;
            }
            canPress[1] = false;
            canDrawHit[1] = true;
            attempts++;
        }
        if (keyDown[KeyEvent.VK_X] == false) {
            canPress[1] = true;
        }
    }

    /**
     * Update Method for the C key
     * Determines what the stage should do when the C key is pressed
     */
    public void Cupdate () { //symmetrical documentation as Zupdate
        if (keyDown[KeyEvent.VK_C] == true && canPress[2] == true) {
            if (detectorList.get(2).detect(allBall.get(2))) {
                int target = detectorList.get(2).detectPos(allBall.get(2));
                points = points + allBall.get(2).get(target).pointValue;
                allBall.get(2).remove(target);
                ballCount--;
                totalBallHit++;
                combo++;
            }
            else {
                points = points - 100;
                combo = 0;
            }
            canPress[2] = false;
            canDrawHit[2] = true;
            attempts++;
        }
        if (keyDown[KeyEvent.VK_C] == false) {
            canPress[2] = true;
        }
    }

    /**
     * Update Method for the V key
     * Determines what the stage should do when the V key is pressed
     */
    public void Vupdate () { //symmetrical documentation as Zupdate
        if (keyDown[KeyEvent.VK_V] == true && canPress[3] == true) {
            if (detectorList.get(3).detect(allBall.get(3))) {
                int target = detectorList.get(3).detectPos(allBall.get(3));
                points = points + allBall.get(3).get(target).pointValue;
                allBall.get(3).remove(target);
                ballCount--;
                totalBallHit++;
                combo++;
            }
            else {
                points = points - 100;
                combo = 0;
            }
            canPress[3] = false;
            canDrawHit[3] = true;
            attempts++;
        }
        if (keyDown[KeyEvent.VK_V] == false) {
            canPress[3] = true;
        }
    }

    /**
     * Update Method for the B key
     * Determines what the stage should do when the B key is pressed
     */
    public void Bupdate () { //symmetrical documentation as Zupdate
        if (keyDown[KeyEvent.VK_B] == true && canPress[4] == true) {
            if (detectorList.get(4).detect(allBall.get(4))) {
                int target = detectorList.get(4).detectPos(allBall.get(4));
                points = points + allBall.get(4).get(target).pointValue;
                allBall.get(4).remove(target);
                ballCount--;
                totalBallHit++;
                combo++;
            }
            else {
                points = points - 100;
                combo = 0;
            }
            canPress[4] = false;
            canDrawHit[4] = true;
            attempts++;
        }
        if (keyDown[KeyEvent.VK_B] == false) {
            canPress[4] = true;
        }
    }

    /**
     * Update Method for the N key
     * Determines what the stage should do when the N key is pressed
     */
    public void Nupdate () { //symmetrical documentation as Zupdate
        if (keyDown[KeyEvent.VK_N] == true && canPress[5] == true) {
            if (detectorList.get(5).detect(allBall.get(5))) {
                int target = detectorList.get(5).detectPos(allBall.get(5));
                points = points + allBall.get(5).get(target).pointValue;
                allBall.get(5).remove(target);
                ballCount--;
                totalBallHit++;
                combo++;
            }
            else {
                points = points - 100;
                combo = 0;
            }
            canPress[5] = false;
            canDrawHit[5] = true;
            attempts++;
        }
        if (keyDown[KeyEvent.VK_N] == false) {
            canPress[5] = true;
        }
    }

    /**
     * Method that paints all ballCreators, balls, ballDetectors, the display class, and
     * sends the info to be displayed to the display class,
     * @param g Graphics class imported from the java awt library
     */
    public void paint (Graphics g) {
        if (inPlay == false && level == 4) {
            info.paintStatistics (g, displayList);
            return;
        }
        g.fillRect(1200,0,110,800);
        for (int i = 0; i < creatorList.size(); i++) { //loop through all creators to paint them
            creatorList.get(i).paint(g);
        }
        for (int i = 0; i < allBall.size(); i++) { //loop through all balls to paint them
            for (int j = 0; j < allBall.get(i).size(); j++) {
                allBall.get(i).get(j).paint(g);
            }
        }
        for (int i = 0; i < detectorList.size(); i++) { //loop through all detectors to paint them
            if (canDrawHit[i]) {
                detectorList.get(i).paintHit(g); //different graphics for detection
                canDrawHit[i] = false;
                canPress[i] = false;
            }
            else {
                detectorList.get(i).paint(g);
            }
        }
        //sends information to the display class to be displayed
        displayList[0] = points;
        displayList[1] = level;
        displayList[2] = totalBallHit;
        displayList[3] = combo;
        displayList[4] = maxBallHit;
        displayList[5] = attempts;
        info.paint(g, displayList);
    }

    /**
     * Method that helps transfer data from one stage to the next
     * @param toNext int list that carries the info to be carried on
     */
    public void transfer (int[] toNext) {
        this.points = toNext[0];
        this.maxBallHit = toNext[1];
        this.combo = toNext[2];
        this.totalBallHit = toNext[3];
        this.attempts = toNext[4];
    }
}

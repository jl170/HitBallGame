package HitBallGame;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class survivalGame extends stage implements KeyListener {
    /**
     * Number of lives player has
     */
    public int lives;
    /**
     * Calculates how life bonus at a combo Checkpoint
     */
    public int comboMultiplier;
    /**
     * Whether the combo should give life bonus or not
     */
    public boolean livesPlus;
    /**
     * second combo parameter for calculating life bonus
     */
    public int combo2;
    /**
     * Boolean list for keeping track of and controlling level increases
     */
    public boolean [] levelUp;
    public survivalGame (int width, int height, String level) {
        this.addKeyListener(this);
        this.creatorList = new ArrayList<ballCreator> (0);
        this.detectorList = new ArrayList<ballDetector> (0);
        this.allBall = new ArrayList<ArrayList<ball>> (0);
        this.width = width;
        this.height = height;
        this.inPlay = true;
        this.points = 0;
        this.keyDown = new boolean [256];
        this.info = new survivalGameDisplay ();
        this.maxPoints = 0;
        this.displayList = new int[10];
        this.lives = 100;
        this.comboMultiplier = 1;
        this.levelUp = new boolean [30];

        for (int i = 0; i < levelUp.length; i++) {
            levelUp[i] = true;
        }
        for (int i = 0; i < keyDown.length; i++) {
            keyDown [i] = false;
        }
        if (level.equals("s-1-1")) {
            creatorList.add(0, new ballCreator (100, 100, 50, 30, 50, 0, 1));
            creatorList.add(1, new ballCreator (200, 100, 50, 30, 60, 1, 0));
            creatorList.add(2, new ballCreator (300, 100, 50, 10, 50, 2, 0));
            creatorList.add(3, new ballCreator (400, 100, 50, 20, 35, 3, 0));
            creatorList.add(4, new ballCreator (500, 100, 50, 15, 50, 4, 0));
            creatorList.add(5, new ballCreator (600, 100, 50, 25, 50, 5, 0));
            detectorList.add(0, new ballDetector (100, 600, 0));
            detectorList.add(1, new ballDetector (200, 600, 1));
            detectorList.add(2, new ballDetector (300, 600, 2));
            detectorList.add(3, new ballDetector (400, 600, 3));
            detectorList.add(4, new ballDetector (500, 600, 4));
            detectorList.add(5, new ballDetector (600, 600, 5));
            this.level = 0;
            this.speed = 3;
            this.radius = 25;
            for (int i = 0; i < creatorList.size(); i++) {
                allBall.add(new ArrayList<ball>());
            }
            this.lanes = 6;
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
        //Update creatorlists
        for (int i = 0; i < creatorList.size(); i++) {
            if (creatorList.get(i).active) {
                creatorList.get(i).update(time);
            }
            if (creatorList.get(i).make == true) {
                allBall.get(creatorList.get(i).getlane()).add(
                    new ball(creatorList.get(i).getxPos(), creatorList.get(i).getyPos(),
                        this.speed, this.radius));
                creatorList.get(i).make = false;
            }
        }
        //Update balls
        for (int i = 0; i < allBall.size(); i++) {
            for (int j = 0; j < allBall.get(i).size(); j++) {
                allBall.get(i).get(j).update(time);
                if (allBall.get(i).get(j).yPos > 550 &&
                    allBall.get(i).get(j).countToMax) {
                    maxBallHit++;
                    allBall.get(i).get(j).countToMax = false;
                }
                if (allBall.get(i).get(j).yPos > 700) {
                    allBall.get(i).remove(j);
                    combo = 0;
                    combo2 = 0;
                    comboMultiplier = 1;
                    lives = lives - 5;
                }
            }
        }
        //Update keys
        Zupdate();
        Xupdate();
        Cupdate();
        Vupdate();
        Bupdate();
        Nupdate();
        //Update lives
        if (lives <= 0) {
            inPlay = false;
        }
        if (combo2 == 10 && livesPlus) {
            lives = lives + 5*comboMultiplier;
            livesPlus = false;
            combo2 = 0;
            comboMultiplier++;
        }

        if (totalBallHit == 10 && levelUp[1]) {
            creatorList.get(1).active = true;
            creatorList.get(1).timeCount = 0;
            levelUp[1] = false;
            level++;
        }
        if (totalBallHit == 30 && levelUp[2]) {
            creatorList.get(0).timeCount = 0;
            creatorList.get(1).timeCount = 0;
            creatorList.get(0).minimumGap = 25;
            creatorList.get(0).timeToMake = 45;
            creatorList.get(1).minimumGap = 25;
            creatorList.get(1).timeToMake = 55;
            levelUp[2] = false;
            level++;
        }
        if (totalBallHit == 50 && levelUp[3]) {
            creatorList.get(2).active = true;
            creatorList.get(2).timeCount = 0;
            creatorList.get(2).minimumGap = 30;
            creatorList.get(2).timeToMake = 70;
            levelUp[3] = false;
            level++;
        }
        if (totalBallHit == 80 && levelUp[4]) {
            creatorList.get(2).timeCount = 0;
            creatorList.get(2).timeToMake = 50;
            levelUp[4] = false;
            level++;
        }
        if (totalBallHit == 120 && levelUp[5]) {
            creatorList.get(0).timeCount = 0;
            creatorList.get(1).timeCount = 0;
            creatorList.get(0).timeToMake = 40;
            creatorList.get(1).timeToMake = 50;
            levelUp[5] = false;
            level++;
        }
        if (totalBallHit == 160 && levelUp[6]) {
            creatorList.get(3).active = true;
            creatorList.get(3).timeCount = 0;
            creatorList.get(3).minimumGap = 40;
            creatorList.get(3).timeToMake = 60;
            levelUp[6] = false;
            level++;
        }
        if (totalBallHit == 210 && levelUp[7]) {
            creatorList.get(3).timeCount = 0;
            creatorList.get(2).timeCount = 0;
            creatorList.get(3).timeToMake = 50;
            creatorList.get(2).timeToMake = 45;
            levelUp[7] = false;
            level++;
        }
        if (totalBallHit == 260 && levelUp[8]) {
            speed = 4;
            for (int i = 0; i < allBall.size(); i++) {
                for (int j = 0; j < allBall.get(i).size(); j++) {
                    allBall.get(i).get(j).speed = 4;
                }
            }
            levelUp[8] = false;
            level++;
        }
        if (totalBallHit == 320 && levelUp[9]) {
            creatorList.get(2).timeCount = 0;
            creatorList.get(3).timeCount = 0;
            creatorList.get(2).minimumGap = 27;
            creatorList.get(3).minimumGap = 35;
            levelUp[9] = false;
            level++;
        }
        if (totalBallHit == 380 && levelUp[10]) {
            creatorList.get(4).active = true;
            creatorList.get(4).timeCount = 0;
            creatorList.get(4).minimumGap = 50;
            creatorList.get(4).timeToMake = 70;
            levelUp[10] = false;
            level++;
        }
        if (totalBallHit == 450 && levelUp[11]) {
            creatorList.get(1).timeCount = 0;
            creatorList.get(3).timeCount = 0;
            creatorList.get(4).timeCount = 0;
            creatorList.get(1).minimumGap = 23;
            creatorList.get(3).timeToMake = 45;
            creatorList.get(4).timeToMake = 60;
            levelUp[11] = false;
            level++;
        }
        if (totalBallHit == 530 && levelUp[12]) {
            creatorList.get(4).timeCount = 0;
            creatorList.get(2).timeCount = 0;
            creatorList.get(0).timeCount = 0;
            creatorList.get(4).minimumGap = 45;
            creatorList.get(2).timeToMake = 40;
            creatorList.get(0).minimumGap = 20;
            levelUp[12] = false;
            level++;
        }
        if (totalBallHit == 610 && levelUp[13]) {
            creatorList.get(3).timeCount = 0;
            creatorList.get(4).timeCount = 0;
            creatorList.get(1).timeCount = 0;
            creatorList.get(3).minimumGap = 30;
            creatorList.get(4).timeToMake = 50;
            creatorList.get(1).minimumGap = 20;
            levelUp[13] = false;
            level++;
        }
        if (totalBallHit == 700 && levelUp[14]) {
            creatorList.get(5).active = true;
            creatorList.get(5).timeCount = 0;
            creatorList.get(5).minimumGap = 60;
            creatorList.get(5).timeToMake = 80;
            levelUp[14] = false;
            level++;
        }
        if (totalBallHit == 800 && levelUp[15]) {
            creatorList.get(5).timeCount = 0;
            creatorList.get(2).timeCount = 0;
            creatorList.get(3).timeCount = 0;
            creatorList.get(5).minimumGap = 50;
            creatorList.get(5).timeToMake = 70;
            creatorList.get(2).minimumGap = 25;
            creatorList.get(3).minimumGap = 25;
            levelUp[15] = false;
            level++;
        }
        if (totalBallHit == 900 && levelUp[16]) {
            speed = 5;
            for (int i = 0; i < allBall.size(); i++) {
                for (int j = 0; j < allBall.get(i).size(); j++) {
                    allBall.get(i).get(j).speed = 5;
                }
            }
            levelUp[16] = false;
            level++;
        }
        if (totalBallHit == 1000 && levelUp[17]) {
            creatorList.get(5).timeCount = 0;
            creatorList.get(4).timeCount = 0;
            creatorList.get(3).timeCount = 0;
            creatorList.get(1).timeCount = 0;
            creatorList.get(5).minimumGap = 45;
            creatorList.get(5).timeToMake = 60;
            creatorList.get(4).minimumGap = 40;
            creatorList.get(3).minimumGap = 20;
            creatorList.get(1).timeToMake = 45;
            levelUp[17] = false;
            level++;
        }
        if (totalBallHit == 1100 && levelUp[18]) {
            creatorList.get(5).timeCount = 0;
            creatorList.get(3).timeCount = 0;
            creatorList.get(1).timeCount = 0;
            creatorList.get(5).minimumGap = 40;
            creatorList.get(3).minimumGap = 17;
            creatorList.get(1).minimumGap = 17;
            levelUp[18] = false;
            level++;
        }
        if (totalBallHit == 1200 && levelUp[19]) {
            creatorList.get(4).timeCount = 0;
            creatorList.get(2).timeCount = 0;
            creatorList.get(0).timeCount = 0;
            creatorList.get(4).minimumGap = 35;
            creatorList.get(2).minimumGap = 20;
            creatorList.get(0).minimumGap = 17;
            levelUp[19] = false;
            level++;
        }
        if (totalBallHit == 1300 && levelUp[20]) {
            creatorList.get(4).timeCount = 0;
            creatorList.get(2).timeCount = 0;
            creatorList.get(0).timeCount = 0;
            creatorList.get(4).minimumGap = 35;
            creatorList.get(2).minimumGap = 20;
            creatorList.get(0).minimumGap = 17;
            levelUp[20] = false;
            level++;
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
                totalBallHit++;
                combo++; //combo increased
                combo2++;
                livesPlus = true;
            }
            else {
                combo = 0; //combo dies when mistake is made
                combo2 = 0;
                comboMultiplier = 1;
                lives--;
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
                totalBallHit++;
                combo++;
                combo2++;
                livesPlus = true;
            }
            else {
                combo = 0;
                combo2 = 0;
                comboMultiplier = 1;
                lives--;
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
                totalBallHit++;
                combo++;
                combo2++;
                livesPlus = true;
            }
            else {
                combo = 0;
                combo2 = 0;
                comboMultiplier = 1;
                lives--;
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
                totalBallHit++;
                combo++;
                combo2++;
                livesPlus = true;
            }
            else {
                combo = 0;
                combo2 = 0;
                comboMultiplier = 1;
                lives--;
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
                totalBallHit++;
                combo++;
                combo2++;
                livesPlus = true;
            }
            else {
                combo = 0;
                combo2 = 0;
                comboMultiplier = 1;
                lives--;
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
                totalBallHit++;
                combo++;
                combo2++;
                livesPlus = true;
            }
            else {
                combo = 0;
                combo2 = 0;
                comboMultiplier = 1;
                lives--;
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
        if (inPlay == false) {
            info.paintStatistics (g, displayList);
            return;
        }
        g.fillRect(1200,0,110,800);
        for (int i = 0; i < creatorList.size(); i++) {
            creatorList.get(i).paint(g);
        }
        for (int i = 0; i < allBall.size(); i++) {
            for (int j = 0; j < allBall.get(i).size(); j++) {
                allBall.get(i).get(j).paint(g);
            }
        }
        for (int i = 0; i < detectorList.size(); i++) {
            if (canDrawHit[i]) {
                detectorList.get(i).paintHit(g);
                canDrawHit[i] = false;
                canPress[i] = false;
            }
            else {
                detectorList.get(i).paint(g);
            }
        }
        displayList[0] = lives;
        displayList[1] = combo;
        displayList[2] = totalBallHit;
        displayList[3] = maxBallHit;
        displayList[4] = combo2;
        if (livesPlus) {
            displayList[5] = 1;
        }
        if (livesPlus == false) {
            displayList[5] = 0;
        }
        info.paint(g, displayList);
        displayList[6] = attempts;
        displayList[7] = comboMultiplier;
        displayList[8] = level;
    }
}
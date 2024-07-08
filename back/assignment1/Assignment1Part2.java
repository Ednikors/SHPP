package com.shpp.p2p.cs.bkuzhel.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part2 extends KarelTheRobot{
    public void run() throws Exception {
        // Karel starts from ground and build column up till he hit the "ceiling",
        // then he turns back and go to the next column
        while (frontIsClear()) { // while no obstacle(end of the world)
            turnLeft();
            buildColumn(); // Karel builds column up
            goBack(); // then goes back
            turnLeft(); // turn left and go to the next column to be built
            moveToNextColumn();
        }
        turnLeft(); // if Karel reaches the end of the world, he builds the last column
        buildColumn();
    }

    // using this method Karel can build column from bottom to top
    public void buildColumn() throws Exception {
        while (frontIsClear()) { // while no wall ahead
            if (noBeepersPresent()) { // and no beepers at the current location
                putBeeper(); // put beeper
            }
            move(); // if Karel placed the beeper he moves to another cell
        }
        if (noBeepersPresent()) { // if wall ahead Karel can't put beeper into last cell
            putBeeper();        // this part of code fixes that
        }
    }

        // when Karel has built a column,
        // using this method he turns back to the bottom
    public void goBack() throws Exception{
        turnAround();
        while (frontIsClear()){
            move();
        }
    }

    // this method directs Karel to the next column
    public void moveToNextColumn() throws Exception{
        move();
        move();
        move();
        move();
    }

    // this method allows Karel to turn around
    public void turnAround() throws Exception{
        turnLeft();
        turnLeft();
    }
}

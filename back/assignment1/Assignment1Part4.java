package com.shpp.p2p.cs.kbohdan.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part4 extends KarelTheRobot {
    public void run() throws Exception {
        buildToTheRight();
    }

    // this method puts beepers together with skips
    // in a "checkers" pattern to the right side in one row
    // then it starts method which doing the same but to the left side
    private void buildToTheRight() throws Exception {
        fillCheckerLine();    // this method fill up row in a "checkers pattern" with beepers to the right side
        if (beepersPresent()) { // this if statement uses to check if in current cell exists beeper
            turnLeft();       // if exists, this loop directs Karel so that the next row of beepers is shifted one cell left
            if (frontIsClear()) {
                move();
                if (leftIsBlocked() && rightIsBlocked() && frontIsClear()) { // this if statement checks if world is Nx1 dimension
                    move(); // if true it directs Karel up and makes him build an upward pattern
                    fillCheckerLine();
                }
                // if world is not Nx1 dimension, Karel goes to the next row with shifting one cell
                // and build "checkers" pattern to the right side
                else {
                    if (leftIsClear()) {
                        turnLeft();
                        move();
                        buildToTheLeft();
                    }
                }
            }
        } else { // if in current cell beeper don`t exists, Karel goes to another row
            turnLeft(); // and starts build a pattern to the left side without shifting cell to the left
            if (frontIsClear()) {
                move();
                turnLeft();
                buildToTheLeft();
            }
        }

    }

    // doing the same as previous method but to the left side
    private void buildToTheLeft() throws Exception {
        fillCheckerLine();    // this method fill up row in a "checkers pattern" with beepers to the left side
        if (beepersPresent()) { // this if statement uses to check if in current cell exists beeper
            turnRight();      // if exists, this loop directs Karel so that the next row of beepers is shifted one cell right
            if (frontIsClear()) {
                move();
                turnRight();
                move();
                buildToTheRight();
            }
        } else {         // if in current cell beeper don`t exists, Karel goes to another row
            turnRight(); // and starts build a pattern to the right side without shifting cell to the right
            if (frontIsClear()) {
                move();
                turnRight();
                buildToTheRight();
            }
        }
    }

    // this method is placing beepers in "checkers" pattern
    public void fillCheckerLine() throws Exception {
        putBeeper(); // place beeper
        while (frontIsClear()) { // while no end of the world
            move(); // move to next cell
            if (frontIsClear()) { // if no obstacle(end of the world) move to next cell and place beeper
                move();
                putBeeper();
            }
        }
    }

    // this method allows Karel to turn right
    public void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

}
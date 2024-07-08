package com.shpp.p2p.cs.bkuzhel.assignment1;


import com.shpp.karel.KarelTheRobot;

public class Assignment1Part3 extends KarelTheRobot {
    public void run() throws Exception {
        fillRow();
        findCenter();
    }

    // this method is used to fill a south row with beepers
    public void fillRow() throws Exception {
        while (frontIsClear()) { // while no end of the world
            putBeeper(); // put beeper and go to the next cell
            move();
        }
        putBeeper(); // if Karel in last cell put beeper
    }

    // using this method Karel can find the center of the row
    public void findCenter() throws Exception {
        while (frontIsClear()) { // go to the opposite side of the world
            move();
        }
        turnAround();
        while (noBeepersPresent()) { //finding a beeper on the one or another side
            move();
        }

        // this loop removes extra beepers and leaves only one central
        if (beepersPresent() && frontIsClear()) { // if beeper found
            move();           // Karel checks if there are any beeper
            if (beepersPresent() && frontIsClear()) { // if another beeper exists, Karel turns back
                turnAround();           // to the first one and removes it
                move();
                pickBeeper();
                turnAround();
                findCenter();
            } else if (frontIsBlocked()) { // if world has width 2, Karel picks one beeper
                pickBeeper();
            }
        }
    }

    // this method allows Karel to turn around
    public void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }
}
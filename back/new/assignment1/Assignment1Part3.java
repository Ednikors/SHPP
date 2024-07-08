package com.shpp.p2p.cs.bkuzhel.assignment1;


/**
 * This class is used to find center of the row
 * */
public class Assignment1Part3 extends SuperKarel {
    public void run() throws Exception {
        fillRow();
        findCenter();
    }

    /**
     * Function is used to remove all extra beepers besides one central
     * */
    private void findCenter() throws Exception {
        while (frontIsClear()) { // go to the opposite side of the world
            move();
        }

        turnAround();

        while (noBeepersPresent()) { //finding a beeper on the one or another side
            move();
        }

        if (beepersPresent() && frontIsClear()) { // if beeper found
            pickBeeper();     // pick beeper up
            move();
            if (noBeepersPresent()) { // Karel checks if there are no any beepers
                turnAround();           // if true, Karel turns around and puts beeper at the center
                move();
                putBeeper();
            } else {                // if false, start this function again
                findCenter();
            }

        }


    }

    /**
     * This function is used to fill a row with beepers
     * */
    public void fillRow() throws Exception {
        while (frontIsClear()) { // while no end of the world
            putBeeper(); // put beeper and go to the next cell
            move();
        }
        putBeeper(); // if Karel in last cell put beeper
    }

}
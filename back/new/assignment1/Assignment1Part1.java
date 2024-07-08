package com.shpp.p2p.cs.bkuzhel.assignment1;

/**
 * This class directs Karel to pick up the newspaper and return to its original position
 * */
public class Assignment1Part1 extends SuperKarel{

    /**
     * The main function which starts all functions needed
     * */
    public void run() throws Exception {
        goToNewspaper();
        pickUpBeeper();
        goBack();
    }

    /**
     * Function which directs Karel to the newspaper
     * */
    private void goToNewspaper() throws Exception {
        goStraight();
        turnRight();
        move();
        turnLeft();
        goStraight();
    }

    /**
     * Function which makes Karel to pick up a newspaper
     */

    private void pickUpBeeper() throws Exception {
        if (beepersPresent()){
            pickBeeper();
        }
    }

    /**
     * Function which directs Karel straight
     * */
    private void goStraight() throws Exception {
        while(frontIsClear()&&noBeepersPresent()){
            move();
        }
    }

    /**
     * Function which directs Karel to the start position
     * */
    private void goBack() throws Exception {
        turnAround();
        goStraight();
        turnRight();
        move();
        turnRight();
    }
}

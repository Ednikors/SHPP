package com.shpp.p2p.cs.bkuzhel.assignment1;

/**
 * This class is used to make Karel build columns on first, last and every 5-th column
 * */
public class Assignment1Part2 extends SuperKarel{

    public void run() throws Exception {
        buildColumns();
    }

    /**
     * Function used to start functions that builds column and directs Karel to next column
     * */
    private void buildColumns() throws Exception {
        while(frontIsClear()){
            turnLeft();
            buildColumn();
            goBack();
            moveToNextColumn();
        }
        turnLeft();
        buildColumn();
    }

    /**
     * Function which moves Karel to the start position of next column
     * */
    private void moveToNextColumn() throws Exception {
        for (int i = 0; i < 4; i++) {
            move();
        }
    }

    /**
     * Function used to build column
     * */
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

    /**
     * Function used to turn Karel around and move him straight
     * */
    private void goBack() throws Exception {
        turnAround();
        while (frontIsClear()){
            move();
        }
        turnLeft();
    }

}

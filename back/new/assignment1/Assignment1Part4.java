package com.shpp.p2p.cs.bkuzhel.assignment1;

/**
 * This class is used to build checker board
 * */
public class Assignment1Part4 extends SuperKarel {
    public void run() throws Exception {
        buildCheckerBoard();
    }

    /**
     * Function is used to start fuctions that fills row in checkers pattern
     * and moves Karel to the next row
     * */
    private void buildCheckerBoard() throws Exception {
        fillCheckerLine();
        checkEastOrWest();
    }

    /**
     * Function is used to move Karel to the next row.
     * If Karel is faced to East, he moves to the left row, if is faced to
     * South, he moves to the right row. Also, before moving the Karel
     * to the next row, the function checks if there is a beeper in the last square,
     * and if so, moves the carl one cell to the side.
     * */
    private void checkEastOrWest() throws Exception {
        if (facingEast() && leftIsClear()) {
            if (beepersPresent()) {
                moveLeft();
                if (leftIsClear()) {
                    moveLeft();
                } else if (frontIsClear()){
                    move();
                }
            } else {
                moveLeft();
                if (leftIsClear()) {
                    turnLeft();
                }
            }
            buildCheckerBoard();
        } else if (facingWest() && rightIsClear()) {
            if (beepersPresent()) {
                moveRight();
                if (rightIsClear()) {
                    moveRight();
                } else if (frontIsClear()){
                    move();
                }
            } else {
                moveRight();
                if (rightIsClear()) {
                    turnRight();
                }
            }
            buildCheckerBoard();
        }

    }

    /**
     * This function turns Karel left and moves him on 1 cell
     * */
    private void moveLeft() throws Exception {
        turnLeft();
        move();
    }

    /**
     * This function turns Karel right and moves him on 1 cell
     * */
    private void moveRight() throws Exception {
        turnRight();
        move();
    }

    /**
     * This method is placing beepers in "checkers" pattern
     * */
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
}
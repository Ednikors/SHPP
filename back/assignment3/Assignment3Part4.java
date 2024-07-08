package com.shpp.p2p.cs.bkuzhel.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

public class Assignment3Part4 extends WindowProgram {

    /* Height of brick */
    private static final int BRICK_HEIGHT = 30;
    /* Width of brick */
    private static final int BRICK_WIDTH = 50;
    /* Amount of bricks in base, also amount of rows*/
    private static final int BRICKS_IN_BASE = 15;

    /* Constant by which the size of the window is increased
     * so that the flag does not go beyond the window */
    private static final int VALUE_OF_INCREASING = 100;

    public void run() {
        setSize(BRICKS_IN_BASE*BRICK_WIDTH+VALUE_OF_INCREASING, BRICKS_IN_BASE*BRICK_HEIGHT+VALUE_OF_INCREASING/2);
        drawPyramid();
    }

    /**
     * Draws pyramid
     * */
    private void drawPyramid() {
        //for amount of rows
        for (int i = 0; i < BRICKS_IN_BASE; i++) {
            //draw pyramid row
            drawPyramidRow(i);
        }
        
    }

    /**
     * Draws piramid row
     *
     * @param bricksAmount Amount of bricks in current row
     * */
    private void drawPyramidRow(int bricksAmount) {
        //for amount of bricks in row
        for (int i = 0; i <= bricksAmount; i++) {
            drawBrick(i, bricksAmount);
        }
    }

    /**
     * Draw one brick
     *
     * @param brickNum Number of current brick
     * @param rowNum Number of current row
     * */
    private void drawBrick(int brickNum, int rowNum) {
        GRect rect = new GRect(calculatePosX(brickNum, rowNum),
                getHeight() + rowNum*BRICK_HEIGHT-BRICKS_IN_BASE*BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
        add(rect);
    }

    /**
     * Calculates x-coordinate for every brick based on data
     * about its number and the row in which it is located
     *
     * @param brickNum Number of current brick
     * @param row Number of current row
     *
     * @return X-coordinate for current brick
     * */
    private int calculatePosX(int brickNum, int row) {
        return getWidth()/2 + brickNum*BRICK_WIDTH - BRICK_WIDTH/2*row- BRICK_WIDTH/2;
    }
}

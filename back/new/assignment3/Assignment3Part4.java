package com.shpp.p2p.cs.bkuzhel.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

/**
 * Class that draws pyramid
 * */
public class Assignment3Part4 extends WindowProgram {
    /**Constant value of brick height*/
    private static final int BRICK_HEIGHT = 30;
    /**Constant value of brick width*/
    private static final int BRICK_WIDTH = 50;
    /**Constant value of amount of bricks in base*/
    private static final int BRICKS_IN_BASE = 15;
    /**Constant value of window increasing*/
    private static final int VALUE_OF_INCREASING = 100;

    /**
     * Main method that starts building of pyramid
     * */
    public void run() {
        setSize(BRICKS_IN_BASE * BRICK_WIDTH + VALUE_OF_INCREASING, BRICKS_IN_BASE * BRICK_HEIGHT + VALUE_OF_INCREASING / 2);
        drawPyramid();
    }

    /**
     * Start function that build one row of pyramid
     * */
    private void drawPyramid() {
        for (int row = 0; row < BRICKS_IN_BASE; row++) {
            drawPyramidRow(row);
        }
    }

    /**
     * Draw pyramid row depends on amount of bricks in row
     * */
    private void drawPyramidRow(int bricksAmount) {
        for (int brickNum = 0; brickNum <= bricksAmount; brickNum++) {
            drawBrick(brickNum, bricksAmount);
        }
    }

    /**
     * Function that draws one brick of pyramid
     * */
    private void drawBrick(int brickNum, int rowNum) {
        GRect rect = new GRect(calculatePosX(brickNum, rowNum),
                getHeight() + rowNum * BRICK_HEIGHT - BRICKS_IN_BASE * BRICK_HEIGHT, BRICK_WIDTH, BRICK_HEIGHT);
        add(rect);
    }

    /**
     * Function that calculates x coordinate for one brick
     * */
    private int calculatePosX(int brickNum, int row) {
        return getWidth() / 2 + brickNum * BRICK_WIDTH - BRICK_WIDTH / 2 * row - BRICK_WIDTH / 2;
    }
}

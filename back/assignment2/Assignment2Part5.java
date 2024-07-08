package com.shpp.p2p.cs.bkuzhel.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 7;
    private static final int NUM_COLS = 15;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    /* Constant by which the size of the window is increased
     * so that the flag does not go beyond the window */
    private static final int VALUE_OF_INCREASING = 100;

    /*The matrix width variable*/
    double matrixWidth;

    /*The matrix height variable*/
    double matrixHeight;

    /*The real window width variable*/
    double realWindowWidth;

    /*The real window height variable*/
    double realWindowHeight;

    public void run() {
        setWindow(); // Configure window settings
        buildMatrix(); // Build matrix

    }

    /**
     * Configure a window settings, and get its real dimensions values
     */
    private void setWindow() {
        // Calculate matrix width using amount of columns
        // size of every box, and size of spacing
        matrixWidth = NUM_COLS * BOX_SIZE + (NUM_COLS - 1) * BOX_SPACING;
        // Calculate matrix height using amount of columns
        // size of every box, and size of spacing
        matrixHeight = NUM_ROWS * BOX_SIZE + (NUM_ROWS - 1) * BOX_SPACING;
        double width = matrixWidth + VALUE_OF_INCREASING;
        double height = matrixHeight + VALUE_OF_INCREASING;
        setSize((int) width, (int) height); // Set window size
        realWindowWidth = getWidth(); // Get real width of window
        realWindowHeight = getHeight(); // Get real height of window
    }

    /**
     * Build a rows of boxes using amount of rows
     */
    private void buildMatrix() {
        for (int i = 0; i < NUM_ROWS; i++) { // for amount of rows
            buildMatrixRow(i); // build row
        }

    }

    /**
     * Build every row of boxes using it amount in one row.
     *
     * @param colNum Current column number
     */
    private void buildMatrixRow(int colNum) {
        for (int i = 0; i < NUM_COLS; i++) {
            GRect rect = new GRect((realWindowWidth - matrixWidth) / 2 + i * (BOX_SIZE + BOX_SPACING),
                    (realWindowHeight - matrixHeight) / 2 + colNum * (BOX_SIZE + BOX_SPACING),
                    BOX_SIZE, BOX_SIZE); // Build every single box with spacing between them
            rect.setFilled(true); // Set filled, and fill color black
            rect.setFillColor(Color.BLACK);
            add(rect);
        }
    }
}

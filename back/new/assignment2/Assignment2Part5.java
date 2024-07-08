package com.shpp.p2p.cs.bkuzhel.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/** Class which draws illusion*/
public class Assignment2Part5 extends WindowProgram {

    /* The number of rows and columns in the grid, respectively. */
    private static final int NUM_ROWS = 12;
    private static final int NUM_COLS = 18;

    /* The width and height of each box. */
    private static final double BOX_SIZE = 40;

    /* The horizontal and vertical spacing between the boxes. */
    private static final double BOX_SPACING = 10;

    /* Constant by which the size of the window is increased
     * so that the flag does not go beyond the window */
    private static final int SPACING = 100;

    public void run() {
        drawIllusionRow();
    }

    /**
     * Function which draws illusion row
     * */
    private void drawIllusionRow() {
        for (int i = 0; i < NUM_ROWS; i++) {
            drawIllusion(i);
        }
    }

    /**
     * Function which draws illusion columns
     *
     * @param row integer value of current row
     * */
    private void drawIllusion(int row) {
        double[] xy = calculateStartPoint();
        for (int i = 0; i < NUM_COLS; i++) {
            GRect rect = new GRect(xy[0]/2+i*(BOX_SIZE+BOX_SPACING), xy[1]/2+ row*(BOX_SIZE+BOX_SPACING),
                    BOX_SIZE, BOX_SIZE);
            rect.setFilled(true);
            add(rect);
        }
    }

    /**
     * Function which calculates start coordinates of illusion
     *
     * @return array of doubles, where id 0 - width, id 1 - height
     * */
    private double[] calculateStartPoint() {
        double [] sizes = new double[2];
        int width = (int) (BOX_SIZE*NUM_COLS + (NUM_COLS - 1)*BOX_SPACING);
        int height = (int) (BOX_SIZE*NUM_ROWS + (NUM_ROWS-1)*BOX_SPACING);
        setSize(width+SPACING, height+SPACING);
        sizes[0] = getWidth() - width;
        sizes[1] = getHeight() - height;
        return sizes;
    }

}

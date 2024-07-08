package com.shpp.p2p.cs.bkuzhel.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part6 extends WindowProgram {

    /* The amount of segments in caterpillar*/
    private static final double SEGMENTS_AMOUNT = 12;

    /* The diameter of every segment*/
    private static final double OVAL_DIAMETER = 100;

    /* Constant by which the size of the window is increased
     * so that the flag does not go beyond the window */
    private static final int VALUE_OF_INCREASING = 100;

    /* Displacement coefficient of the next segment relative
     * to the previous one along the x-axis*/
    private static final double X_AXIS_DISPLACEMENT_COEFFICIENT = 0.6;

    public void run() {
        setSize((int) (VALUE_OF_INCREASING + SEGMENTS_AMOUNT * OVAL_DIAMETER * X_AXIS_DISPLACEMENT_COEFFICIENT),
                (int) (VALUE_OF_INCREASING + OVAL_DIAMETER + OVAL_DIAMETER / 2));
        buildCaterpillar();
    }


    /**
     * Build caterpillar where each subsequent segment is shifted up or down
     */
    private void buildCaterpillar() {
        boolean buildUp = false; // Shift up or down
        double posX = 0; // Start x position
        double posY = 25; // Start y position
        // for each segment check if it is shifted up or down
        //and build segment
        for (int i = 0; i < SEGMENTS_AMOUNT; i++) {
            if (buildUp) { // If segment was shifted up
                posY -= OVAL_DIAMETER / 2; // Then shift it down
            } else { // If segment was shifted down
                posY += OVAL_DIAMETER / 2; // Then shift it up
            }
            // Draw segment
            drawOneSegment(posX + i * OVAL_DIAMETER * X_AXIS_DISPLACEMENT_COEFFICIENT, posY);
            buildUp = !buildUp;
        }
    }

    /**
     * Build every single segment of caterpillar.
     *
     * @param posX The x coordinate of the upper-left corner of the segment
     * @param posY The y coordinate of the upper-left corner of the segment
     */
    private void drawOneSegment(double posX, double posY) {
        GOval oval = new GOval(posX, posY, OVAL_DIAMETER, OVAL_DIAMETER); // Draw segment
        oval.setFilled(true); // Set filled, and fill color is green
        oval.setFillColor(Color.GREEN);
        oval.setColor(Color.RED); // Set color of segment borders red
        add(oval);
    }
}

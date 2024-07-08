package com.shpp.p2p.cs.bkuzhel.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends WindowProgram {
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 300;

    /*The coefficient of the ratio of the circle diameter
     * to the smallest side of the window
     */
    public static final int CIRCLE_DIAMETER_RATIO = 3;

    public void run() {
        double circleDiameter = calculateDiameter();
        drawCircles(circleDiameter);
        drawRectangle(circleDiameter);
    }

    /**
     * Returns the diameter of circle using the real window size.
     * First this function found which dimension is smaller, then
     * using it size calculate circle diameter value
     *
     * @return Circle diameter value
     */
    private double calculateDiameter() {
        // Checks which of the sizes is smaller
        double smallerSide = Math.min(getHeight(), getWidth());
        return smallerSide / CIRCLE_DIAMETER_RATIO;
    }


    /**
     * Draw 4 circles using value of its diameter
     *
     * @param diameter Circle diameter
     */
    public void drawCircles(double diameter) {
        for (int i = 0; i < 2; i++) {
            drawCirclesRow(i, diameter); // draw 2 rows of circle
        }
    }

    /**
     * Draw row of 2 circles on the both corners of the window.
     * All circles are drawn in black.
     *
     * @param circlesRowPos Current row of circles, top or bottom
     * @param diameter      Circle diameter value
     */
    private void drawCirclesRow(int circlesRowPos, double diameter) {
        for (int i = 0; i < 2; i++) {   // For every corner, left of right
            GOval oval = new GOval(i * (getWidth() - diameter),
                    circlesRowPos * (getHeight() - diameter),
                    diameter, diameter); // Draw circle
            oval.setFilled(true); // Set filled, and fill color black
            oval.setFillColor(Color.BLACK);
            oval.setColor(Color.WHITE); // Set circle borders white
            add(oval);
        }
    }

    /**
     * Using value of circlesDiameter draws rectangle whose
     * corners are in the centers of the circles.
     *
     * @param circleDiameter Circle diameter value
     */
    private void drawRectangle(double circleDiameter) {
        GRect rect = new GRect(circleDiameter / 2, circleDiameter / 2,
                getWidth() - circleDiameter, getHeight() - circleDiameter); // Draw rectangle
        rect.setFilled(true); // Set filled, and fill color black
        rect.setFillColor(Color.WHITE);
        rect.setColor(Color.WHITE); // Set rectangle borders white
        add(rect);
    }
}

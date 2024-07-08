package com.shpp.p2p.cs.bkuzhel.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/** Class which draws illusory contours */
public class Assignment2Part2 extends WindowProgram {

    /** Size parameters of window */
    public static final int APPLICATION_WIDTH = 900;
    public static final int APPLICATION_HEIGHT = 600;

    /** Circle diameter to the lowest side of window ratio*/
    private static final int CIRCLE_DIAMETER_TO_SIDE_RATIO = 3;

    /**
     * Main function which first calculates diameter of the circle
     * then run function which draws circles and rectangle which are parts of
     * illusory contour.
     * */
    public void run() {
        double diameter = getCircleDiameter();
        drawCirclesMatrix(diameter);
        drawRectangle(diameter);
    }

    /**
     * Function which draws rectangle over circles.
     *
     * @param diameter double value of circle diameter
     * */
    private void drawRectangle(double diameter) {
        GRect rect = new GRect(diameter/2, diameter/2, getWidth()-diameter, getHeight()-diameter);
        rect.setFilled(true);
        rect.setColor(Color.WHITE);
        add(rect);
    }

    /**
     * Function which run function which draw two rows of circles.
     *
     * @param diameter double value of circle diameter
     * */
    private void drawCirclesMatrix(double diameter) {
        for (int i = 0; i < 2; i++) {
            drawCircles(i, diameter);
        }
    }

    /**
     * Function to draw two circles using another function
     * whick draws one circle
     *
     * @param column integer value of current column
     * @param diameter double value of circle diameter
     * */
    private void drawCircles(int column, double diameter) {
        for (int i = 0; i < 2; i++) {
            drawCircle(column, i, diameter);
        }
    }

    /**
     * Function which calculates circle diameter by the smallest side
     *
     * @return double value of circle diameter
     * */
    private double getCircleDiameter() {
        return (double) Math.min(getHeight(), getWidth()) /CIRCLE_DIAMETER_TO_SIDE_RATIO;
    }

    /**
     * Function which draws one circle
     *
     * @param column integer value of current column
     * @param row integer value of current row
     * @param diameter double value of circle diameter
     * */
    private void drawCircle(int column, int row, double diameter) {
        GOval oval = new GOval(column*(getWidth()-diameter), row*(getHeight()-diameter),
                diameter, diameter);
        oval.setFilled(true);
        oval.setFillColor(Color.BLACK);
        oval.setColor(Color.WHITE);
        add(oval);
    }
}

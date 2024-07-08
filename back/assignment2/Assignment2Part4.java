package com.shpp.p2p.cs.bkuzhel.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {
    /* The height of the flag */
    private static final double FLAG_HEIGHT = 200;

    /* The width of the flag */
    private static final double FLAG_WIDTH = 300;

    /* Constant by which the size of the window is increased
     * so that the flag does not go beyond the window */
    private static final int VALUE_OF_INCREASING = 100;

    /* Amount of the lines in the flag. Is used to calculate
     * the correct position of the line and its height*/
    private static final int AMOUNT_OF_LINES_IN_FLAG = 3;

    /* Font of the label and its size*/
    private static final String LABEL_FONT = "Verdana-18";

    /* The top color of the Netherlands flag */
    private static final Color BRIGHT_VERMILION = new Color(173, 29, 37);

    /* The bottom color of the Netherlands flag*/
    private static final Color COBALT_BLUE = new Color(30, 71, 133);

    /* The application window width */
    double applicationWidth = FLAG_WIDTH + VALUE_OF_INCREASING;

    /* The application window height */
    double applicationHeight = FLAG_HEIGHT + VALUE_OF_INCREASING;

    /* Array with frag colors */
    private static final Color[] colourList = {BRIGHT_VERMILION, Color.WHITE, COBALT_BLUE};

    public void run() {
        setSize((int) (applicationWidth), (int) (applicationHeight));
        setBackground(Color.GRAY); // Set background color gray to better distinguish the flag,
        // since it contains white in the middle
        drawFlag();
        printLabel();
    }

    /**
     * Draw an every line of flag
     */
    private void drawFlag() {
        for (int i = 0; i < 3; i++) {
            drawLine(i);
        }
    }

    /**
     * Draw a line of flag using its id to draw line in the appropriate position
     * and fill it with the appropriate color.
     * It calculates 2 scale coefficient which shift the coordinates by their value
     * because the real size of the window
     * differs from the given one.
     *
     * @param lineId Id of the line is drawing
     */
    private void drawLine(int lineId) {
        double widthScaleCoeff = (double) getWidth() / (applicationWidth); // Scale coefficient for x-dimension
        double heightScaleCoeff = (double) getHeight() / (applicationHeight); // Scale coefficient for y-dimension
        double startPosX = (getWidth() - FLAG_WIDTH) / 2; // The x coordinate of the upper-left corner of line
        double startPosY = (getHeight() - FLAG_HEIGHT) / 2; // The y  coordinate of the upper-left corner of line
        GRect rect = new GRect(startPosX * widthScaleCoeff,
                (startPosY * heightScaleCoeff + FLAG_HEIGHT / AMOUNT_OF_LINES_IN_FLAG * lineId),
                FLAG_WIDTH, FLAG_HEIGHT / AMOUNT_OF_LINES_IN_FLAG); // Draw line
        rect.setFilled(true); // Set filled, and fill color depends on line id
        rect.setFillColor(colourList[lineId]);
        rect.setColor(colourList[lineId]);
        add(rect);
    }

    /**
     * Prints a label with the name of the country whose flag is drawn
     */
    private void printLabel() {
        GLabel label = new GLabel("Flag of Netherlands");
        label.setFont(LABEL_FONT);
        label.setLocation(getWidth() - label.getWidth(), getHeight() - label.getDescent());
        add(label);
    }


}

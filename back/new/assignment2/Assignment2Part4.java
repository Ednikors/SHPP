package com.shpp.p2p.cs.bkuzhel.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part4 extends WindowProgram {
    /* The height of the flag */
    private static final double FLAG_HEIGHT = 300;

    /* The width of the flag */
    private static final double FLAG_WIDTH = 450;

    /* Constant by which the size of the window is increased
     * so that the flag does not go beyond the window */
    private static final int VALUE_OF_INCREASING = 100;

    /* Amount of the lines in the flag. Is used to calculate
     * the correct position of the line and its height*/
    private static final int AMOUNT_OF_LINES_IN_FLAG = 3;

    /* Font of the label and its size*/
    private static final String LABEL_FONT = "Verdana-18";

    /* Colors of Italy flag */
    private static final Color FERN_GREEN = new Color(0, 140, 69);

    private static final Color BRIGHT_WHITE = new Color(244, 245, 240);

    private static final Color FLAME_SCARLET = new Color(205, 33, 42);

    /* The application window width */
    double applicationWidth = FLAG_WIDTH + VALUE_OF_INCREASING;

    /* The application window height */
    double applicationHeight = FLAG_HEIGHT + VALUE_OF_INCREASING;

    /* Array with frag colors */
    private static final Color[] colourList = {FERN_GREEN, BRIGHT_WHITE, FLAME_SCARLET};

    public void run() {
        setSize((int) (applicationWidth), (int) (applicationHeight));
        setBackground(Color.GRAY);
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
     * because the real size of the window differs from the given one.
     *
     * @param lineId integer value of an id of the line is drawing
     */
    private void drawLine(int lineId) {
        double widthScaleCoeff = (double) getWidth() / (applicationWidth);
        double heightScaleCoeff = (double) getHeight() / (applicationHeight);
        double startPosX = (getWidth() - FLAG_WIDTH) / 2;
        double startPosY = (getHeight() - FLAG_HEIGHT) / 2;
        GRect rect = new GRect(startPosX * widthScaleCoeff+ FLAG_WIDTH / AMOUNT_OF_LINES_IN_FLAG * lineId,
                (startPosY * heightScaleCoeff ),
                FLAG_WIDTH/ AMOUNT_OF_LINES_IN_FLAG, FLAG_HEIGHT );
        rect.setFilled(true);
        rect.setFillColor(colourList[lineId]);
        rect.setColor(colourList[lineId]);
        add(rect);
    }

    /**
     * Prints a label with the name of the country whose flag is drawn
     */
    private void printLabel() {
        GLabel label = new GLabel("Flag of Italy");
        label.setFont(LABEL_FONT);
        label.setLocation(getWidth() - label.getWidth(), getHeight() - label.getDescent());
        add(label);
    }


}

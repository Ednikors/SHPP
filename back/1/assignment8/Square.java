package com.shpp.p2p.cs.bkuzhel.assignment8;

import acm.graphics.GRect;

import java.awt.*;

/**
 * This class represents a single square object on the chessboard.
 */
public class Square{

    /**
     * Global value for GRect object.
     */
    private GRect rect;

    /**
     * Global values of integer datatype for x and y coordinates
     * of square, its size and animation direction
     */
    private int x, y, size, animationDirection;

    /**
     * Global value of square color
     */
    private Color color;

    /**
     * Global boolean value. Used to check whether a given square should be animated or not
     */
    private boolean isAnimated;

    /**
     * Constructor for class Square. Creates every object of this class with
     * given parameters
     */
    public Square(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.size = size;
        this.isAnimated = true;
        this.animationDirection = -1;

        rect = new GRect(x, y, size, size);
        rect.setFilled(true);
        rect.setColor(color);
    }

    /**
     * Getter, which returns GRect object for Square object
     */
    public GRect getGRect() {
        return rect;
    }

    /**
     * Getter, which returns x-coordinate of Square object
     */
    public int getX() {
        return x;
    }

    /**
     * Getter, which returns y-coordinate of Square object
     */
    public int getY() {
        return y;
    }

    /**
     * Getter, which returns color of Square object
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter, which returns size of Square object
     */
    public int getSize() {
        return size;
    }

    /**
     * Getter, which returns boolean value if Square object should be animated or not
     */
    public boolean isAnimated() {
        return isAnimated;
    }

    /**
     * Method used to turn on or off animation for Square object
     */
    public void toggleAnimation() {
        isAnimated = !isAnimated;
    }

    /**
     * Method which animates Square object.
     * First, it calculates coordinates of the center for Square object.
     * Then, decrease or increase object depends on current size and change its location.
     * In the end, method checks if Square object isn`t smaller than minimum size of bigger than
     * maximum size. If so, changes animation direction to reverse
     *
     * @param maximumSize maximum permissible size of Square object
     * @param minimumSize minimum permissible size of Square object
     */
    public void animate(int maximumSize, int minimumSize) {
        if (isAnimated) {
            double centerX = rect.getX() + rect.getWidth() / 2;
            double centerY = rect.getY() + rect.getHeight() / 2;

            rect.setSize(rect.getWidth() + animationDirection, rect.getHeight() + animationDirection);
            rect.setLocation(centerX - rect.getWidth() / 2, centerY - rect.getHeight() / 2);

            if (rect.getWidth() == maximumSize || rect.getWidth() == minimumSize) {
                animationDirection *= -1;
            }
        }
    }
}

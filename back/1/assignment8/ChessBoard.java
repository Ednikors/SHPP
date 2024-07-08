package com.shpp.p2p.cs.bkuzhel.assignment8;

import com.shpp.cs.a.graphics.WindowProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * A class which implements a chessboard with animated
 * squares
 */
public class ChessBoard extends WindowProgram {

    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 600;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Array list of all squares in chessboard
     */
    private ArrayList<Square> squares;

    /**
     * Radius with center in cursor forms
     * an area in which squares will be animated
     */
    private static final int ANIMATION_RADIUS = 50;

    /**
     * Size of each square
     */
    private static final int SQUARE_SIZE = 20;

    /**
     * Minimum square size. Used for animation
     */
    private static final int MINIMUM_SQUARE_SIZE = 10;

    /**
     * Screen refresh rate(30 FPS). Affects the animation speed
     */
    private static final double PAUSE_TIME = 1000.0 / 30;

    /**
     * Global variables to store the current cursor coordinates
     */
    int cursorX, cursorY;

    /**
     * the initial method, which is triggered when the program is started
     * and is responsible for starting the methods
     * that are responsible for the program's operability
     */
    public void run() {
        drawChessBoard();
        addMouseListeners();
        Timer timer = new Timer((int) PAUSE_TIME, e -> animateSquares());
        timer.start();
    }

    /**
     * checks which squares are in the area with the cursor and starts their animation
     */
    private void animateSquares() {
        for (Square square : squares) {
            int x = square.getX() + square.getSize() / 2;
            int y = square.getY() + square.getSize() / 2;

            if (distance(cursorX, cursorY, x, y) < ANIMATION_RADIUS && square.getColor() == Color.BLACK) {
                square.animate(SQUARE_SIZE, MINIMUM_SQUARE_SIZE);
            }
        }
    }

    /**
     * Method used to refresh current cursor coordinates
     */
    public void mouseMoved(MouseEvent e) {
        cursorX = e.getX();
        cursorY = e.getY();
    }


    /**
     * Checks which of the squares was clicked and either turns the animation on
     * or off for it, depending on the initial state of the square before the click
     */
    public void mouseClicked(MouseEvent e) {
        for (Square square : squares) {
            if (square.getGRect().contains(e.getX(), e.getY())) {
                square.toggleAnimation();
            }
        }
    }

    /**
     * Method used to draw chessboard.
     * First, initializes an ArrayList of squares.
     * For each column and row, it creates the required number of squares that they can fit.
     * When creating checks whether the current square should be black or white
     * then draws it and add to ArrayList of squares
     */
    private void drawChessBoard() {
        squares = new ArrayList<>();
        for (int i = 0; i < getHeight() / SQUARE_SIZE; i++) {
            for (int j = 0; j < getWidth() / SQUARE_SIZE; j++) {
                boolean isBlack = (i + j) % 2 == 0;
                Square square = new Square(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE,
                        isBlack ? Color.BLACK : Color.WHITE);
                add(square.getGRect());
                squares.add(square);
            }
        }
    }

    /**
     * Method used to calculate distance between two points
     * @param x1 x-coordinate of first point
     * @param y1 y-coordinate of first point
     * @param x2 x-coordinate of second point
     * @param y2 y-coordinate of second point
     *
     * @return distance between two given points
     */
    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
}

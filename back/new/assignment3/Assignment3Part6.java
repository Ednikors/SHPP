package com.shpp.p2p.cs.bkuzhel.assignment3;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
/**
 * Class that implements animation of a heart beat
 * */
public class Assignment3Part6 extends WindowProgram {
    // Constant of window size
    private static final int WINDOW_SIZE = 600;
    // Constant of heart max size
    private static final int HEART_SIZE_MAX = 600;
    // Constant of heart min size
    private static final int HEART_SIZE_MIN = 450;
    // Constant of heart beat speed
    private static final double HEART_BEAT_SPEED = 1.8;
    // Constant of window increasing
    private static final int VALUE_OF_INCREASING = 150;

    // Constant of points in heart polygon
    private static final int NUM_POINTS = 150;
    // FPS 60
    private static final double PAUSE_TIME = 1000.0 / 60;

    /**
     * Main function that sets size of window, creates heart and animates it
     * */
    public void run() {
        setSize(WINDOW_SIZE + VALUE_OF_INCREASING, WINDOW_SIZE + VALUE_OF_INCREASING);
        GPolygon heart = createHeart();
        add(heart);
        animateHeartbeat(heart);
    }

    /**
     * Function that creates heart polygon.
     * @return GPolygon heart
     * */
    private GPolygon createHeart() {
        GPolygon heart = new GPolygon(getHeartPoints());
        updateHeartSize(heart, HEART_SIZE_MAX);
        heart.setLocation((double) getWidth() / 2, (double) getHeight() / 2);
        heart.setFilled(true);
        heart.setColor(Color.red);
        return heart;
    }

    /**
     * Function that updates size of heart
     * @param heart GPolygon heart object
     * @param size Double value of heart new size
     * */
    private void updateHeartSize(GPolygon heart, double size) {
        heart.scale(size / heart.getWidth());
    }

    /**
     * Function that calculates points of heart.
     * @return array of points
     * */
    private GPoint[] getHeartPoints() {
        GPoint[] pnts = new GPoint[NUM_POINTS];
        double dt = (2 * Math.PI - 0) / NUM_POINTS;
        for (int i = 0; i < NUM_POINTS; i++) {
            pnts[i] = new GPoint(calculatePosX(i * dt), calculatePosY(i * dt));
        }
        return pnts;
    }

    /**
     * Function to calculate y coordinate of heart point in current step
     * @param step current position in polar coordinates
     * @return y coordinate of one point
     * */
    private double calculatePosY(double step) {
        return -(13 * Math.cos(step) - 5 * Math.cos(2 * step) - 2 * Math.cos(3 * step) - Math.cos(4 * step));
    }

    /**
     * Function to calculate x coordinate of heart point in current step
     * @param step current position in polar coordinates
     * @return x coordinate of one point
     * */
    private double calculatePosX(double step) {
        return 16 * Math.pow(Math.sin(step), 3);
    }

    /**
     * Function that animates heart beat
     * @param heart GPolygon object of heart
     * */
    private void animateHeartbeat(GPolygon heart) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 5000) {
            for (double size = HEART_SIZE_MAX; size >= HEART_SIZE_MIN; size -= HEART_BEAT_SPEED) {
                if (System.currentTimeMillis() - startTime > 5000) {
                    break;
                }
                updateHeartSize(heart, size);
                pause(PAUSE_TIME);
            }
            for (double size = HEART_SIZE_MIN; size <= HEART_SIZE_MAX; size += HEART_BEAT_SPEED) {
                if (System.currentTimeMillis() - startTime > 5000) {
                    break;
                }
                updateHeartSize(heart, size);
                pause(PAUSE_TIME);
            }
        }
        print("Animation time: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}

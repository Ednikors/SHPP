package com.shpp.p2p.cs.bkuzhel.assignment3;

import acm.graphics.*;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;



public class Assignment3Part6 extends WindowProgram {
    /*The amount of time to pause between frames(60fps)*/
    private static final double PAUSE_TIME = 1000.0 / 60;

    /*Number of points in heart. The more point the more detailed heart will be built*/
    private static final int NUM_POINTS = 150;

    /* MAX and MIN heart size. Used in animation*/
    private static final int HEART_SIZE_MAX = 600;
    private static final int HEART_SIZE_MIN = 450;

    /*The speed with which the heart beats. The bigger value the faster beats*/
    private static final double HEART_BEAT_SPEED = 1.8;

    /* Constant by which the size of the window is increased
     * so that the flag does not go beyond the window */
    private static final int VALUE_OF_INCREASING = 150;

    public void run() {
        setSize(HEART_SIZE_MAX + VALUE_OF_INCREASING, HEART_SIZE_MAX + VALUE_OF_INCREASING);
        GPolygon heart = drawHeart();
        add(heart);
        animHeartBeat(heart);
    }

    /**
     * Create heart-shaped polygon
     *
     * @return GPolygon object
     * */
    private GPolygon drawHeart() {
        //create polygon and calculates points for it
        GPolygon heart = new GPolygon(getPoints());
        updateHeartSize(heart, HEART_SIZE_MAX);
        heart.setLocation((double) getWidth() / 2, (double) getHeight() / 2);
        heart.setFilled(true);
        heart.setColor(Color.red);
        return heart;
    }

    /**
     * Updates polygon size to a given size
     *
     * @param heart GPolygon object
     * @param size Size of object to be updated
     * */
    private void updateHeartSize(GPolygon heart, double size) {
        heart.scale(size / heart.getWidth());
    }

    /**
     * Calculates coordinates of each point of heart
     *
     * @return Array of points
     * */
    private GPoint[] getPoints() {
        GPoint[] pnts = new GPoint[NUM_POINTS];
        //delta-step by which the position of the points changes
        double dt = (2 * Math.PI - 0) / NUM_POINTS;
        for (int i = 0; i < NUM_POINTS; i++) {
            pnts[i] = new GPoint(calculatePosX(i * dt), calculatePosY(i * dt));
        }
        return pnts;
    }

    /**
     * Calculate y-coordinate for each point depends on delta-step
     *
     * @param step Delta-step for each point
     *
     * @return y-coordinate of point
     * */
    private double calculatePosY(double step) {
        return -(13 * Math.cos(step) - 5 * Math.cos(2 * step) -
                2 * Math.cos(3 * step) - Math.cos(4 * step));
    }

    /**
     * Calculate x-coordinate for each point depends on delta-step
     *
     * @param step Delta-step for each point
     *
     * @return x-coordinate of point
     * */
    private double calculatePosX(double step) {
        return 16 * Math.pow(Math.sin(step), 3);
    }

    /**
     * Animation of beat of heart. Has a handle that the animation
     * cannot last longer than 5 seconds
     *
     * @param heart GPolygon object to be animated
     * */
    private void animHeartBeat(GPolygon heart) {
        long startTime = System.currentTimeMillis();
        //start of animation
        while (System.currentTimeMillis() - startTime < 5000) {  //check if animation doesn't go longer than 5sec
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

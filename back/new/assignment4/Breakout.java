package com.shpp.p2p.cs.bkuzhel.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Class which implements Breakout game
 * */
public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * The amount of time to pause between frames(100fps)
     */
    private static final double PAUSE_TIME = 1000.0 / 120;

    /**
     * The array of 5 colours for first 10 rows of bricks
     */
    private static final Color[] colorList = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};

    /**
     * Number of turns
     */
    private static final int NTURNS = 3;


    /**
     * "Global" values
     */
    private GObject paddle;
    private int brickAmount = NBRICK_ROWS * NBRICKS_PER_ROW;
    private final GLabel endGameLabel = new GLabel("");

    public void run() {
        GObject ball = addBall();
        paddle = addPaddle();
        addMouseListeners();
        drawBrickRows();
        // the player has three attempts to win the game
        for (int i = 0; i < NTURNS; i++) {
            if (brickAmount > 0) {
                waitForClick();
                remove(endGameLabel);
                gameProcess(i, ball); // start one attempt
            }
        }
        //if no attempts prints that player lost the game
        //if there are attempt prints that player win the game
        remove(endGameLabel);
        printInfo(brickAmount > 0 ? "You lost :(" : "You win :)");

    }

    /**
     * Method that prints information about game
     *
     * @param s String to print
     */
    private void printInfo(String s) {
        endGameLabel.setLabel(s);
        endGameLabel.setLocation((double) getWidth() / 2 - endGameLabel.getWidth() / 2,
                (double) getHeight() / 2);
        add(endGameLabel);
    }

    /**
     * Creates ball
     *
     * @return Ball object
     */
    private GObject addBall() {
        GOval ball = new GOval((double) getWidth() / 2 - BALL_RADIUS, (double) getHeight() / 2 - BALL_RADIUS,
                BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        add(ball);
        return ball;
    }

    /**
     * Creates paddle
     *
     * @return Paddle object
     */
    private GObject addPaddle() {
        GRect paddle = new GRect((double) (getWidth() - PADDLE_WIDTH) / 2,
                getHeight() - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle);
        add(paddle);
        return paddle;
    }

    /**
     * Method which move a paddle to the current position of mouse inside the window
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        //the main tracking
        if (mouseEvent.getX() - PADDLE_WIDTH / 2 >= 0 && mouseEvent.getX() + PADDLE_WIDTH / 2 <= getWidth()) {
            paddle.setLocation(mouseEvent.getX() - (double) PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_OFFSET);
        } else {
            //if you move the mouse very fast and it goes off the screen,
            // the paddle may not move, these loops solve this problem
            if (mouseEvent.getX() + PADDLE_WIDTH > getWidth()) {
                paddle.setLocation(getWidth() - PADDLE_WIDTH, getHeight() - PADDLE_Y_OFFSET);
            } else {
                paddle.setLocation(0, getHeight() - PADDLE_Y_OFFSET);
            }
        }
    }

    /**
     * The main process of one attempt
     *
     * @param roundNum Number of current attempt
     * @param ball     Ball that destroys bricks
     */
    private void gameProcess(int roundNum, GObject ball) {
        // place the ball
        ball.setLocation((double) getWidth() / 2 - BALL_RADIUS, (double) getHeight() / 2 - BALL_RADIUS);
        //start movement vectors of the ball
        RandomGenerator rgen = RandomGenerator.getInstance();
        double vx = rgen.nextDouble(1.0, 3.0);
        double vy = 3;
        //with chance of 50% reverse vector x
        if (rgen.nextBoolean(0.5))
            vx = -vx;
        //the main loop, goes till all bricks won't be destroyed
        while (brickAmount > 0) {
            ball.move(vx, vy); // move the ball
            //check if ball touch right or left wall
            if (ball.getX() + BALL_RADIUS * 2 >= getWidth() || ball.getX() <= 0) {
                vx = -vx; // if touch then reverse vector
            }
            //if ball touch the ceiling
            if (ball.getY() <= 0) {
                vy = -vy; // if touch then reverse vector
            }
            //if ball touch the bottom
            if (ball.getY() + BALL_RADIUS * 2 >= getHeight()) {
                //print information that ball touched the ground
                printInfo("Ooops... The ball touched the ground");
                //break current attempt
                break;
            }
            //check if in current ball position it collides with anyone object
            GObject collidingObject = getCollidingObject(ball);
            //if collided with paddle or data bar
            if (collidingObject == paddle) {
                //if ball touch paddle with bottom dots
                if (((int) (ball.getY() + ball.getHeight()) <= paddle.getY() + paddle.getHeight() / 4)) {
                    vy = -vy; // reverse vector
                }
            }
            // if ball collides with brick
            else if (collidingObject != null) {
                //destroy this brick
                remove(collidingObject);
                //decrease counter
                brickAmount--;
                //update information in data bar
                vy = -vy; // reverse vector
            }

            pause(PAUSE_TIME);
        }

    }

    /**
     * Method that checks if ball collided with any other object
     *
     * @param ball Ball that destroys bricks
     * @return Returns object if ball collided with it, or null if not collided
     */
    private GObject getCollidingObject(GObject ball) {
        //get current position of 4 dots of the bal
        GPoint[] points = calculatePoints(ball);
        //for each point check if it collided with any object
        for (GPoint point : points) {
            GObject collidedObject = getElementAt(point);
            //if collided than return object
            if (collidedObject != null) {
                return collidedObject;
            }
        }
        //if not collided, return null
        return null;
    }

    /**
     * Calculates current position of 4 dots of the ball with the help of which,
     * checks whether the ball hit the object or not
     *
     * @param ball Ball that destroys bricks
     * @return Array of current position of 4 dots
     */
    private GPoint[] calculatePoints(GObject ball) {
        double x = ball.getX();
        double y = ball.getY();
        GPoint[] points = new GPoint[4];
        points[0] = new GPoint(x, y);
        points[1] = new GPoint(x + 2 * BALL_RADIUS, y);
        points[2] = new GPoint(x, y + 2 * BALL_RADIUS);
        points[3] = new GPoint(x + 2 * BALL_RADIUS, y + 2 * BALL_RADIUS);
        return points;
    }

    /**
     * Draw brick matrix
     */
    private void drawBrickRows() {
        // calculate width of each brick
        double brickWidth = (double) (getWidth() - (NBRICKS_PER_ROW) * BRICK_SEP) / NBRICKS_PER_ROW;
        //init variable
        Color color = Color.WHITE;
        //get color for eack row and build it
        for (int i = 0; i < NBRICK_ROWS; i++) {
            color = getColorByRow(i, color);
            drawBrickRow(i, brickWidth, color);
        }
    }

    /**
     * Get color for each row. For 10 standard rows returns color
     * from array of colors. If rows are more than 10, returns random color
     *
     * @return Color for current row
     */
    private Color getColorByRow(int rowN, Color prewColor) {
        Color color;
        RandomGenerator generator = new RandomGenerator();
        //if current row number is less than 10
        if (rowN < colorList.length * 2) {
            color = colorList[rowN / 2];
        } else {
            //if more than 10
            color = rowN % 2 == 0 ? generator.nextColor() : prewColor;
        }
        return color;
    }

    /**
     * Draw one row of bricks
     */
    private void drawBrickRow(int rowN, double brickWidth, Color color) {
        for (int i = 0; i < NBRICKS_PER_ROW; i++) {
            drawBrick(rowN, i, brickWidth, color);
        }
    }

    /**
     * Draw one brick and fill it with color
     */
    private void drawBrick(int rowN, int brickN, double brickWidth, Color color) {
        GRect brick = new GRect((double) BRICK_SEP / 2 + brickN * (brickWidth + BRICK_SEP),
                BRICK_Y_OFFSET + rowN * (BRICK_HEIGHT + BRICK_SEP),
                brickWidth, BRICK_HEIGHT);
        brick.setFilled(true);
        brick.setColor(color);
        add(brick);
    }


}
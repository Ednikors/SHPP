package com.shpp.p2p.cs.bkuzhel.assignment1;

import com.shpp.karel.KarelTheRobot;

public class Assignment1Part1 extends KarelTheRobot {
    public void run() throws Exception {
        goToNewspaper();
        pickUpNewspaper();
        goBack();
    }

    // this method sends Karel to the newspaper
    public void goToNewspaper() throws Exception {
        turnRight(); //this code rotates the carl so that he is facing the front of the door
        move();
        turnLeft();
        while (frontIsClear() && noBeepersPresent()) { // while no wall and no beeper ahead
            move(); //go to beeper
        }
    }

    // using this method Karel can pick up beeper
    public void pickUpNewspaper() throws Exception{
        pickBeeper();
    }

    // when beeper picked up, Karel must go back using this method
    public void goBack() throws Exception{
        turnAround();
        while (frontIsClear()){ // Karel go inside his house, till he hit the wall
            move();
        }
        turnRight(); // Karel turns and go to his start position
        move();
        turnRight();
    }

    // this method allows Karel to turn right
    public void turnRight() throws Exception{
        turnLeft();
        turnLeft();
        turnLeft();
    }

    // this method allows Karel to turn around
    public void turnAround() throws Exception{
        turnLeft();
        turnLeft();
    }

}

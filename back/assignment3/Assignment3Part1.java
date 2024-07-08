package com.shpp.p2p.cs.bkuzhel.assignment3;

import com.shpp.cs.a.console.TextProgram;


public class Assignment3Part1 extends TextProgram {

    /*Constants of recommended days of exercises for cardiovascular and blood pressure*/
    private static final int RECOMMENDED_DAYS_FOR_CARDIOVASCULAR = 5;
    private static final int RECOMMENDED_DAYS_FOR_BLOOD_PRESSURE = 3;
    /*Constants of recommended time of exercises for cardiovascular and blood pressure*/
    private static final int RECOMMENDED_TIME_FOR_CARDIOVASCULAR = 30;
    private static final int RECOMMENDED_TIME_FOR_BLOOD_PRESSURE = 40;

    /*Counters for days in which user do enough exercises*/
    int cardiovascularCounter = 0;
    int bloodPressureCounter = 0;

    public void run() {
        /*Ask user about his\her exercise time on every day*/
        askUser();
        /*Makes report about user's exercises*/
        getAerobicsReport();
    }


    /**
     * Asks user information about how much time he\her
     * exercise every day
     */
    private void askUser() {
        //For every day
        for (int i = 1; i <= 7; i++) {
            //ask user how much minutes he\she exercises
            print("How many minutes did you do on day " + i + "? ");
            int minutes = readInt();
            //Time of exercise can't be less than 0
            while (minutes < 0) {
                //ask again
                print("Minutes of training cannot be negative. Try again: ");
                minutes = readInt();
            }
            //add counters
            addCounters(minutes);
        }
    }

    /**
     * Increase counter if exercise time is enough.
     *
     * @param minutes Time of training
     */
    private void addCounters(int minutes) {
        cardiovascularCounter += (minutes >= RECOMMENDED_TIME_FOR_CARDIOVASCULAR ? 1 : 0);
        bloodPressureCounter += (minutes >= RECOMMENDED_TIME_FOR_BLOOD_PRESSURE ? 1 : 0);
    }

    /**
     * Makes report on the user's training during the week
     */
    private void getAerobicsReport() {
        // Check if user trained enough days for cardiovascular.
        // If yes sends 0, if not sends amount of days needed to train more into
        // method printResults()
        printResults("\nCardiovascular health",
                (cardiovascularCounter >= RECOMMENDED_DAYS_FOR_CARDIOVASCULAR ?
                        0 : RECOMMENDED_DAYS_FOR_CARDIOVASCULAR - cardiovascularCounter));
        // Check if user trained enough days for blood pressure.
        // If yes sends 0, if not sends amount of days needed to train more into
        // method printResults()
        printResults("Blood pressure",
                (bloodPressureCounter >= RECOMMENDED_DAYS_FOR_BLOOD_PRESSURE ?
                        0 : RECOMMENDED_DAYS_FOR_BLOOD_PRESSURE - bloodPressureCounter));
    }


    /**
     * Displays training information on the screen.
     *
     * @param exerciseType Type of exercise(Cardiovascular or for blood pressure)
     * @param daysNeeded   Days needed to train as much as recommended, or 0 if trained enough
     */
    private void printResults(String exerciseType, int daysNeeded) {
        String strByExcerciseType = (exerciseType.equals("Blood pressure") ?
                "to keep a low blood pressure." : "for cardiovascular health.");
        if (daysNeeded == 0) {
            println(exerciseType + ":");
            println("\tGreat job! You've done enough exercise " + strByExcerciseType);
        } else {
            println(exerciseType + ":");
            println("\tYou needed to train hard for at least " + daysNeeded + " more day(s) a week!");
        }
    }
}

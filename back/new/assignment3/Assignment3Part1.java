package com.shpp.p2p.cs.bkuzhel.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Class is used to track cardiovasular and blood pressure health
 * depends on how much minutes user do aerobics each day
 * */
public class Assignment3Part1 extends TextProgram {

    private static final int DAYS_IN_WEEK = 7;

    /*Constants of recommended days of exercises for cardiovascular and blood pressure*/
    private static final int RECOMMENDED_DAYS_FOR_CARDIOVASCULAR = 5;
    private static final int RECOMMENDED_DAYS_FOR_BLOOD_PRESSURE = 3;
    /*Constants of recommended time of exercises for cardiovascular and blood pressure*/
    private static final int RECOMMENDED_TIME_FOR_CARDIOVASCULAR = 30;
    private static final int RECOMMENDED_TIME_FOR_BLOOD_PRESSURE = 40;

    /**
     * Main function that starts all program
     * */
    public void run() {
        double[] array = askUser();
        getResults(array);
    }

    /**
     * Function which get values of days when user trained enough and
     * starts function which prints info about training
     * @param array Array of minutes of training for each day
     * */
    private void getResults(double[] array) {
        int[] counters = countDays(array);
        getAerobicsReport(counters[0], counters[1]);
    }

    /**
     * Function is used to get report about aerobics, depends on users input. It checks
     * two counters for each type of training then calls Function printResults() which
     * prints if user trained enough or not.
     * @param cardiovascularCounter Integer value of days when user trained enough for
     *                                cardiovascular health
     * @param bloodPressureCounter Integer value of days when user trained enough for
     *                             blood pressure health
     * */
    private void getAerobicsReport(int cardiovascularCounter, int bloodPressureCounter) {
        printResults("\nCardiovascular health",
                (cardiovascularCounter >= RECOMMENDED_DAYS_FOR_CARDIOVASCULAR ?
                        0 : RECOMMENDED_DAYS_FOR_CARDIOVASCULAR - cardiovascularCounter));
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

    /**
     * Function that counts days when user is trained enough for
     * each type of health
     * @param array Array of minutes of training for each day
     * */
    private int[] countDays(double[] array) {
        int cardiovascularCounter = 0;
        int bloodPressureCounter = 0;
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            cardiovascularCounter += (array[i] >= RECOMMENDED_TIME_FOR_CARDIOVASCULAR ? 1 : 0);
            bloodPressureCounter += (array[i] >= RECOMMENDED_TIME_FOR_BLOOD_PRESSURE ? 1 : 0);
        }
        return new int[]{cardiovascularCounter, bloodPressureCounter};
    }

    /**
     * Function that asks user about how much minutes he/she trained
     * every day
     * @return Array of minutes of training for each day
     * */
    private double[] askUser() {
        double[] array = new double[DAYS_IN_WEEK];
        double minutes;
        for (int i = 0; i < DAYS_IN_WEEK; i++) {
            print("How many minutes did you do on day " + (i+1) +"? ");
            minutes = readDouble();
            while (minutes < 0) {
                print("Minutes of training cannot be negative. Try again: ");
                minutes = readInt();
            }
            array[i] = minutes;
        }
        return array;
    }

}

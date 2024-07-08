package com.shpp.p2p.cs.bkuzhel.assignment3;

import com.shpp.cs.a.console.TextProgram;


public class Assignment3Part3 extends TextProgram {
    public void run() {
        while (true) {
            raiseToPower(readDouble("Enter base: "), readInt("Enter exponent: "));
        }
    }

    /**
     * Methods which starts the certain calculations depends on the exponent
     *
     * @param base     Number that is raised to a power
     * @param exponent Power number
     */
    private void raiseToPower(double base, int exponent) {
        if (exponent == 0) {
            printResult(base, exponent, String.valueOf(1));
        }
        if (exponent > 0) {
            calculateWithPositiveExponent(base, exponent);
        }
        if (exponent < 0) {
            calculateWithNegativeExponent(base, exponent);
        }
    }

    /**
     * If exponent is negative this method is called.
     *
     * @param base     Number that is raised to a power
     * @param exponent Power number
     */
    private void calculateWithNegativeExponent(double base, int exponent) {
        int[] splitted;
        while (base == 0.0){
            base = readDouble("Wrong base, try again: ");
        }
        //if the number is fractional
        if (base % 2 != 1 && base % 2 != 0) {
            //split it to numerator and denominator
            splitted = splitFractionalNumber(base);
            //since the negative exponent, fraction must be inverted
            swap(splitted);
            //calculate number to power
            calculateDoubleNumber(base, exponent, splitted);
        }
        //if the number is integer
        else {
            //since the negative exponent, number must be inverted
            splitted = new int[]{1, (int) base};
            //calculate number to power
            calculateDoubleNumber(base, exponent, splitted);
        }
    }

    /**
     * swaps data in an array
     *
     * @param arr Array of 2 numbers
     */
    public void swap(int[] arr) {
        int temp = arr[0];
        arr[0] = arr[1];
        arr[1] = temp;
    }

    /**
     * If exponent is positive this method is called.
     *
     * @param base     Number that is raised to a power
     * @param exponent Power number
     */
    private void calculateWithPositiveExponent(double base, int exponent) {
        while (base == 0.0){
            base = readDouble("Wrong base, try again: ");
        }
        //if the number is fractional
        if (base % 2 != 1 && base % 2 != 0) {
            //split it to numerator and denominator
            int[] splitted = splitFractionalNumber(base);
            //calculate number to power
            calculateDoubleNumber(base, exponent, splitted);
        }
        //If number is integer
        else {
            printResult(base, exponent, String.valueOf(calculateIntegerNumber(base, exponent)));
        }
    }

    /**
     * Splits fractional base to numerator and denominator
     *
     * @param base Fractional number to split
     * @return Array of 2 numbers, where 0 - numerator, 1 - denominator
     */
    private int[] splitFractionalNumber(double base) {
        int[] numAndDenom = new int[2];
        //separate the whole and fractional part
        String[] arr = String.valueOf(base).split("\\.");
        //calculate numerator
        numAndDenom[0] = Integer.parseInt(arr[0] + arr[1]); // 9
        //calculate denominator based on the number of decimal places
        numAndDenom[1] = (int) calculateIntegerNumber(10, arr[1].length());
        return numAndDenom;
    }


    /**
     * Raises double/fractional number to power
     *
     * @param base     Number that is raised to a power
     * @param exponent Power number
     * @param splitted Array of two numbers to raise to power
     */
    private void calculateDoubleNumber(double base, int exponent, int[] splitted) {
        //raises numerator and denominator to power
        for (int i = 0; i <= 1; i++) {
            splitted[i] = (int) calculateIntegerNumber(splitted[i], exponent);
        }
        //reduce the numerator and denominator
        int[] num = reduceTheFraction(splitted);
        //display information
        printDouble(base, exponent, num);
    }

    /**
     * Print information after calculation for
     * double/fractional numbers
     *
     * @param base     Number that is raised to a power
     * @param exponent Power number
     * @param data     Array of numbers after calculation
     */
    private void printDouble(double base, int exponent, int[] data) {
        String strValue;
        //if number is double
        if (data[0] == 0) {
            strValue = String.valueOf(data[1]);
        }
        //if number is fractional
        else {
            strValue = data[0] + "/" + data[1];
        }
        printResult(base, exponent, strValue);
    }

    /**
     * Reduce the numerator and denominator.
     * If possible, reduce it altogether, returns a number, if not,
     * looks for the greatest common divisor and divides by it.
     *
     * @param array Array of 2 numbers - numerator and denominator
     * @return Array of 2 reduced numbers. If 0 element of array is
     * equal to 0, that means that it was possible to reduce by dividing the
     * denominator by the numerator. If 0 element is not equal to 0,
     * then return fractional.
     */
    private int[] reduceTheFraction(int[] array) {
        //If the numerator is evenly divisible by the denominator
        if (array[0] % array[1] == 0) {
            array[1] = array[0] / array[1];
            array[0] = 0;
        }
        //if not
        else {
            //then find the greatest common divisor and divide by it.
            int GCD = findGCD(array[0], array[1]);
            for (int i = 0; i <= 1; i++) {
                array[i] /= GCD;
            }
        }
        return array;
    }

    /**
     * Raises to power integer number.
     *
     * @param base     Number that is raised to a power
     * @param exponent Power number
     * @return Number after raising to power
     */
    private double calculateIntegerNumber(double base, int exponent) {
        double num = base;
        for (int i = 0; i < abs(exponent) - 1; i++) {
            num *= base;
        }
        return num;
    }

    /**
     * The module of a number. Always returns a positive number
     *
     * @param value Number to be taken under the module
     * @return Positive number
     */
    private int abs(int value) {
        return (value > 0 ? value : -value);
    }

    /**
     * Finds the greatest common divisor for 2 values using Euclid algorithm
     *
     * @param a First value for which the greatest common divisor is found
     * @param b Second value for which the greatest common divisor is found
     * @return The greatest common divisor
     */
    private int findGCD(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return findGCD(b, a % b);
        }
    }

    /**
     * Prints result
     *
     * @param base     Number that is raised to a power
     * @param exponent Power number
     * @param result   String version of result
     */
    private void printResult(double base, int exponent, String result) {
        println("The number " + base + " to the power of " + exponent + " = " + result);
    }
}

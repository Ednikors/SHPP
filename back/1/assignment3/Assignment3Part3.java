package com.shpp.p2p.cs.bkuzhel.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Class that implements raising a number to a power
 * */
public class Assignment3Part3 extends TextProgram{

    /**
     * Main function that starts all program.
     * It asks user to enter base and exponent, then
     * starts function that raise base to power
     * */
    public void run() {
        double base = readDouble("Enter base: ");
        int exponent = readInt("Enter exponent: ");
        double result = raiseToPower(base, exponent);
        println(base + " ^ " + exponent + " = " + result);
    }

    /**
     * Function that raise base to power.
     * First it checks if exponent is 0, then returns 1.
     * If exponents is not 0, then base is raised to power depends on
     * if exponent is negative or positive.
     * */
    private double raiseToPower(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }

        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }

        double result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

}

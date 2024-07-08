package com.shpp.p2p.cs.bkuzhel.assignment2;

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {

    /**
     * When program starts method calculateRoots(...) calls function enterValues()
     * to get values of a, b, c from it and calculate roots of equation
     */
    public void run() {
        calculateRoots(enterValues());

    }

    /**
     * Returns list of values {a, b, c} which it gets from user
     *
     * @return Parameters of the quadratic equation
     */
    public double[] enterValues() {
        double[] values = new double[3];
        print("Please enter a: ");
        values[0] = readDouble();
        print("Please enter b: ");
        values[1] = readDouble();
        print("Please enter c: ");
        values[2] = readDouble();
        return values;
    }

    /**
     * Calculates roots of equation using discriminant.
     * The list of values must be in the form of an array
     * of 3 values{a,b,c}.
     *
     * @param values Parameters of the quadratic equation a, b and c
     */
    private void calculateRoots(double[] values) {
        double a = values[0];
        double b = values[1];
        double c = values[2];
        double D = Math.pow(b, 2) - 4 * a * c; // Discriminant calculation
        if (D < 0) {
            print("There are no real roots");
        }
        if (D == 0) {
            double x = -b / 2 * a;
            print("There in one root: " + x);
        }
        if (D > 0) {
            double x1 = (-b + Math.sqrt(D)) / 2 * a;
            double x2 = (-b - Math.sqrt(D)) / 2 * a;
            print("There are two roots: " + x1 + " and " + x2);
        }
    }

}

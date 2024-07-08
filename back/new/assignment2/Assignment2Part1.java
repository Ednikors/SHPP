package com.shpp.p2p.cs.bkuzhel.assignment2;

import com.shpp.cs.a.console.TextProgram;

/**This class is designed for finding the roots of a quadratic equation*/
public class Assignment2Part1 extends TextProgram {

    /**
     * Main function which gets parameters of quadratic equation from user and starts
     * function which calculates roots of this equation
     * */
    public void run() {
        print("Enter a: ");
        double a = readDouble();
        print("Enter b: ");
        double b = readDouble();
        print("Enter c: ");
        double c = readDouble();
        calculateRoots(a, b, c);
    }


    /**
     * Function which calculates roots of quadratic equation depends on discriminant value.
     * First, discriminant is calculated, then if discriminant > 0, function calculates two roots,
     * if discriminant = 0 then only one root is calculated and if discriminant is less than 0
     * there are no roots to calculate.
     *
     * @param a First parameter of quadratic equation
     * @param b Second parameter of quadratic equation
     * @param c Third parameter of quadratic equation
     * */
    private void calculateRoots(double a, double b, double c) {
        double d = calculateDiscriminant(a, b, c);
        if (d>0){
            double x1 = (-b + Math.sqrt(d))/(2*a);
            double x2 = (-b - Math.sqrt(d))/(2*a);
            System.out.println("There are two roots: " + x1 +" and " + x2);
        } else if (d == 0){
            double x = -b / 2*a;
            System.out.println("There is one root: " + x);
        } else {
            System.out.println("There are no real roots");
        }

    }

    /**
     * This function is used to calculate discriminant.
     *
     * @param a First parameter of quadratic equation
     * @param b Second parameter of quadratic equation
     * @param c Third parameter of quadratic equation
     * */
    private double calculateDiscriminant(double a, double b, double c) {
        return Math.pow(b, 2) - 4 * a * c;
    }
}

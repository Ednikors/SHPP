package com.shpp.p2p.cs.bkuzhel.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * Class that implements number-gradients
 * */
public class Assignment3Part2 extends TextProgram {

    /**
     * Main function that starts all program
     * */
    public void run() {
        int n = readPositiveInt("Enter a positive number: ");
        calculate(n);
    }

    /**
     * Function that implements main calculation of program.
     * This function works till n won't become 1.
     * Inside while loop, code checks if n is even or odd, then
     * starts corresponding function. And when n become 1,
     * code ends calculation and prints "the end".
     * @param n Integer value to be calculated
     * */
    private void calculate(int n) {
        while (n != 1) {
            n = (n % 2 == 0) ? calculateIfEven(n) : calculateIfOdd(n);
        }
        println("the end");
    }

    /**
     * If n is odd, then this function is started.
     * This function calculates new number, prints it then returns.
     * @param oddNumber Integer value of n after calculation
     * */
    private int calculateIfOdd(int oddNumber) {
        int result = oddNumber * 3 + 1;
        println(oddNumber + " is odd so I make 3n + 1: " + result);
        return result;
    }

    /**
     * If n is evem, then this function is started.
     * This function calculates new number, prints it then returns.
     * @param evenNumber Integer value of n after calculation
     * */
    private int calculateIfEven(int evenNumber) {
        int result = evenNumber / 2;
        println(evenNumber + " is even so I take half: " + result);
        return result;
    }

    /**
     * Function that checks if user enters right number.
     * If not it asks user to enter new value then returns it.
     * @param prompt String value of n to be checked
     * @return right value of n.
     * */
    private int readPositiveInt(String prompt) {
        int n = 0;
        while (n <= 0) {
            n = readInt(prompt);
        }
        return n;
    }
}

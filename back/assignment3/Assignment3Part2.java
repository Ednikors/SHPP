package com.shpp.p2p.cs.bkuzhel.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {

    // зробити обробку <= 0
    public void run() {
        calculate(readInt("Enter a number: "));
    }

    /**
     * Method which checks if parameter is odd or even
     * and sends it to specialised function which calculates result
     *
     * @param n Integer number
     * */
    private void calculate(int n) {
        while (n != 1) {
            //Checks if n is odd or not
            n = (n % 2 == 0 ? calculateIfEven(n) : calculateIfOdd(n));
        }
        println("the end");
    }

    /**
     * If number is odd this method multiply it by 3 and add 1
     * then return
     *
     * @param oddNumber Integer odd number
     * @return Number after calculation
     * */
    private int calculateIfOdd(int oddNumber) {
        int temp = oddNumber;
        oddNumber = temp * 3 + 1;
        println(temp + " is odd so I make 3n + 1: " + oddNumber);
        return oddNumber;
    }

    /**
     * If number is even this method divide it by 2
     * then return
     *
     * @param evenNumber Integer even number
     * @return Number after calculation
     * */
    private int calculateIfEven(int evenNumber) {
        int temp = evenNumber;
        evenNumber /= 2;
        println(temp + " is even so I take half: " + evenNumber);
        return evenNumber;
    }


}

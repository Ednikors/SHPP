package com.shpp.p2p.cs.bkuzhel.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * Class which implements addition of two strings of digits.
 * Used when user needs to add two numbers which is bigger
 * than int type can be.
 * */
public class AlgorismAlgorithms extends TextProgram {
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        if (isDigitStrings(new String[]{n1, n2})) {
            int maxLength = Math.max(n1.length(), n2.length());
            n1 = String.format("%" + maxLength + "s", n1).replace(' ', '0');
            n2 = String.format("%" + maxLength + "s", n2).replace(' ', '0');

            return calculate(n1, n2, maxLength);
        }
        return "Entered values in not a digits";
    }

    /**
     * Function which checks if two strings are made of
     * digits. Return false if in strings are at least one char,
     * true if all made of digits.
     *
     * @param data Array of chars to be checked
     * @return true\false depending on if strings are made of digits or not.
     * */
    private boolean isDigitStrings(String[] data) {
        for (String word : data) {
            for (char c : word.toCharArray()) {
                if (!Character.isDigit(c)) return false;
            }
        }
        return true;
    }

    /**
     * Main function which adds two strings together.
     * First, symbols of the same position in two strings converts to
     * integer value, then adds. If result contains tens, then they are moved to the next line.
     *
     * @param n1 First string value to be added
     * @param n2 Second string value to be added
     * @param maxLength Integer value of the biggest string size
     * @return String value of added numbers.
     * */
    private String calculate(String n1, String n2, int maxLength) {
        StringBuilder result = new StringBuilder();
        int temp = 0;

        for (int i = maxLength - 1; i >= 0; i--) {
            int digit1 = n1.charAt(i) - '0';
            int digit2 = n2.charAt(i) - '0';

            int sum = digit1 + digit2 + temp;
            temp = sum / 10;
            int digit = sum % 10;

            result.insert(0, digit);
        }

        if (temp > 0) {
            result.insert(0, temp);
        }

        return result.toString();
    }

}

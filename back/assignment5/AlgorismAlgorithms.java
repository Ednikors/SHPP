package com.shpp.p2p.cs.bkuzhel.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * Class intended for adding numbers greater than long type
 */
public class AlgorismAlgorithms extends TextProgram {
    public void run() {
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
        if (isNotDigits(new String[]{n1, n2})) {
            return "*ERROR* You've entered wrong number, try again.";
        }
        if (n1.length() > n2.length()) {
            n2 = toSimilarSize(n2, n1.length());
        } else {
            n1 = toSimilarSize(n1, n2.length());
        }
        return calculateResult(n1, n2);
    }

    /**
     * Method which checks if there are any other
     * letters or symbols in input data to inform
     * user that he have entered wrong value
     *
     * @param data List of input values to be checked
     * @return Boolean value, true - if input data contains not only digits,
     * false - if input data is only digits
     */
    private boolean isNotDigits(String[] data) {
        for (String word : data) {
            for (char c : word.toCharArray()) {
                if (!Character.isDigit(c)) return true;
            }
        }
        return false;
    }

    /**
     * Method which calculates sum of two input values.
     *
     * @param n1 String value of first number
     * @param n2 String value of second number
     * @return String value of sum of two numbers
     */
    private String calculateResult(String n1, String n2) {
        StringBuilder result = new StringBuilder();
        int tens = 0;
        char[] n1Array = n1.toCharArray();
        char[] n2Array = n2.toCharArray();
        for (int i = n1.length() - 1; i >= 0; i--) {
            int temp = (n1Array[i] - '0') + (n2Array[i] - '0') + tens;
            if (temp >= 10) {
                tens = temp / 10;
                temp = temp % 10;
            } else {
                tens = 0;
            }
            result.insert(0, temp);
        }
        if (tens > 0) {
            result.insert(0, tens);
        }
        return result.toString();
    }

    /**
     * Method is used to bring the input data to the same length
     *
     * @param str       String value which length must be increased
     * @param strLength Integer value of length of the string
     */
    private String toSimilarSize(String str, int strLength) {
        StringBuilder strBuilder = new StringBuilder(str);
        while (strBuilder.length() < strLength) {
            strBuilder.insert(0, "0");
        }
        return strBuilder.toString();
    }


}
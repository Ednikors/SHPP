package com.shpp.p2p.cs.bkuzhel.assignment10;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The main class that implements the functionality of the simple calculator
 */
public class Assignment10Part1 {
    /**
     * Variable pattern, used to validate the correctness of parameter input
     */
    private static final Pattern VARIABLE_PATTERN = Pattern.compile("([a-zA-Z]+)\\s*=\\s*(-?\\d+(\\.\\d+)?)");

    /**
     * The main method that accepts as args[] the expression(args[0]) and its parameters.
     * Also, divides the expression and parameters into different variables, calls the expression
     * calculation function and outputs the result
     *
     * @param args Input, where args[0] - expression, args[n] - parameters
     */
    public static void main(String[] args) {
        String expression = args[0].replaceAll("\\s+", "");
        HashMap<String, Double> variables = parseParameters(args);
        ExpressionCalculator calculator = new ExpressionCalculator();
        double result = calculator.calculate(expression, variables);
        System.out.println("\nExpression: " + expression + "\n" +
                "Arguments:" + variables + "\n" +
                "Result: " + result);
    }

    /**
     * Method used to parse parameters and convert them to HashMap
     *
     * @param args - parameters to be converted(input as string)
     * @return HashMap of variables
     */
    private static HashMap<String, Double> parseParameters(String[] args) {
        HashMap<String, Double> variables = new HashMap<>();
        for (int i = 1; i < args.length; i++) {
            Matcher matcher = VARIABLE_PATTERN.matcher(args[i].replaceAll(" ", ""));
            if (matcher.matches()) {
                String variableName = matcher.group(1);
                double value = Double.parseDouble(matcher.group(2));
                variables.put(variableName, value);
            }
        }
        return variables;
    }
}

package com.shpp.p2p.cs.bkuzhel.assignment11;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The main class that implements the functionality of the advanced calculator
 */
public class Assignment11Part1 {

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
        HashMap<String, Double> variables = parseParameters(args);
        ExpressionProcessing exp = new ExpressionProcessing();
        RecursiveCalc calc = new RecursiveCalc();
        try {
            String result = calc.expression(exp.expressionProcessing(args[0], variables));
            System.out.println("Expression: " + args[0] + "\n" +
                    "Variables: " + variables + "\n" +
                    "Calculated result: " + result);
        } catch (Exception e) {
            System.out.println(exceptionHandler(e, args[0], variables));
        }
    }

    /**
     * An error handler that displays information to the user about incorrect input, etc.
     *
     * @param e Exception object
     * @param expression String value of expression to be calculated
     * @param variables HashMap of variables for expression
     *
     * @return String with information about exception
     * */
    private static String exceptionHandler(Exception e, String expression, HashMap<String, Double> variables) {
        switch (e.getMessage()) {
            case "no expression" -> {
                return "\nThe expression is not entered.\n" +
                        "Example: sin(2)*x+-3y+(3-6)\n" +
                        "Your expression: " + expression + "\n";
            }
            case "no parameters" -> {
                return "\nParameters are" + (variables.isEmpty() ? " not entered at all." :
                        " either not all entered or entered incorrectly.") + "\n" +
                        "Example: x=2, b=-3\n" +
                        "Your parameters: " + variables + "\n";
            }
            case "closing bracket" -> {
                return "\nClosing brackets are missing. Please check your expression.\n" +
                        "Example: sin(10) or (3+3)\n" +
                        "Your expression: " + expression + "\n";
            }
            case "opening bracket" -> {
                return "\nOpening brackets in function are missing. Please check your expression.\n" +
                        "Example: sin(10)\n" +
                        "Your expression: " + expression + "\n";
            }
            case "incorrect expression" -> {
                return "\nThe expression is entered incorrectly.\n" +
                        "Example: sin(2)*x+-3y+(3-6)\n" +
                        "Your expression: " + expression + "\n";
            }
        }
        return e.getMessage();
    }

    /**
     * Method used to parse parameters and convert them to HashMap
     *
     * @param args - parameters to be converted(input as string)
     *
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

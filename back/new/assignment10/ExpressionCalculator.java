package com.shpp.p2p.cs.bkuzhel.assignment10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class in which the logic of the calculator is written
 */
public class ExpressionCalculator {
    /**
     * A constant array of operators that can be used in the calculator. They are arranged in
     * descending order of their priority
     */
    public static final List<String> OPERATORS = Arrays.asList("^", "/", "*", "-", "+");

    /**
     * Expression variable pattern, used for correct reading parameters
     */
    Pattern EXPRESSION_VARIABLE_PATTERN = Pattern.compile("((-?\\d+)?)(-?[a-z]+)((\\d+)?)");

    /**
     * In this method, First, the function that processes the expression is called.
     * Then, using a loop, the expression is calculated for each parameter in the order of calculation.
     *
     * @param expression expression to be calculated
     * @param variables  variables of expression
     * @return result of calculation
     */
    public double calculate(String expression, HashMap<String, Double> variables) {
        try {
            List<String> expressionAsList = expressionProcessing(expression, variables);
            for (String operator : OPERATORS) {
                for (int i = 0; i < countOperators(operator, expressionAsList); ) {
                    int operatorIndex = expressionAsList.indexOf(operator);
                    expressionAsList.set(operatorIndex, String.valueOf(applyOperation(operator, Double.parseDouble(expressionAsList.get(operatorIndex - 1)),
                            Double.parseDouble(expressionAsList.get(operatorIndex + 1)))));
                    expressionAsList.remove(operatorIndex + 1);
                    expressionAsList.remove(operatorIndex - 1);
                }
            }
            return Double.parseDouble(expressionAsList.get(0));
        } catch (NumberFormatException e) {
            System.out.println("\nVariables are either entered incorrectly or not entered at all. Example: a=2");
            return 0;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nNo expression entered");
            return 0;
        }
    }

    /**
     * Method, used to process the expression. First, it splits expression to the List<String>, then looks for parameters
     * in the expression, then replaces the letter with its enumerated representation from the HashMap of variables.
     * If there is a number with the parameter, it multiplies them.
     *
     * @param expression expression to be calculated
     * @param variables  variables of expression
     * @return processed expression, ready to be calculated
     */
    private List<String> expressionProcessing(String expression, HashMap<String, Double> variables) {
        List<String> splittedExpression = splitExpression(expression);
        for (String element : splittedExpression) {
            Matcher matcher = EXPRESSION_VARIABLE_PATTERN.matcher(element);
            if (matcher.matches()) {
                String variable = matcher.group(3);
                if (variable.startsWith("-")) {
                    variable = variable.substring(1);
                }
                if (variables.containsKey(variable)) {
                    double leftNumber = matcher.group(2) != null ? Double.parseDouble(matcher.group(2)) : 1;
                    double rightNumber = matcher.group(5) != null ? Double.parseDouble(matcher.group(5)) : 1;
                    double variableValue = variables.get(variable);
                    double result = leftNumber * (element.contains("-" + variable) ? -1 : 1) * variableValue * rightNumber;
                    splittedExpression.set(splittedExpression.indexOf(element), String.valueOf(result));
                }
            }
        }
        return splittedExpression;
    }

    /**
     * Method used to calculate how many times each operator is used in expression
     */
    private int countOperators(String op, List<String> splitExpression) {
        int count = 0;
        for (String l : splitExpression) {
            if (l.equals(op)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Method is used to split expression into list of strings
     *
     * @param expression expression as string
     * @return expression as list of strings
     */
    public static List<String> splitExpression(String expression) {
        List<String> result = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        boolean isNegative = false;
        boolean previousWasOperator = true;

        // Iterate each character in the string
        for (char currentChar : expression.toCharArray()) {

            // If the character is an operator
            if (OPERATORS.contains("" + currentChar)) {
                // If the current token is not empty, add it to the result
                if (!currentToken.isEmpty()) {
                    result.add(currentToken.toString());
                    currentToken = new StringBuilder();
                }

                // If the operator is '-' and the previous token was an operator, it means a negative number
                if (currentChar == '-' && previousWasOperator) {
                    isNegative = true;
                } else {
                    // Otherwise, add the operator to the result
                    result.add(isNegative ? "-" : "" + currentChar);
                    isNegative = false;
                    previousWasOperator = true;
                }
            } else {
                // If the character is not an operator
                if (isNegative) {
                    // If the previous token was a negative number, append '-' to the current token
                    currentToken.append('-');
                    isNegative = false;
                }
                // Append the character to the current token
                currentToken.append(currentChar);
                previousWasOperator = false;
            }
        }

        // If there is an unfinished token left, add it to the result
        if (!currentToken.isEmpty()) {
            result.add(currentToken.toString());
        }

        return result;
    }

    /**
     * A method that evaluates each operation in an expression
     */
    private static Double applyOperation(String op, double a, double b) {
        switch (op) {
            case "+" -> {
                return a + b;
            }
            case "-" -> {
                return a - b;
            }
            case "*" -> {
                return a * b;
            }
            case "/" -> {
                if (b == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                return a / b;
            }
            case "^" -> {
                return Math.pow(a, b);
            }
        }
        return null;
    }
}

package com.shpp.p2p.cs.bkuzhel.assignment11;

import java.util.HashMap;
import java.util.List;

//        E -> T + E | T - E | T
//        T -> F * T | F / T | F
//        F -> N     | (E)
//        FUNC -> NAME(E)

/**
 * A class in which the logic of the calculator is described
 * */
public class RecursiveCalc {

    /**
     * Variable of current lexeme position in list
     * */
    private int curLexemePos = 0;

    /**
     * A function that describes the first level of recursion. Used for addition and subtraction.
     * First, calls the term function to get the terms of the expression. Next, it calculates the value of the
     * results according to the sign, and if the expression is the end, it returns the value
     *
     * @param lexemes List of expression splitted to lexemes
     *
     * @return String value of the evaluated expression
     * */
    public String expression(List<Lexeme> lexemes) {
        Lexeme lexeme = lexemes.get(curLexemePos++);
        if (lexeme.type == Lexeme.LexemeType.EOE) {
            throw new RuntimeException("no expression");
        } else {
            curLexemePos--;
        }
        double leftOperand = term(lexemes);
        while (curLexemePos < lexemes.size()) {
            lexeme = lexemes.get(curLexemePos);
            switch (lexeme.type) {
                case OP_PLUS -> {
                    curLexemePos++;
                    leftOperand += term(lexemes);
                }
                case OP_MINUS -> {
                    curLexemePos++;
                    leftOperand -= term(lexemes);
                }
                case OP_POW -> { // Новий випадок для операції степеня
                    curLexemePos++;
                    leftOperand = Math.pow(leftOperand, term(lexemes));
                }
                case RIGHT_BRACKET, EOE -> {
                    return String.valueOf(Math.round(leftOperand*100.0)/100.0);
                }
                default -> throw new RuntimeException("Invalid operator: " + lexeme.value);
            }
        }
        return null;
    }


    /**
     * A function that implements the second level of recursion.
     * It is used for multiplication and division of numbers. First, calls the term function to
     * get the factors(numbers) of the terms. Next, it calculates the value of the results according to the sign,
     * and if the expression is the end, it returns the value
     *
     * @param lexemes List of lexemes
     *
     * @return term value
     * */
    private double term(List<Lexeme> lexemes) {
        double leftOperand = factor(lexemes);
        while (curLexemePos < lexemes.size()) {
            Lexeme lexeme = lexemes.get(curLexemePos);
            switch (lexeme.type) {
                case OP_MUL -> {
                    curLexemePos++;
                    leftOperand *= factor(lexemes);
                }
                case OP_DIV -> {
                    curLexemePos++;
                    leftOperand /= factor(lexemes);
                }
                default -> {
                    return leftOperand;
                }
            }
        }
        return leftOperand;
    }

    /**
     * A function that implements the third level of recursion.
     * It is used for calculating factors.
     *
     * @param lexemes List of lexemes
     *
     * @return factor value
     * */
    private double factor(List<Lexeme> lexemes) {
        Lexeme lexeme = lexemes.get(curLexemePos++);
        double factor;
        switch (lexeme.type) {
            case OP_MINUS -> {
                factor = factor(lexemes);
                return -factor;
            }
            case LEFT_BRACKET -> {
                factor = Double.parseDouble(expression(lexemes));
                lexeme = lexemes.get(curLexemePos++);
                if (lexeme.type != Lexeme.LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("closing bracket");
                }
                while (curLexemePos < lexemes.size()) {
                    lexeme = lexemes.get(curLexemePos);
                    if (lexeme.type == Lexeme.LexemeType.OP_PLUS || lexeme.type == Lexeme.LexemeType.OP_MINUS
                            || lexeme.type == Lexeme.LexemeType.OP_MUL || lexeme.type == Lexeme.LexemeType.OP_DIV
                            || lexeme.type == Lexeme.LexemeType.RIGHT_BRACKET || lexeme.type == Lexeme.LexemeType.EOE) {
                        curLexemePos--;
                        break;
                    }
                    curLexemePos++;
                }
                return factor;
            }
            case FUNC -> {
                curLexemePos--;
                return func(lexemes);
            }
            case NUMBER -> {
                return Double.parseDouble(lexeme.value);
            }
            default -> throw new RuntimeException("incorrect expression");
        }
    }

    /**
     * A function that is designed to process and calculate functions in an expression
     *
     * @param lexemes List of lexemes
     *
     * @return function value
     * */
    private double func(List<Lexeme> lexemes) {
        HashMap<String, Function> func = ExpressionProcessing.getFunctionsMap();
        Lexeme lexeme = lexemes.get(curLexemePos++);
        String funcName = lexeme.value;
        double value=0;
        lexeme = lexemes.get(curLexemePos++);
        if (lexeme.type == Lexeme.LexemeType.LEFT_BRACKET) {
            curLexemePos--;
            value = Double.parseDouble(expression(lexemes));
            lexeme = lexemes.get(curLexemePos++);
            if (lexeme.type != Lexeme.LexemeType.RIGHT_BRACKET) {
                throw new RuntimeException("closing bracket");
            }
        } else if (lexeme.type == Lexeme.LexemeType.NUMBER){
            throw new RuntimeException("opening bracket");
        }
        Function function = func.get(funcName);
        if (function == null) {
            throw new RuntimeException("\nThe calculator does not support the function: " + funcName+"\n" +
                    "Supported functions:"+func.keySet()+"\n");
        }
        return function.call(value);
    }

}
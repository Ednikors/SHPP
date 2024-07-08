package com.shpp.p2p.cs.bkuzhel.assignment11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A class that describes the logic of splitting an expression into lexemes and
 * declares a function that returns a hashmap with mathematical functions.
 */
public class ExpressionProcessing {

    /**
     * Method that cleans the expression of whitespace and runs a function that splits it into lexemes
     *
     * @param expression String value of expression
     * @return List of lexemes
     * */
    public List<Lexeme> expressionProcessing(String expression, HashMap<String, Double> variables) {
        expression = expression.replaceAll("\\s+", "");
        return getLexemeList(expression, variables);
    }

    /**
     * Method that splits expression into lexemes. The method goes through each symbol in the expression,
     * then writes it according to the symbol into an array of lexeme objects, which contains information
     * about the type and value of each lexeme. Also, replaces the parameters in the expression with their numeric value
     *
     * @param expression String value of expression
     * @param variables HashMap of variables
     *
     * @return List of lexemes
     * */
    private List<Lexeme> getLexemeList(String expression, HashMap<String, Double> variables) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int curPos = 0;
        while (curPos < expression.length()) {
            char c = expression.charAt(curPos);
            switch (c) {
                case '+' -> {
                    lexemes.add(new Lexeme(Lexeme.LexemeType.OP_PLUS, "" + c));
                    curPos++;
                }
                case '-' -> {
                    lexemes.add(new Lexeme(Lexeme.LexemeType.OP_MINUS, "" + c));
                    curPos++;
                }
                case '*' -> {
                    lexemes.add(new Lexeme(Lexeme.LexemeType.OP_MUL, "" + c));
                    curPos++;
                }
                case '/' -> {
                    lexemes.add(new Lexeme(Lexeme.LexemeType.OP_DIV, "" + c));
                    curPos++;
                }
                case '^' -> {
                    lexemes.add(new Lexeme(Lexeme.LexemeType.OP_POW, "" + c));
                    curPos++;
                }
                case '(' -> {
                    lexemes.add(new Lexeme(Lexeme.LexemeType.LEFT_BRACKET, "" + c));
                    curPos++;
                }
                case ')' -> {
                    lexemes.add(new Lexeme(Lexeme.LexemeType.RIGHT_BRACKET, "" + c));
                    curPos++;
                }
                default -> {
                    //if number
                    if (c >= '0' && c <= '9') {
                        StringBuilder sb = new StringBuilder();
                        while (c >= '0' && c <= '9') {
                            sb.append(c);
                            curPos++;
                            if (curPos >= expression.length()) {
                                break;
                            }
                            c = expression.charAt(curPos);
                        }
                        lexemes.add(new Lexeme(Lexeme.LexemeType.NUMBER, sb.toString()));

                        //if there is a letter near to number( 3y )
                        if (Character.isLetter(c)) {
                            lexemes.add(new Lexeme(Lexeme.LexemeType.OP_MUL, "*"));
                        }
                    } else {

                        //this part of the code handles the literal part of the code.
                        //this loop collects letters group into one string
                        if (Character.isLetter(c)) {
                            StringBuilder sb = new StringBuilder();
                            while (Character.isLetter(c)) {
                                sb.append(c);
                                curPos++;
                                if (curPos >= expression.length()) {
                                    break;
                                }
                                c = expression.charAt(curPos);
                            }

                            //processing if a logarithm, since there are also numbers next to letter characters
                            if (sb.toString().equals("log")){
                                while (c!='('){
                                    sb.append(c);
                                    curPos++;
                                    if (curPos >= expression.length()) {
                                        break;
                                    }
                                    c = expression.charAt(curPos);
                                }
                            }

                            //if the length of the term is greater than 1, then it is a function
                            if (sb.length() > 1) {
                                lexemes.add(new Lexeme(Lexeme.LexemeType.FUNC, sb.toString()));
                            }

                            //if lenght of string = 0 then it's variable
                            else {
                                if (variables.containsKey(sb.toString())) {
                                    String variable = variables.get(sb.toString()).toString();
                                    lexemes.add(new Lexeme(Lexeme.LexemeType.NUMBER, variable));
                                }
                                else {throw new RuntimeException("no parameters");}
                            }

                        }
                    }
                }
            }
        }
        lexemes.add(new Lexeme(Lexeme.LexemeType.EOE, ""));
        return lexemes;
    }

    /**
     * A function that returns a hashmap of mathematical functions
     * */
    public static HashMap<String, Function> getFunctionsMap() {
        HashMap<String, Function> functions = new HashMap<>();
        functions.put("sin", Math::sin);
        functions.put("cos", Math::cos);
        functions.put("tan", Math::tan);
        functions.put("atan", Math::atan);
        functions.put("log10", Math::log10);
        functions.put("log2", x -> Math.log(x) / Math.log(2));
        functions.put("sqrt", Math::sqrt);
        return functions;
    }

}


package com.shpp.p2p.cs.bkuzhel.assignment11;


/**
 * A class that describes the behavior of a syre Lexeme object
 * */
public class Lexeme {
    LexemeType type;
    String value;

    /**
     * A class constructor
     * */
    public Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Enumeration of lexeme types, according to its symbol
     *
     * OP_PLUS - addition(+)
     * OP_MINUS - subtraction(+)
     * OP_MUL - multiplication(*)
     * OP_DIV - division(/)
     * OP_POW - raise to power(^)
     * NUMBER - numeric value
     * FUNC - function(sin(), cos(), etc.)
     * LEFT_BRACKET - left bracket( ( )
     * RIGHT_BRACKET - right bracket - ( ) )
     * EOE - end of expression
     */

    public enum LexemeType {
        OP_PLUS, OP_MINUS, OP_MUL, OP_DIV, OP_POW,
        NUMBER, FUNC,
        LEFT_BRACKET, RIGHT_BRACKET,
        EOE;
    }

}

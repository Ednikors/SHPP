package com.shpp.p2p.cs.bkuzhel.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.Random;

public class Assignment3Part5 extends TextProgram {

    public void run() {
        playGame();
    }

    /**
     * Method that starts a game
     */
    private void playGame() {
        int moneyAtLucky = 0;
        int moneyAtRound;
        //counter of rounds
        int counter = 0;
        //while money at lucky guy is less than 20
        while (moneyAtLucky < 20) {
            //play one round
            moneyAtRound = playOneRound();
            moneyAtLucky += moneyAtRound;
            printRoundResult(moneyAtRound, moneyAtLucky);
            counter++;
        }
        println("It took " + counter + " games to earn $20");
    }

    /**
     * Print result for one round
     *
     * @param moneyAtLucky Current amount of money at lucky guy
     * @param moneyAtRound Amount of money that lucky guy won at current round
     */
    private void printRoundResult(int moneyAtRound, int moneyAtLucky) {
        println("This game, you earned $" + moneyAtRound);
        println("Your total is $" + moneyAtLucky);
    }

    /**
     * Play one round
     *
     * @return Amount of money that lucky guy won at current round
     */
    private int playOneRound() {
        int startBet = 1;
        // if there is a head, then bet is doubled
        while (flipCoin()) {
            startBet += startBet;
        }
        //if tail, then money goes to lucky guy
        return startBet;
    }

    /**
     * Coin toss simulation
     *
     * @return Boolean value which means that there is head or tail
     * True - head, False - tail
     */
    private boolean flipCoin() {
        Random random = new Random();
        return random.nextBoolean();
    }
}

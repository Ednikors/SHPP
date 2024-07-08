package com.shpp.p2p.cs.bkuzhel.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.Random;

/**
 * Class that implements game "Bernoulli casino"
 * */
public class Assignment3Part5 extends TextProgram {

    /**
     * Main function that starts program
     * */
    public void run() {
        playGame();
    }

    /**
     * Function that implements main logic of game.
     * Game goes till lucky guy won't win 20$.
     * Lucky guy starts with 0$. Every round lucky guy can earn
     * endless amount of money depends on luck.
     * If lucky guy earn 20$ or more, game ends and prints information
     * about game.
     * */
    private void playGame() {
        int totalMoney = 0;
        int rounds = 0;

        while (totalMoney < 20) {
            int moneyWon = playOneRound();
            totalMoney += moneyWon;
            printRoundResult(moneyWon, totalMoney);
            rounds++;
        }

        println("It took " + rounds + " games to earn $20");
    }

    /**
     * Function that prints information for every round.
     * @param moneyWon Integer value of money that lucky guy won in one round
     * @param totalMoney Integer value of total money earned by lucky guy
     * */
    private void printRoundResult(int moneyWon, int totalMoney) {
        println("This game, you earned $" + moneyWon);
        println("Your total is $" + totalMoney);
    }

    /**
     * Function that implements one round of game.
     * Game starts from bet 1$. Then when lucky guy flips a coin
     * and head comes up, bet is doubled. If tail comes up, round ends.
     */
    private int playOneRound() {
        int bet = 1;

        while (flipCoin()) {
            bet *= 2;
        }

        return bet;
    }

    /**
     * Simulation of flipping a coin
     * */
    private boolean flipCoin() {
        Random random = new Random();
        return random.nextBoolean();
    }
}

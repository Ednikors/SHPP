package com.shpp.p2p.cs.bkuzhel.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Method which implements a road game. Used to find a word from dictionary
 * by 3 letters of car number plate
 */
public class Assignment5Part3 extends TextProgram {

    /**
     * Path to dictionary
     */
    private static final String PATH_TO_FILE = "assets\\en-dictionary.txt";

    /**
     * Main method which starts a game
     */
    public void run() {
        ArrayList<String> words = readWordsFromFile();
        while (true) {
            String readLetters = readLine("Enter 3 letters: ");
            if (readLetters.length() != 3) {
                println("You entered wrong amount of letters. Try again.");
                continue;
            }
            findWord(words, readLetters.toLowerCase());
        }
    }

    /**
     * The method takes each word, splits it into an array of chars, and
     * checks each letter to see if it is one of the 3 letters that the word should consist of.
     *
     * @param words   Array of string values from dictionary
     * @param letters String value of 3 letters from which is needed to get a word
     */
    private void findWord(ArrayList<String> words, String letters) {
        for (String word : words) {
            int currentIndex = 0;
            StringBuilder formedWord = new StringBuilder();
            for (char c : word.toCharArray()) {
                if (currentIndex < letters.length() && c == letters.charAt(currentIndex)) {
                    formedWord.append(c);
                    currentIndex++;
                }
            }
            if (formedWord.toString().equals(letters)) {
                println( "Word found: " + word);
            }
        }
    }

    /**
     * Method which reads data from file.
     *
     * @return String array of words from dictionary
     */
    private ArrayList<String> readWordsFromFile() {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }


}
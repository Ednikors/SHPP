package com.shpp.p2p.cs.bkuzhel.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Method which finds words in list by 3 letters.
 */
public class Assignment5Part3 extends TextProgram {

    /**
     * Path to dictionary
     */
    private static final String PATH_TO_FILE = "assets\\en-dictionary.txt";

    public void run() {
        ArrayList<String> words = readWordsFromFile();
        while (true) {
            String readLetters = readLine("Enter 3 letters: ");
            if (readLetters.length() != 3) {
                println("Wrong enter. Please, try again.");
                continue;
            }
            findMatchingWords(words, readLetters.toLowerCase());
        }
    }

    /**
     * Function which calls function that checks if entered 3 letters is in the word, if true
     * then print word.
     * @param words List of words from file
     * @param letters String values of entered letters
     * */
    private void findMatchingWords(ArrayList<String> words, String letters) {
        for (String word : words) {
            if (isWordMatching(word, letters)) {
                println("Word found: " + word);
            }
        }
    }

    /**
     * The method takes word, splits it into an array of chars, and
     * checks each letter to see if it is one of the 3 letters that the word should consist of.
     *
     * @param word String value of word to be checked
     * @param letters String values of entered letters
     * @return true/false depends on if word contains 3 entered letters
     * */
    private boolean isWordMatching(String word, String letters) {
        int currentIndex = 0;
        for (char c : word.toCharArray()) {
            if (currentIndex < letters.length() && c == letters.charAt(currentIndex)) {
                currentIndex++;
            }
        }
        return currentIndex == letters.length();
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
package com.shpp.p2p.cs.bkuzhel.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * Class which used to count amount of syllables in the word
 */
public class SyllableCounting extends TextProgram {
    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesInWord(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesInWord(String word) {
        if (!isOneSyllable(word)) {
            return countSyllable(word);
        }
        return 1;
    }

    /**
     * Param which start two methods which removes exceptions
     * and counts syllables
     *
     * @param word String value of word whose syllables must be counted
     * @return Integer value of counted syllables
     */
    private int countSyllable(String word) {
        word = removeExceptions(word);
        return getVowelsAmount(word);
    }

    /**
     * Remove from word last "e" and double vowels
     *
     * @param word String value to be modified
     * @return String value of word after removing exceptions
     */
    private String removeExceptions(String word) {
        if (word.toCharArray()[word.length() - 1] == 'e') {
            word = removeLastE(word);
        }
        return removeDoubleVowels(word);
    }

    /**
     * Method which is used to remove double vowels from word.
     * First string value splits to the char array, then every char, and
     * it's adjacent char is checked if they are vowels.
     *
     * @param word String value to be modified
     * @return String value after removing double vowels
     */
    private String removeDoubleVowels(String word) {
        StringBuilder sb = new StringBuilder(word);
        for (int i = 0; i < word.length() - 1; i++) {
            char[] letters = word.toCharArray();
            if (isVowel(letters[i]) && isVowel(letters[i + 1])) {
                word = sb.deleteCharAt((i + 1)).toString();
                i = 0;
            }
        }
        return word;
    }

    /**
     * Method which is used to remove last "e" from word
     *
     * @param word String value to be modified
     * @return String value after removing last "e"
     */
    private String removeLastE(String word) {
        StringBuilder sb = new StringBuilder(word);
        sb.deleteCharAt(word.length() - 1);
        return sb.toString();
    }

    /**
     * Method which checks if char is vowel
     *
     * @param w Char value to be checked
     * @return Boolean value, true-if char is a vowel, false-if not a vowel
     */
    private boolean isVowel(char w) {
        return w == 'a' || w == 'e' || w == 'i' || w == 'o' || w == 'u' || w == 'y';
    }

    /**
     * Method which counts amount of vowel in word
     *
     * @param word String value of word whose vowels must be counted
     * @return Integer value of vowels amount
     */
    private int getVowelsAmount(String word) {
        String vowels = word.replaceAll("(?i)[^aeiouy]+", "");
        return vowels.length() > 0 ? vowels.length() : 1;
    }

    /**
     * Method which checks if word is made of one syllable
     *
     * @param word String value to be checked for 1 vowel
     * @return Boolean value, true-if word is made of one vowel,
     * false - if word is made of two or more vowels
     */
    private boolean isOneSyllable(String word) {
        return word.toCharArray()[word.length() - 1] == 'e' && getVowelsAmount(word) == 1; //&& word.length() != 1
    }

}

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
        word = word.toLowerCase();

        if (word.endsWith("e")) {
            word = deleteLastE(word);
        }

        return countVowels(word);
    }

    /**
     * Function which counts vowels in word. Each char of the word checks if
     * it is vowel then syllableCounter is increased by 1, if current char
     * is not vowel(its own index in vowels list = -1) then this char is skipped and
     * the next one is checked. If no syllables was counted, returns 1.
     *
     * @param word String value of word whose syllables must be counted
     *
     * @return Integer value of counted syllables
     * */
    private int countVowels(String word) {
        String vowels = "aeiouy";
        boolean lastWasVowel = false;
        int syllableCount = 0;

        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);

            if (vowels.indexOf(currentChar) != -1) {
                if (!lastWasVowel) {
                    syllableCount++;
                }
                lastWasVowel = true;
            } else {
                lastWasVowel = false;
            }
        }

        if (syllableCount == 0) {
            return  1;
        }
        return syllableCount;
    }

    /**
     * Function which delete last char "e" in word. Called if
     * last char of the word is "e".
     *
     * @param word String value of word whose last char is "e"
     * @return String value without last char "e"
     * */
    private String deleteLastE(String word) {
        StringBuilder sb = new StringBuilder(word);
        sb.deleteCharAt(word.length() - 1);
        return sb.toString();
    }

}

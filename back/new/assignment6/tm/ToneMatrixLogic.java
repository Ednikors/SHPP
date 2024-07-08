package com.shpp.p2p.cs.bkuzhel.assignment6.tm;

import static java.lang.Math.abs;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {
        double[] result = new double[ToneMatrixConstants.sampleSize()];
        for (int row = 0; row < toneMatrix.length; row++) {
            if (toneMatrix[row][column]) {
                for (int i = 0; i < result.length; i++) {
                    result[i] += samples[row][i];
                }
            }
        }
        return normalizeResult(result);
    }

    /**
     * Function that normalize values in array. First, the largest value in the array is searched, then all values
     * in the array are divided by the value found.
     * @param result Array to be normalized
     * @return Normalized array
     * */
    public static double[] normalizeResult(double result[]) {
        double maxValue = findMaxValue(result);

        if (maxValue != 0) {
            for (int i = 0; i < result.length; i++) {
                result[i] /= maxValue;
            }
        }
        return result;
    }

    /**
     * Function which finds max value in array;
     *
     * @param result Array in which the maximum value is searched
     * @return max value in array
     * */
    public static double findMaxValue(double result[]){
        double maxValue = 0;
        for (double value : result) {
            maxValue = abs(value) > maxValue ? abs(value) : maxValue;
        }
        return maxValue;
    }

}

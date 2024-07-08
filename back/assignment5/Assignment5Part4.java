package com.shpp.p2p.cs.bkuzhel.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * A class that displays a certain column from a csv file on the screen
 */
public class Assignment5Part4 extends TextProgram {
    /**
     * Path to file
     */
    private static final String PATH_TO_FILE = "assets\\file.csv";

    public void run() {
        ArrayList<String> columnValues = extractColumn(PATH_TO_FILE, 2);
        if (columnValues != null) {
            for (String value : columnValues) {
                println(value);
            }
        }
    }

    /**
     * Method which splits data from CDV row to the separate fields.
     * Also has processing to correctly separate data that is enclosed in quotes.
     *
     * @param line String value of data from CSV row to be separated
     * @return String array of splited data row
     */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> fields = new ArrayList<>();
        String[] splitFields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        for (String field : splitFields) {
            field = field.replaceAll("^\"|\"$", "");
            field = field.replace("\"\"", "\"");
            fields.add(field);
        }
        return fields;
    }

    /**
     * Method which reads CSV file and uses a fieldsIn(...) method
     * to get data from chosen column
     *
     * @param filename    Name(path) to file
     * @param columnIndex Integer value of column index, which must be
     *                    displayed
     * @return String array which contains data from chosen column,
     * or null if file doesn't exist or don't have that many columns.
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<String> fields = fieldsIn(line);
                if (columnIndex < fields.size()) {
                    result.add(fields.get(columnIndex));
                } else {
                    println("This file does not contain that many columns");
                    return null;
                }
            }
        } catch (IOException e) {
            return null;
        }
        return result;
    }


}

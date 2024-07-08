package com.shpp.p2p.cs.bkuzhel.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * A class that displays a certain column from a csv file on the screen
 */
public class Assignment5Part4 extends TextProgram {

    /**Path to file*/
    private static final String PATH_TO_FILE = "ssets\\test.csv";

    public void run() {
        ArrayList<String> columnValues = extractColumn(PATH_TO_FILE, 1);
        if (columnValues != null) {
            for (String value : columnValues) {
                println(value);
            }
        } else {
            println("Something gone wrong :(");
        }
    }

    /**
     * Method which splits data from CSV row to the separate fields.
     *
     * @param line String value of data from CSV row
     * @return String array of splitted data row
     */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> fields = new ArrayList<>();
        Matcher m = Pattern.compile("(?:^|,)(\"(?:[^\"]+|\"\")*\"|[^,]*)").matcher(line);
        while (m.find()) {
            String field = m.group(1);
            if (field.startsWith("\"") && field.endsWith("\"")) {
                field = field.substring(1, field.length() - 1).replaceAll("\"\"", "\"");
            }
            fields.add(field);
        }
        return fields;
    }

    /**
     * Function which extract all columns from csv file.
     *
     * @param pathToFile path to file information from which must be extracted
     * @param columnIndex index of column to be extracted
     * @return list of values from chosen column in file
     * */
    private ArrayList<String> extractColumn(String pathToFile, int columnIndex) {
        ArrayList<String> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathToFile));
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> fields = fieldsIn(line);
                if (columnIndex < fields.size()) {
                    result.add(fields.get(columnIndex));
                }
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return result;
    }


}

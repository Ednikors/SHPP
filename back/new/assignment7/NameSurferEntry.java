package com.shpp.p2p.cs.bkuzhel.assignment7;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

    /**
     * Global value for name of entry
     */
    private String name;
    /**
     * Global array of decades of entry
     */
    private int[] decades = new int[NDECADES];

    /* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        String[] splittedLine = line.split(" ");
        name = splittedLine[0];
        for (int i = 0; i < NDECADES; i++) {
            decades[i] = Integer.parseInt(splittedLine[i + 1]);
        }
    }

    /* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        return decades[decade];
    }

    /* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        StringBuilder decadesString = new StringBuilder();
        for (int decade : decades) {
            decadesString.append(decade).append(" ");
        }
        return name + "[" + decadesString + "]";
    }

    /**
     * Redefined object comparison method. Compares name and decade.
     *
     * @param o Object for comparison
     * @return true, if the objects are identical, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NameSurferEntry entry = (NameSurferEntry) o;
        return Objects.equals(name, entry.name) && Arrays.equals(decades, entry.decades);
    }


    /**
     * Redefined hash code generation method. Uses name and decade.
     *
     * @return Hash code of the object
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(decades);
        return result;
    }
}


package org.minimalna.latinsquare.util;

import java.util.stream.IntStream;

public class TestUtil {
    /**
     * Generates a valid latin square starting from the first row readily filled up with values from 1 to n.
     * The next rows are each gradually filled up based on their previous row's corresponding cell shifted by one.
     * @param size the size of the n*n array
     * @return a valid latin square of the given size, filled up with values ranging from 1 to n
     */
    public static int[][] generateLatinSquare(int size) {
        int[][] latin = new int[size][size];
        latin[0] = IntStream.rangeClosed(1, size).toArray();

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size; j++) {
                latin[i][j] = latin[i - 1][(j + 1) % size];
            }
        }
        return latin;
    }
}

package org.minimalna.latinsquare.util;

import java.util.stream.IntStream;

public class LatinSquareGenerator {
    /**
     * Generates an n*n int matrix containing values from 1 to size inclusive.
     * @param size the size of the square
     * @return an n*n size valid latin square
     */
    public static int[][] generateLatinSquare(int size) {
        int[] base = IntStream.rangeClosed(1, size).toArray();

        int[][] latin = new int[size][size];

        latin[0] = base;

        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size; j++) {
                latin[i][j] = latin[i - 1][(j + 1) % size];
            }
        }
        return latin;
    }
}


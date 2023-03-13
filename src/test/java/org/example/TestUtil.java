package org.example;

import java.util.stream.IntStream;

public class TestUtil {

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

package org.minimalna.latinsquare.util;

import java.util.stream.IntStream;

public class SudokuGenerator implements SquareGenerator{
    /**
     * Generates an n*n int matrix containing values from 1 to size inclusive, representing a valid sudoku.
     *
     * @param size the size of the inner grid in the Sudoku puzzle
     * @return an n^2*n^2 size valid latin square
     */
    public int[][] generate(int size) {
        int totalSize = size * size;

        int[][] sudoku = new int[totalSize][totalSize];
        sudoku[0] = IntStream.rangeClosed(1, totalSize).toArray();

        for (int i = 1; i < totalSize; i++) {
            int step = 1;
            if (i % size != 0)
                step = size;
            for (int j = 0; j < totalSize; j++) {
                if (step != 1) {
                    sudoku[i][j] = sudoku[i - 1][(j + step) % totalSize];
                } else {
                    sudoku[i][j] = sudoku[i - size][((j + step) + totalSize) % totalSize];
                }
            }
        }
        return sudoku;
    }
}

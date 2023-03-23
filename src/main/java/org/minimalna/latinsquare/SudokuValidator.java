package org.minimalna.latinsquare;

import org.apache.commons.lang3.ArrayUtils;
import org.minimalna.latinsquare.util.ValidationUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuValidator {
    private final List<Set<Integer>> gridSets = new ArrayList<>();

    /**
     * Validates whether the given 2D integer array represents a valid Sudoku puzzle.
     *
     * The Sudoku matrix is valid if each row, column, and submatrix contains all digits from 1 to n without repetition.
     * This method iterates through each cell in the Sudoku puzzle, and for each cell, it checks whether the current value
     * occurs in any of the corresponding row, column, and submatrix subsets. If the value has already occurred in any of
     * the subsets, then the method returns false, indicating that the puzzle is invalid.
     * Iterating through each cell exactly once results in O(n^2) time complexity, where n is the size of one dimension of the Sudoku puzzle.
     * The worst-case time complexity of validating the cell is O(1).
     *
     * Space complexity: O(n^2) where n is the size of the Sudoku puzzle. This is because the method creates 3*n hash sets of size n each to
     * store the values in each row, column, and submatrix of the Sudoku puzzle.
     *
     * @param sudoku a 2D integer array representing the Sudoku puzzle
     * @return true if the Sudoku puzzle is valid, false otherwise
     */
    public boolean validate(int[][] sudoku) {
        if (ArrayUtils.isEmpty(sudoku)) return false;

        int size = sudoku.length;

        for (int i = 0; i < 3 * size; i++) {
            gridSets.add(new HashSet<>());
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!validateInSubsets(sudoku, i, j)) {
                    System.out.println("validating row: " + i + ", col: " + j);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateInSubsets(int[][] sudoku, int row, int col) {
        int size = sudoku.length;
        int innerSize = (int) Math.sqrt(size);
        int currentSubgrid = innerSize * (row / innerSize) + (col / innerSize);

        Set<Integer> currentRow = gridSets.get(row);
        Set<Integer> currentCol = gridSets.get(size + col);
        Set<Integer> currentSubMatrix = gridSets.get(2 * size + currentSubgrid);

        int currentValue = sudoku[row][col];

        return (ValidationUtils.isInRange(currentValue, size) &&
                currentRow.add(currentValue) &&
                currentCol.add(currentValue) &&
                currentSubMatrix.add(currentValue));
    }

}

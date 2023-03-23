package org.minimalna.latinsquare;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuSpaceOptimizedValidator implements SudokuValidator{

    /**
     * Validates whether the given 2D integer array represents a valid Sudoku puzzle.
     *
     * The Sudoku matrix is valid if each row, column, and submatrix contains all digits from 1 to n without repetition.
     * This method iterates through each cell in the Sudoku puzzle, and for each cell, it checks whether the current value
     * occurs in any of the corresponding row, column, and submatrix subsets. If the value has already occurred in any of
     * the subsets, then the method returns false, indicating that the puzzle is invalid.
     * Iterating through each cell exactly once results in O(n^2) time complexity, where n is the size of one dimension of the Sudoku puzzle.
     *
     * Space complexity: O(n^2) where n is the size of the Sudoku puzzle.
     *
     * @param sudoku a 2D integer array representing the Sudoku puzzle
     * @return true if the Sudoku puzzle is valid, false otherwise
     */
    public boolean validate(int[][] board) {
        if (ArrayUtils.isEmpty(board)) return false;

        int totalSize = board.length;
        int innerSize = (int) Math.sqrt(totalSize);

        int[][] subMatricesRow = new int[innerSize][totalSize];

        for (int i = 0; i < totalSize; i++) {
            int[] currentRow = board[i];
            int[] rowColumn = new int[totalSize];
            int r = 0;

            for (int j = 0; j < totalSize; j++) {
                int currentValue = board[i][j];
                rowColumn[j] = currentValue;

                r = i % innerSize;

                if (r <= innerSize - 1) {
                    subMatricesRow[j / innerSize][r * innerSize + j % innerSize] = currentValue;
                }

            }
            //reached the bottom row of a submatrix, we can now run validation for all submatrices
            if (r == innerSize - 1) {
                for (int[] subMatrix : subMatricesRow) {
                    if (!validateSubset(subMatrix)) {
                        return false;
                    }
                }
            }

            if (!validateSubset(currentRow)) {
                return false;
            }

            //validate all columns during the first row traversal
            if (i == 0) {
                for (int j = 0; j < totalSize; j++) {
                    rowColumn[j] = board[j][i];
                }
                if (!validateSubset(rowColumn)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateSubset(int[] nums) {
        int size = nums.length;
        Set<Integer> validNums = IntStream.rangeClosed(1, size).boxed().collect(Collectors.toSet());

        for (int num : nums) {
            if (!validNums.remove(num))
                return false;
        }
        return true;
    }
}

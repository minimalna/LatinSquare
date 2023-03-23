package org.minimalna.latinsquare;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.minimalna.latinsquare.util.SudokuGenerator;

class SudokuValidatorTest {

    private static final SudokuSpaceOptimizedValidator SPACE_OPTIMIZED_VALIDATOR = new SudokuSpaceOptimizedValidator();
    private static final SudokuGenerator SUDOKU_GENERATOR = new SudokuGenerator();

    @Test
    void testValidateNullCellSquare() {
        int[][] grid = null;
        Assertions.assertFalse(SPACE_OPTIMIZED_VALIDATOR.validate(grid));
    }

    @Test
    void testValidateEmptySquare() {
        int[][] grid = new int[][]{};

        Assertions.assertFalse(SPACE_OPTIMIZED_VALIDATOR.validate(grid));
    }

    @Test
    public void testValid1rowSudoku() {
        int[][] inputSudoku = SUDOKU_GENERATOR.generate(1);

        Assertions.assertTrue(SPACE_OPTIMIZED_VALIDATOR.validate(inputSudoku));
    }

    @Test
    public void testInvalid1rowSudoku() {
        int[][] inputSudoku = SUDOKU_GENERATOR.generate(1);
        inputSudoku[0][0] = 2;

        Assertions.assertFalse(SPACE_OPTIMIZED_VALIDATOR.validate(inputSudoku));
    }

    @Test
    public void testValid9rowSudoku() {
        int[][] inputSudoku = SUDOKU_GENERATOR.generate(3);

        Assertions.assertTrue(SPACE_OPTIMIZED_VALIDATOR.validate(inputSudoku));
    }
}
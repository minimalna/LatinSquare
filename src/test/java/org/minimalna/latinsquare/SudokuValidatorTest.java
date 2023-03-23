package org.minimalna.latinsquare;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.minimalna.latinsquare.util.SudokuGenerator;


class SudokuValidatorTest {
    private static final SudokuValidator validator = new SudokuValidator();

    @Test
    void testValidateNullCellSquare() {
        int[][] grid = null;
        Assertions.assertFalse(validator.validate(grid));
    }

    @Test
    void testValidateEmptySquare() {
        int[][] grid = new int[][]{};

        SudokuValidator validator = new SudokuValidator();
        Assertions.assertFalse(validator.validate(grid));
    }

    @Test
    public void testValid1rowSudoku() {
        int[][] inputSudoku = SudokuGenerator.generateSudoku(1);

        SudokuValidator validator = new SudokuValidator();
        Assertions.assertTrue(validator.validate(inputSudoku));
    }

    @Test
    public void testInvalid1rowSudoku() {
        int[][] inputSudoku = SudokuGenerator.generateSudoku(1);
        inputSudoku[0][0] = 2;

        SudokuValidator validator = new SudokuValidator();
        Assertions.assertFalse(validator.validate(inputSudoku));
    }

    @Test
    public void testValid9rowSudoku() {
        int[][] inputSudoku = SudokuGenerator.generateSudoku(3);

        SudokuValidator validator = new SudokuValidator();
        Assertions.assertTrue(validator.validate(inputSudoku));
    }

    @Test
    public void testValid100rowSudoku() {
        int[][] inputSudoku = SudokuGenerator.generateSudoku(10);

        SudokuValidator validator = new SudokuValidator();
        Assertions.assertTrue(validator.validate(inputSudoku));
    }

    @Test
    public void testValid625rowSudoku() {
        int[][] inputSudoku = SudokuGenerator.generateSudoku(25);

        SudokuValidator validator = new SudokuValidator();
        Assertions.assertTrue(validator.validate(inputSudoku));
    }

    @Test
    public void testInvalidDuplicatesSudoku() {
        int[][] inputSudoku = SudokuGenerator.generateSudoku(3);
        inputSudoku[0][0] = 2;

        SudokuValidator validator = new SudokuValidator();
        Assertions.assertFalse(validator.validate(inputSudoku));
    }


    @Test
    public void testInvalidValuesSudoku() {
        int[][] inputSudoku = SudokuGenerator.generateSudoku(3);
        inputSudoku[0][0] = 11;

        SudokuValidator validator = new SudokuValidator();
        Assertions.assertFalse(validator.validate(inputSudoku));
    }
}
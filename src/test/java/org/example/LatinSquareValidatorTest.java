package org.example;

import org.example.util.LatinSquareGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LatinSquareValidatorTest {

    @Test
    void testValidateEmptySquare() {
        int[][] grid = new int[][]{};

        Assertions.assertFalse(LatinSquareValidator.validateInPlaceLatinSquare(grid));
    }

    @Test
    void testValidateNullCellSquare() {
        int[][] grid = null;

        Assertions.assertFalse(LatinSquareValidator.validateInPlaceLatinSquare(grid));
    }
    
    @Test
    void testValidate1CellValidSquare() {
        int[][] grid = new int[][]{{1}};

        Assertions.assertTrue(LatinSquareValidator.validateInPlaceLatinSquare(grid));
    }

    @Test
    void testValidate3rowsValidSquare() {
        int[][] grid = new int[][]{{1,2,3},{3,1,2},{2,3,1}};

        Assertions.assertTrue(LatinSquareValidator.validateInPlaceLatinSquare(grid));
    }

    @Test
    void testValidate10rowValidSquare() {
        int[][] grid = LatinSquareGenerator.generateLatinSquare(10);

        Assertions.assertTrue(LatinSquareValidator.validateInPlaceLatinSquare(grid));
    }

    @Test
    void testValidate10000rowValidSquareUtil() {
        int[][] grid =  TestUtil.generateLatinSquare(10000);

        Assertions.assertTrue(LatinSquareValidator.validateInPlaceLatinSquare(grid));
    }

    @Test
    void testValidateNonValidOrderSquare() {
        int[][] grid = new int[][]{{1,3,2},{3,1,2},{2,3,1}};

        Assertions.assertFalse(LatinSquareValidator.validateInPlaceLatinSquare(grid));
    }

    @Test
    void testValidateNonValidDuplicateValuesSquare() {
        int[][] grid = new int[][]{{1,3,1},{3,1,1},{2,3,1}};

        Assertions.assertFalse(LatinSquareValidator.validateInPlaceLatinSquare(grid));
    }

    @Test
    void testValidateNonValidValuesSquare() {
        int[][] grid = new int[][]{{0,2,7},{3,1,2},{2,3,1}};

        Assertions.assertFalse(LatinSquareValidator.validateInPlaceLatinSquare(grid));
    }

    @Test
    void testValidate10rowInvalidDuplicateInRowAndColSquare() {
        LatinSquareGenerator.generateLatinSquare(10);

        int[][] grid = new int[][]{
                {1, 2, 3, 4, 5, 1, 7, 8, 9, 10},
                {10, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {9, 10, 1, 2, 3, 4, 5, 6, 7, 8},
                {8, 9, 10, 1, 2, 3, 4, 5, 6, 7},
                {7, 8, 9, 10, 1, 2, 3, 4, 5, 6},
                {1, 7, 8, 9, 10, 6, 2, 3, 4, 5},
                {5, 6, 7, 8, 9, 10, 1, 2, 3, 4},
                {4, 5, 6, 7, 8, 9, 10, 1, 2, 3},
                {3, 4, 5, 6, 7, 8, 9, 10, 1, 2},
                {2, 3, 4, 5, 6, 7, 8, 9, 10, 1}};

        Assertions.assertFalse(LatinSquareValidator.validateInPlaceLatinSquare(grid));
    }

    @Test
    void testvalidateExtraSpaceLatinSquareValidSquare() {
        int[][] grid = new int[][]{{1,2,3},{3,1,2},{2,3,1}};

        Assertions.assertTrue(LatinSquareValidator.validateExtraSpaceLatinSquare(grid));
    }

    @Test
    void testValidateStoreNonValidOrderSquare() {
        int[][] grid = new int[][]{{1,3,2},{3,1,2},{2,3,1}};

        Assertions.assertFalse(LatinSquareValidator.validateExtraSpaceLatinSquare(grid));
    }

    @Test
    void testValidateStoreNonValidDuplicateValuesSquare() {
        int[][] grid = new int[][]{{1,3,1},{3,1,1},{2,3,1}};

        Assertions.assertFalse(LatinSquareValidator.validateExtraSpaceLatinSquare(grid));
    }

    @Test
    void testValidateStoreNonValidValuesSquare() {
        int[][] grid = new int[][]{{1,2,7},{3,1,2},{2,3,1}};

        Assertions.assertFalse(LatinSquareValidator.validateExtraSpaceLatinSquare(grid));
    }
}
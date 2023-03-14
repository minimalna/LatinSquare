package org.minimalna.latinsquare;

import org.minimalna.latinsquare.util.LatinSquareGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.minimalna.latinsquare.util.TestUtil;

import java.util.stream.Stream;

class LatinSquareValidatorTest {
    private static final LatinSquareInPlaceValidator IN_PLACE_VALIDATOR = new LatinSquareInPlaceValidator();
    private static final LatinSquareExtraSpaceValidator EXTRA_SPACE_VALIDATOR = new LatinSquareExtraSpaceValidator();

    @ParameterizedTest
    @MethodSource("provideValidators")
    void testValidateNullCellSquare(LatinSquareValidator validator) {
        int[][] grid = null;

        Assertions.assertFalse(validator.validate(grid));
    }

    @ParameterizedTest
    @MethodSource("provideValidators")
    void testValidateEmptySquare(LatinSquareValidator validator) {
        int[][] grid = new int[][]{};

        Assertions.assertFalse(validator.validate(grid));
    }

    @ParameterizedTest
    @MethodSource("provideValidators")
    void testValidate1CellValue1Square(LatinSquareValidator validator) {
        int[][] grid = new int[][]{{1}};

        Assertions.assertTrue(validator.validate(grid));
    }
    @ParameterizedTest
    @MethodSource("provideValidators")
    void testValidate1CellNonValue1Square(LatinSquareValidator validator) {
        int[][] grid = new int[][]{{2}};

        Assertions.assertFalse(validator.validate(grid));
    }
    @ParameterizedTest
    @MethodSource("provideValidators")
    void testValidate3rowsValidSquare(LatinSquareValidator validator) {
        int[][] grid = new int[][]{{1, 2, 3}, {3, 1, 2}, {2, 3, 1}};

        Assertions.assertTrue(validator.validate(grid));
    }

    @ParameterizedTest
    @MethodSource("provideValidators")
    void testValidate10rowValidSquare(LatinSquareValidator validator) {
        int[][] grid = LatinSquareGenerator.generateLatinSquare(10);

        Assertions.assertTrue(validator.validate(grid));
    }

    @ParameterizedTest
    @MethodSource("provideValidators")
    void testValidate1000rowValidSquare(LatinSquareValidator validator) {
        int[][] grid = TestUtil.generateLatinSquare(1000);

        Assertions.assertTrue(validator.validate(grid));
    }

    @ParameterizedTest
    @MethodSource("provideValidators")
    void testValidateInvalidOrderSquare(LatinSquareValidator validator) {
        int[][] grid = new int[][]{{1, 3, 2}, {3, 1, 2}, {2, 3, 1}};

        Assertions.assertFalse(validator.validate(grid));
    }

    @ParameterizedTest
    @MethodSource("provideValidators")
    void testValidateInvalidDuplicateValuesSquare(LatinSquareValidator validator) {
        int[][] grid = new int[][]{{1, 3, 1}, {3, 1, 1}, {2, 3, 1}};

        Assertions.assertFalse(validator.validate(grid));
    }

    @ParameterizedTest
    @MethodSource("provideValidators")
    void testValidateInvalidValuesSquare(LatinSquareValidator validator) {
        int[][] grid = new int[][]{{0, 2, 7}, {3, 1, 2}, {2, 3, 1}};

        Assertions.assertFalse(validator.validate(grid));
    }

    @ParameterizedTest
    @MethodSource("provideValidators")
    void testValidate10rowInvalidDuplicateInRowAndColSquare(LatinSquareValidator validator) {
        int[][] grid = LatinSquareGenerator.generateLatinSquare(10);
        grid[0][1] = grid[0][0];

        Assertions.assertFalse(validator.validate(grid));
    }

    private static Stream<LatinSquareValidator> provideValidators() {
        return Stream.of(
                IN_PLACE_VALIDATOR,
                EXTRA_SPACE_VALIDATOR
        );
    }
}
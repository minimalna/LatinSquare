package org.minimalna.latinsquare;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LatinSquareInPlaceValidator implements LatinSquareValidator {

    /**
     * This solution walks through the matrix in a diagonal fashion and checks for the uniqueness of values in the rows
     * as well as the columns content to be equal to the rows. It has a time complexity of O(2 * n^2 + n) -> O(n^2).
     * The first part derives from visiting each element twice when gathering overlapping rows and columns. The second part
     * is explained by the removal of all the column elements from the row set. The extra space needed is O(2 * n) -> O(n).
     *
     * @param square a two-dimensional (n*n only) int matrix containing any possible int values in any order
     * @return a boolean representing the validation result
     */
    public boolean validate(int[][] square) {
        if (ArrayUtils.isEmpty(square)) return false;

        int size = square.length;
        if (size == 1) return true;

        for (int i = 0; i < size; i++) {
            Set<Integer> row = Arrays.stream(square[i])
                    .filter(v -> (v >= 1 && v <= size))
                    .boxed()
                    .collect(Collectors.toSet());

            if (row.size() != size) return false;

            Set<Integer> column = getUniqueValuesFromColumn(square, i);
            row.removeAll(column);

            if (!row.isEmpty()) return false;
        }

        return true;
    }

    private Set<Integer> getUniqueValuesFromColumn(int[][] square, int colNum) {
        return Arrays.stream(square)
                .map(row -> row[colNum])
                .collect(Collectors.toSet());
    }
}
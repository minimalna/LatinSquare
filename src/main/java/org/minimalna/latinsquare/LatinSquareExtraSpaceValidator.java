package org.minimalna.latinsquare;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LatinSquareExtraSpaceValidator implements LatinSquareValidator {

    /**
     * This solution focuses on visiting each element just once in an iterative manner, which results in O(n^2) time
     * complexity. As a tradeoff, the constant extra space needed is extended to O(n^2 + n) -> O(n^2). It stores all the
     * columns values in a dedicated list of sets corresponding to each column, while the extra space required for the
     * rows validation is always just n because the allocated space is reused with each outer iteration.
     *
     * @param square a two-dimensional (n*n only) int matrix containing any possible int values in any order
     * @return a boolean representing the validation result
     */
    public boolean validate(int[][] square) {
        if (ArrayUtils.isEmpty(square)) return false;

        int size = square.length;
        if (size == 1) return true;

        List<Set<Integer>> columns = new ArrayList<>(size);
        Set<Integer> currentRow = new HashSet<>(size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0) {
                    columns.add(new HashSet<>());
                }
                Set<Integer> currentCol = columns.get(j);
                int currentValue = square[i][j];

                if (isInRange(size, currentValue)) {
                    currentCol.add(currentValue);
                    currentRow.add(currentValue);
                } else {
                    return false;
                }
                if (i == size - 1 && currentCol.size() != size)
                    return false;
            }
            if (currentRow.size() != size)
                return false;

            currentRow = new HashSet<>(size);
        }

        return true;
    }

    private static boolean isInRange(int maxValue, int value) {
        return value >= 1 && value <= maxValue;
    }
}

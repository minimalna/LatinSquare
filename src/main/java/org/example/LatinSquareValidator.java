package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

public class LatinSquareValidator {

    /**
     * This solution walks through the matrix in a diagonal fashion and checks for the uniqueness of values in the rows
     * as well as the columns content to be equal to the rows. It has a time complexity of O(2 * n^2 + n)-> O(n^2).
     * The first part derives from visiting each element twice when gathering overlapping rows and columns. The second part
     * is explained by the removal of all the column elements from row set. The extra space needed is O(2 * n) -> O(n)
     *
     * @param square TODO
     * @return TODO
     */
    public static boolean validateInPlaceLatinSquare(int[][] square) {
        if (ArrayUtils.isEmpty(square)) return false;

        int size = square.length;
        if (size == 1) return true;

        for (int i = 0; i < size; i++) {
            Set<Integer> row = Arrays.stream(square[i])
                    .filter(v -> (v >= 1 && v <= size))
                    .boxed()
                    .collect(Collectors.toSet());

            if (row.size() != size) return false;

            Set<Integer> column = getCol(square, i);
            row.removeAll(column);

            if (!row.isEmpty()) return false;
        }

        return true;
    }

    /**
     * This solution focuses on visiting each element just once, which still results in O(n^2) time
     * complexity. Instead, the constant extra space needed is extended to O(n^2 + n) -> O(n^2).
     *
     * @param square TODO
     * @return TODO
     */
    public static boolean validateExtraSpaceLatinSquare(int[][] square) {
        int size = square.length;
        if (size <= 1) return true;

        List<Set<Integer>> columns = new ArrayList<>();
        Set<Integer> row = new HashSet<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0) {
                    columns.add(new HashSet<>());
                }
                Set<Integer> col = columns.get(j);
                if (square[i][j] > 0 && square[i][j] <= size) {
                    col.add(square[i][j]);
                    row.add(square[i][j]);
                }
                if (i == size - 1 && col.size() != size) {
                    return false;
                }
            }
            if (row.size() != size) {
                return false;
            }
            row = new HashSet<>();
        }

        return true;
    }

    private static Set<Integer> getCol(int[][] square, int colNum) {
        Set<Integer> columns = new HashSet<>();
        for (int[] row : square) {
            columns.add(row[colNum]);
        }
        return columns;
    }
}
package org.minimalna.latinsquare;

public interface LatinSquareValidator extends SquareValidator {
    /**
     * Validates a Latin Square.
     * @param square a two-dimensional (n*n only) int matrix containing any possible int values in any order
     * @return a boolean representing the validation result
     */
    boolean validate(int[][] square);
}

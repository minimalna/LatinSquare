package org.minimalna.latinsquare;

public interface SudokuValidator extends SquareValidator {

    boolean validate(int[][] square);
}

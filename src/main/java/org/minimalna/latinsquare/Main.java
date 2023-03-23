package org.minimalna.latinsquare;

import org.minimalna.latinsquare.util.UserInputReader;

public class Main {
    public static void main(String[] args) {
        int[][] square = UserInputReader.readUserInputMatrix();
        SquareValidator validator = UserInputReader.readUserInputSolutionType();
        boolean isValid = validator.validate(square);
        String validationType = validator instanceof SudokuValidator ? "Sudoku" : "Latin Square";
        System.out.println("The square you have entered is a " + (isValid ? "" : "NON-") + "VALID " + validationType + ". ");

        System.out.println("Thank you for using this program!");
    }
}

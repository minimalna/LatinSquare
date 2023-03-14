package org.minimalna.latinsquare;

import org.minimalna.latinsquare.util.UserInputReader;

public class Main {
    public static void main(String[] args) {
        int[][] square = UserInputReader.readUserInputMatrix();
        LatinSquareValidator validator = UserInputReader.readUserInputSolutionType();
        boolean isValid = validator.validate(square);
        System.out.println("The square you have entered is a " + (isValid ? "" : "NON-") + "VALID latin square.");
        System.out.println("Thank you for using this program!");
    }
}

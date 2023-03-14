package org.minimalna.latinsquare.util;

import org.minimalna.latinsquare.LatinSquareExtraSpaceValidator;
import org.minimalna.latinsquare.LatinSquareInPlaceValidator;
import org.minimalna.latinsquare.LatinSquareValidator;

import java.util.Scanner;

public class UserInputReader {
    /**
     * Reads the user input into an n*n matrix, both size and values given by the user inside the method
     *
     * @return the n*n matrix defined by user input
     */
    public static int[][] readUserInputMatrix() {
        int square[][] = null;

        int size;
        System.out.println("Welcome to Latin Square Validator!");
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the number of rows/columns of the matrix");
            size = in.nextInt();
            square = new int[size][size];

            System.out.println("Enter the elements of the matrix");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    square[i][j] = in.nextInt();
                }
            }

            System.out.println("Elements of the matrix are");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(square[i][j] + "  ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("An error occurred during input reading.");
        }
        return square;
    }

    /**
     * Reads the user input and returns a corresponding instance type of LatinSquareValidator. If the option is not valid,
     * exits the program with an exception.
     *
     * @return the corresponding instance type of LatinSquareValidator based on the input
     */
    public static LatinSquareValidator readUserInputSolutionType() {
        int validationType;
        LatinSquareValidator validator;

        System.out.println("""
                Validation type of your choice:
                [1] In-place validator
                [2] Extra space validator""");

        try (Scanner in = new Scanner(System.in)) {
            validationType = in.nextInt();

            switch (validationType) {
                case 1 -> validator = new LatinSquareInPlaceValidator();
                case 2 -> validator = new LatinSquareExtraSpaceValidator();
                default -> throw new RuntimeException("Please enter a valid option");
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during input reading.", e);
        }
        return validator;
    }
}

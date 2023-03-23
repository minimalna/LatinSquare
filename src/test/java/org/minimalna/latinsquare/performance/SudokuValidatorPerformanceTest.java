package org.minimalna.latinsquare.performance;

import org.junit.jupiter.api.Test;
import org.minimalna.latinsquare.*;

public class SudokuValidatorPerformanceTest {


    private static final int MATRIX_SIZE = 30;
    private static final int NR_OF_RUNS = 10;

    @Test
    public void evaluateSpaceOptimizedSudokuValidationPerformance() {
        SudokuSpaceOptimizedValidator validator = new SudokuSpaceOptimizedValidator();
        PerformanceEvaluator.evaluatePerformance(validator, MATRIX_SIZE, NR_OF_RUNS);
    }
}

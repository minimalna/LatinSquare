package org.minimalna.latinsquare.performance;

import org.junit.jupiter.api.Test;
import org.minimalna.latinsquare.LatinSquareExtraSpaceValidator;
import org.minimalna.latinsquare.LatinSquareInPlaceValidator;
import org.minimalna.latinsquare.LatinSquareValidator;

class PerformanceTest {

    private static final int MATRIX_SIZE = 1000;
    private static final int NR_OF_RUNS = 100;

    @Test
    public void evaluateInPlaceValidationPerformance() {
        LatinSquareValidator lsv = new LatinSquareInPlaceValidator();
        PerformanceEvaluator.evaluatePerformance(lsv, MATRIX_SIZE, NR_OF_RUNS);
    }

    @Test
    public void evaluateExtraSpaceValidationPerformance() {
        LatinSquareValidator lsv = new LatinSquareExtraSpaceValidator();
        PerformanceEvaluator.evaluatePerformance(lsv, MATRIX_SIZE, NR_OF_RUNS);
    }
}
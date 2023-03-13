package org.minimalna.latinsquare.performance;

import org.minimalna.latinsquare.LatinSquareValidator;
import org.minimalna.latinsquare.util.LatinSquareGenerator;

public class PerformanceEvaluator {

    public static void evaluatePerformance(LatinSquareValidator validator, int matrixSize, int nrOfRuns) {
        long accumulatedRuntime = 0L;

        int i = 0;
        while (i <= nrOfRuns) {
            int[][] sample = LatinSquareGenerator.generateLatinSquare(matrixSize);
            long startTime = System.currentTimeMillis();
            validator.validate(sample);
            long endTime = System.currentTimeMillis();
            if (i != 0) { // skipping first run to remove the bias of warmup
                accumulatedRuntime += endTime - startTime;
            }
            i++;
        }

        System.out.printf("Average time taken for %d validations of %d * %d grid: %d milliseconds.%n",
                nrOfRuns, matrixSize, matrixSize, accumulatedRuntime / nrOfRuns);
    }
}

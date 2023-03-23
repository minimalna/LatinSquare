package org.minimalna.latinsquare.performance;

import org.minimalna.latinsquare.SquareValidator;
import org.minimalna.latinsquare.SudokuValidator;
import org.minimalna.latinsquare.util.LatinSquareGenerator;
import org.minimalna.latinsquare.util.SquareGenerator;
import org.minimalna.latinsquare.util.SudokuGenerator;

public class PerformanceEvaluator {

    public static void evaluatePerformance(SquareValidator validator, int matrixSize, int nrOfRuns) {
        SquareGenerator generator = validator instanceof SudokuValidator ? new SudokuGenerator() : new LatinSquareGenerator();
        long averageRuntime = getAverageRuntime(validator, generator, matrixSize, nrOfRuns);

        System.out.printf("Average time taken for %d validations of %d * %d grid: %d milliseconds.%n",
                nrOfRuns, matrixSize, matrixSize, averageRuntime);
    }

    public static int getAverageRuntime(SquareValidator validator, SquareGenerator generator, int matrixSize, int nrOfRuns) {
        long accumulatedRuntime = 0L;

        int i = 0;
        while (i <= nrOfRuns) {
            int[][] sample = generator.generate(matrixSize);
            long startTime = System.currentTimeMillis();
            validator.validate(sample);
            long endTime = System.currentTimeMillis();
            if (i != 0) { // skipping first run to remove the bias of warmup
                accumulatedRuntime += endTime - startTime;
            }
            i++;
        }
        return (int) accumulatedRuntime / nrOfRuns;
    }
}

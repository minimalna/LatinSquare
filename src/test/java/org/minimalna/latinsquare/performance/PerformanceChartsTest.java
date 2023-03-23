package org.minimalna.latinsquare.performance;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.jupiter.api.Test;
import org.minimalna.latinsquare.LatinSquareExtraSpaceValidator;
import org.minimalna.latinsquare.LatinSquareInPlaceValidator;
import org.minimalna.latinsquare.SudokuSpaceOptimizedValidator;
import org.minimalna.latinsquare.util.LatinSquareGenerator;
import org.minimalna.latinsquare.util.SudokuGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class PerformanceChartsTest {
    private static final List<Integer> latinSquareMatrixSizes = IntStream.iterate(1, n -> n <= 1000, n -> n == 1 ? n + 99 : n + 100).boxed().toList();
    private static final List<Integer> sudokuMatrixSizes = IntStream.iterate(1, n -> n <= Math.sqrt(2000), n -> n == 1 ? n + 9 : n + 3).boxed().toList();
    private static final LatinSquareInPlaceValidator LATIN_SQUARE_IN_PLACE_VALIDATOR = new LatinSquareInPlaceValidator();
    private static final LatinSquareExtraSpaceValidator LATIN_SQUARE_EXTRA_SPACE_VALIDATOR = new LatinSquareExtraSpaceValidator();
    private static final SudokuSpaceOptimizedValidator SUDOKU_SPACE_OPTIMIZED_VALIDATOR = new SudokuSpaceOptimizedValidator();

    private static final LatinSquareGenerator LATIN_SQUARE_GENERATOR = new LatinSquareGenerator();
    private static final SudokuGenerator SUDOKU_GENERATOR = new SudokuGenerator();
    private static final int NR_OF_RUNS = 30;

    @Test
    public void latinSquareCharts() throws IOException {
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        latinSquareMatrixSizes.forEach(size -> {
            System.out.println("Calculating performance for size: " + size);
            line_chart_dataset.addValue(
                    PerformanceEvaluator.getAverageRuntime(LATIN_SQUARE_IN_PLACE_VALIDATOR, LATIN_SQUARE_GENERATOR, size, NR_OF_RUNS),
                    "In-place",
                    size.toString()
            );
        });
        latinSquareMatrixSizes.forEach(size -> {
            System.out.println("Calculating performance for size: " + size);
            line_chart_dataset.addValue(
                    PerformanceEvaluator.getAverageRuntime(LATIN_SQUARE_EXTRA_SPACE_VALIDATOR,  LATIN_SQUARE_GENERATOR, size, NR_OF_RUNS),
                    "Extra space",
                    size.toString()
            );
        });

        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Comparison of time taken by in-place vs. extra-space validation for different matrix sizes",
                "Size (N) of matrix", "Time in milliseconds",
                line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 1024;    /* Width of the image */
        int height = 768;   /* Height of the image */
        File lineChart = new File("charts/Latin_square_performance_latest.jpeg");
        ChartUtils.saveChartAsJPEG(lineChart, lineChartObject, width, height);
    }

    @Test
    public void sudokuCharts() throws IOException {
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

        sudokuMatrixSizes.forEach(size -> {
            int totalSize = size*size;
            System.out.println("Calculating sudoku validator performance for size: " + totalSize);
            line_chart_dataset.addValue(
                    PerformanceEvaluator.getAverageRuntime(SUDOKU_SPACE_OPTIMIZED_VALIDATOR, SUDOKU_GENERATOR, size, NR_OF_RUNS),
                    "In-place",
                    String.valueOf(totalSize)
            );
        });

        JFreeChart lineChartObject = ChartFactory.createLineChart(
                "Time taken by space optimized  Sudoku validation for different matrix sizes",
                "Size (N) of matrix", "Time in milliseconds",
                line_chart_dataset, PlotOrientation.VERTICAL,
                true, true, false);

        int width = 1024;    /* Width of the image */
        int height = 768;   /* Height of the image */
        File lineChart = new File("charts/Sudoku_performance_latest.jpeg");
        ChartUtils.saveChartAsJPEG(lineChart, lineChartObject, width, height);
    }
}

package org.minimalna.latinsquare.performance;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.jupiter.api.Test;
import org.minimalna.latinsquare.LatinSquareExtraSpaceValidator;
import org.minimalna.latinsquare.LatinSquareInPlaceValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class PerformanceChartsTest {
    private static final List<Integer> matrixSizes = IntStream.iterate(1, n -> n <= 600, n -> n + 50).boxed().toList();
    private static final LatinSquareInPlaceValidator IN_PLACE_VALIDATOR = new LatinSquareInPlaceValidator();
    private static final LatinSquareExtraSpaceValidator EXTRA_SPACE_VALIDATOR = new LatinSquareExtraSpaceValidator();
    private static final int NR_OF_RUNS = 50;

    @Test
    public void chart() throws IOException {
        DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
        matrixSizes.forEach(size -> {
            System.out.println("Calculating performance for size: " + size);
            line_chart_dataset.addValue(
                    PerformanceEvaluator.getAverageRuntime(IN_PLACE_VALIDATOR, size, NR_OF_RUNS),
                    "In-place",
                    size.toString()
            );
        });
        matrixSizes.forEach(size -> {
            System.out.println("Calculating performance for size: " + size);
            line_chart_dataset.addValue(
                    PerformanceEvaluator.getAverageRuntime(EXTRA_SPACE_VALIDATOR, size, NR_OF_RUNS),
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
        File lineChart = new File("charts/Performance_latest.jpeg");
        ChartUtils.saveChartAsJPEG(lineChart, lineChartObject, width, height);
    }
}

/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex03;

import java.util.List;
import java.util.stream.Stream;
import js8ri.util.FileUtil;

/**
 *
 * @author yoshiki
 */
public class ParallelStreamPerformance {

    private static final int MEASURE_COUNT = 10;

    /**
     * Class to collect statistics
     */
    private static class Statistics {

        long count;
        long startTime;
        long endTime;

    }

    public static void main(String[] args) {
        List<String> wap = FileUtil.readAsWords("WarAndPeace.txt");
        Statistics[] nonParallelStatistics = new Statistics[MEASURE_COUNT];
        Statistics[] parallelStatistics = new Statistics[MEASURE_COUNT];

        for (int i = 0; i < MEASURE_COUNT; i++) {
            nonParallelStatistics[i] = meaurePerformance(wap.stream());
        }

        for (int i = 0; i < MEASURE_COUNT; i++) {
            parallelStatistics[i] = meaurePerformance(wap.parallelStream());
        }

        long count1 = verifyCount(nonParallelStatistics);
        long count2 = verifyCount(parallelStatistics);

        if (count1 != count2) {
            throw new AssertionError("Invalid count: " + count1 + " " + count2);
        }

        System.out.printf("No. of words : %d%n", wap.size());
        System.out.printf("No. of lengthy words : %d%n", count1);

        System.out.printf("%d ns (stream)%n", averageElapsedTime(nonParallelStatistics));
        System.out.printf("%d ns (parallelStream) %n", averageElapsedTime(parallelStatistics));

    }

    private static Statistics meaurePerformance(Stream<String> stream) {
        Statistics st = new Statistics();

        st.startTime = System.nanoTime();
        st.count = stream.filter(w -> w.length() > 12).count();
        st.endTime = System.nanoTime();

        return st;
    }

    private static long verifyCount(Statistics[] statistics) {
        long count = statistics[0].count;

        for (int i = 1; i < statistics.length; i++) {
            if (count != statistics[i].count) {
                throw new AssertionError(
                        "Invalid count: " + count + " " + statistics[i].count);
            }
        }
        return count;
    }

    private static long averageElapsedTime(Statistics[] statistics) {
        long total = 0;

        for (Statistics st : statistics) {
            total += st.endTime - st.startTime;
        }

        return total / statistics.length;
    }
}

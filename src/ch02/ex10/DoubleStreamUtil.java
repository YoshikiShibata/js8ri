/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex10;

import java.util.stream.Stream;

/**
 * Write a call to reduce that can be used to compute the average of a
 * Stream<Double>. Why can’t you simply compute the sum and divide by count()?
 *
 * Stream<Double>の平均を計算するために使用できるreduce の呼び出しを書きなさ い。単純に合計を計算して、count()
 * で割ることができないのはなぜですか。
 */
public class DoubleStreamUtil {

    private DoubleStreamUtil() {
        // Non-instantiable
    }

    /**
     * Computes the average of Doubles of a stream. If the stream empty, then
     * 0,0 will be returned.
     *
     * @param stream stream of Doubles
     * @return average value
     */
    public static double average(Stream<Double> stream) {
        long[] count = new long[1];
        
        double sum = stream.reduce(0.0, (x, y) -> {
            count[0]++;
            return x + y;
            }
        );
        
        if (count[0] == 0) // empty stream
            return 0.0;
        
        System.out.println(count[0]);
        return sum / count[0];
    }
}

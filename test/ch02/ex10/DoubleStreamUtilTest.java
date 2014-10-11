/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class DoubleStreamUtilTest {

    @Test
    public void testEmptyStream() {
        Stream<Double> emptyStream = Stream.empty();

        double ave = DoubleStreamUtil.average(emptyStream);
        assertTrue(ave == 0.0);
    }

    @Test
    public void testLargeStream() {
        // Prepare
        final int NO_OF_DOUBLES = 1000;

        List<Double> dList = new ArrayList<>(NO_OF_DOUBLES);
        double total = 0.0;

        for (int i = 0; i < NO_OF_DOUBLES; i++) {
            double value = Math.random() * NO_OF_DOUBLES;
            dList.add(value);
            total += value;
        }

        // Action
        double ave = DoubleStreamUtil.average(dList.stream());

        // Check
        assertEquals(total / NO_OF_DOUBLES, ave, 0.001);
    }
}

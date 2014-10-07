/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex08;

import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for zip method
 */
public class StreamUtilTest {

    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionFirst() {
        StreamUtil.zip(null, Stream.empty());
    }

    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionSecond() {
        StreamUtil.zip(Stream.empty(), null);
    }

    @Test
    public void testOneElementStreams() {
        verifyZipStream(1, 1);
    }

    @Test
    public void testOneHundredElementsStreams() {
        verifyZipStream(100, 100);
    }

    @Test
    public void testDifferentSizeStreams() {
        verifyZipStream(100, 150);
    }

    @Test
    public void testDifferentSizeStreams2() {
        verifyZipStream(150, 100);
    }

    @Test
    public void testLargeStreams() {
        verifyZipStream(Integer.MAX_VALUE / 4, Integer.MAX_VALUE / 4);
    }

    private void verifyZipStream(int sizeOfFirst, int sizeOfSecond) {
        if (sizeOfFirst < 0 || sizeOfSecond < 0) {
            throw new AssertionError("illegal values");
        }

        // Prepare
        Stream<Integer> s1 = Stream.iterate(0, n -> n + 2).limit(sizeOfFirst);
        Stream<Integer> s2 = Stream.iterate(1, n -> n + 2).limit(sizeOfSecond);

        // Action
        Stream<Integer> zipped = StreamUtil.zip(s1, s2);

        // Check
        int[] value = new int[1];
        zipped.forEach(v -> {
            if (v != value[0]) {
                fail(String.format("expected: %d, value = %d", value[0], v));
            }
            value[0]++;
        });

        int minSize = sizeOfFirst >= sizeOfSecond ? sizeOfSecond : sizeOfFirst;
        int total = minSize * 2;
        assertTrue(total == value[0]);
    }
}

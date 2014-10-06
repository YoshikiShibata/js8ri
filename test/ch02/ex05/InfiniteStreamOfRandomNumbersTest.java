/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex05;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yoshiki
 */
public class InfiniteStreamOfRandomNumbersTest {
    private Stream<Long> randomStream;
    
    public InfiniteStreamOfRandomNumbersTest() {
    }

    
    @Before
    public void setUp() {
        randomStream = InfiniteStreamOfRandomNumbers.random(
                25214903917L, 11L, 1L << 48, 0L);
    }
   
    
    @Test
    public void testSimpleGeneration() {
        final int NO_OF_RANDOMS = 100;
        
        Set<Long> randomSet = new HashSet<>();
        
        randomStream.filter(randomSet::add).limit(NO_OF_RANDOMS).count();
        assertTrue(randomSet.size() == NO_OF_RANDOMS);
    }
    
    @Test
    public void testNegativeValues() {
        final int NO_OF_RANDOMS = 100;
        Set<Long> randomSet = new HashSet<>();
        
        randomStream.filter(randomSet::add).limit(NO_OF_RANDOMS).count();
        
        for (Long value: randomSet) {
            if (value < 0)
                return;
        }
        fail("No negative value");
    }
    
    @Test
    public void verifyMillionGeneration() {
        final int NO_OF_RANDOMS = 1_000_000;
        
        Stream<Long> rs = InfiniteStreamOfRandomNumbers.random(
                25214903917L, 11L, 1L << 63, 0L);
        
        long[] range = new long[20];
        rs.filter(value -> { countUp(range, value); return true; }).
                limit(NO_OF_RANDOMS).count();
        
        for (long occurrence: range) {
            System.out.println(occurrence);
        }
        
    }
    
    private static void countUp(long[] range, long value) {
        if ((range.length % 2) != 0)
            throw new AssertionError("the size of range must be even.");
        
        long interval = Long.MAX_VALUE / (range.length / 2);
        
        for (int i = 0; i < range.length; i++) {
            long lower = Long.MIN_VALUE + i * interval;
            long upper = lower + interval;
            
            if (lower <= value && value < upper) {
                range[i] += 1;
                return;
            }
        }
    }
}

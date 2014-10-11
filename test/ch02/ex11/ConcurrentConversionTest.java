/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex11;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for ConcurrentConversion. 
 */
public class ConcurrentConversionTest {

    @Test(expected = NullPointerException.class)
    public void testNullPointerException() {
        ConcurrentConversion.toList(null, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSize() {
        ConcurrentConversion.toList(Stream.<Integer>empty(), -1);
    }

    @Test
    public void testEmptyStream() {
        List<Integer> list = ConcurrentConversion.toList(Stream.empty(), 0);

        assertTrue(list.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalSize() {
        List<Integer> list = ConcurrentConversion.toList(Stream.empty(), 1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalSize2() {
        List<Integer> original = new ArrayList<>();
        original.add(0);
        original.add(1);
        
        List<Integer> list = ConcurrentConversion.toList(original.stream(), 1);
    } 
    
    @Test
    public void testLargeStream() {
        // Prepare
        final int NO_OF_ELEMENTS = 100_000;
        List<Integer> original = new ArrayList<>(NO_OF_ELEMENTS);
        for (int i = 0; i < NO_OF_ELEMENTS; i++)
            original.add(i);
        
        // Action
        List<Integer> list = ConcurrentConversion.toList(original.stream(), NO_OF_ELEMENTS);
        
        // Check
        Set<Integer> sorted = new TreeSet<>(list);
        assertTrue(sorted.size() == NO_OF_ELEMENTS);
        List<Integer> sortedList = new ArrayList<>(sorted);
        assertTrue(sortedList.equals(original));
    }

}

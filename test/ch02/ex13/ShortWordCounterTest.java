/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex13;

import java.util.List;
import java.util.stream.Stream;
import js8ri.util.FileUtil;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for ShortWordCounter
 */
public class ShortWordCounterTest {
    
    @Test(expected=NullPointerException.class)
    public void testNullPointerException() {
        ShortWordCounter.countShortWords(null, 12);
    }
    
    
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        ShortWordCounter.countShortWords(Stream.empty(), 0);
    }
    
    @Test
    public void countShortWords() {
        // Prepare
        List<String> words = FileUtil.readAsWords("WarAndPeace.txt");
        
        // Action
        int[] occurences = ShortWordCounter.countShortWords(words.stream(), 12);
        
        // Check
        assertTrue(occurences != null);
        assertTrue(occurences.length == 12);
        assertTrue(occurences[0] == 0);
        
        // Print Results
        for (int i = 0; i < occurences.length; i++) {
            System.out.printf("%2d : %d%n", i, occurences[i])
;        }
    }
}

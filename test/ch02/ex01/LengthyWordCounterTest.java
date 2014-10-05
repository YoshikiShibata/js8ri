/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex01;

import java.io.IOException;
import java.util.List;
import java.util.RandomAccess;
import js8ri.util.FileUtil;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test code for LegnthyWordCounter.
 */
public class LengthyWordCounterTest {

    private static List<String> words;

    public LengthyWordCounterTest() {
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        words = FileUtil.readAsWords("alice.txt");
        assertTrue(words instanceof RandomAccess);
    }

    @Test
    public void testCount() {
        // Prepare
        long start1 = System.nanoTime();
        int count = 0;
        for (String w : words) {
            if (w.length() > 12) {
                count++;
            }
        }
        long end1 = System.nanoTime();
        System.out.println("count1 = " + count + ": " + (end1 - start1));
        
        
        // Action
        LengthyWordCounter lwc = new LengthyWordCounter(12);
        long start2 = System.nanoTime();
        int count2 = lwc.count(words);
        long end2 = System.nanoTime();
        System.out.println("count2 = " + count2 + ": " + (end2 - start2));
        
        // Check
        assertTrue(count == count2);
        
        long start3 = System.nanoTime();
        long count3 = words.parallelStream().filter(w -> w.length() > 12).count();
        long end3 = System.nanoTime();
        System.out.println("count3 = " + count3 + ": " + (end3 - start3));
    }
}

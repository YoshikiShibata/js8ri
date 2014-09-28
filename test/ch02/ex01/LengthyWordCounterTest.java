/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author yoshiki
 */
public class LengthyWordCounterTest {

    private static List<String> words;

    public LengthyWordCounterTest() {
    }

    @BeforeClass
    public static void setUpClass() throws IOException {
        String contents = new String(Files.readAllBytes(
                Paths.get("alice.txt")), StandardCharsets.UTF_8);

        words = Arrays.asList(contents.split("[¥¥P{L}]+"));
        assertTrue(words instanceof RandomAccess);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
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

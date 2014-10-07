/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for CombineArrayLists
 */
public class CombineArrayListsTest {
    
    public CombineArrayListsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
    
    @Test
    public void testOneArrayList() {
        // Prepare
        CombineArrayLists.ArrayOfStreamFlatter<Integer> asf = CombineArrayLists.create1();
        ArrayList<Integer> as = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            as.add(i);
        Stream<ArrayList<Integer>> asli = Stream.of(as);
        
        // Action
        ArrayList<Integer> flatList = asf.flat(asli);
        
        // Check
        for (int i = 0; i < 10; i++) {
            assertTrue(i == flatList.get(i));
        }
    }
}

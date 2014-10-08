/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch02.ex09;

import java.util.ArrayList;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for CombineArrayLists
 */
public class CombineArrayListsTest {

    @Test
    public void testEmpryArrayList() {
        verifyEmptyArrayList(CombineArrayLists.create1());
        verifyEmptyArrayList(CombineArrayLists.create2());
        verifyEmptyArrayList(CombineArrayLists.create3());
    }

    private void verifyEmptyArrayList(CombineArrayLists.ArrayOfStreamFlatter<Integer> asf) {
        // Prepare
        ArrayList<Integer> emptyList = new ArrayList<>();
        Stream<ArrayList<Integer>> asli = Stream.of(emptyList);

        // Action
        ArrayList<Integer> flatList = asf.flat(asli);

        // Check
        assertTrue(flatList.isEmpty());
    }

    @Test
    public void testOneArrayList() {
        verifyOneArrayList(CombineArrayLists.create1());
        verifyOneArrayList(CombineArrayLists.create2());
        verifyOneArrayList(CombineArrayLists.create3());
    }

    private void verifyOneArrayList(CombineArrayLists.ArrayOfStreamFlatter<Integer> asf) {
        // Prepare
        ArrayList<Integer> as = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            as.add(i);
        }
        Stream<ArrayList<Integer>> asli = Stream.of(as);

        // Action
        ArrayList<Integer> flatList = asf.flat(asli);

        // Check
        for (int i = 0; i < 10; i++) {
            assertTrue(i == flatList.get(i));
        }
    }
}

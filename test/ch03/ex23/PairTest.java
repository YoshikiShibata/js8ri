/*
 * Copyright (C) 2014 Yoshiki Shibata. All rights reserved.
 */
package ch03.ex23;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test code for Pair
 */
public class PairTest {

    @Test
    public void testMap() {
        Pair<String> p = new Pair<>("10", "20");
        Pair<Integer> pi = p.map(Integer::valueOf);
        
        assertTrue(pi.first() == 10);
        assertTrue(pi.second() == 20);
    }
}
